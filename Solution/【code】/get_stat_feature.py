# coding=utf-8
'''
@Description: 从query log中提取视频精排模型训练所需的数据.
@Author: weihailu
@Date: 2020-05-14
@Email: weihailu@tencent.com
'''

'''
CREATE TABLE DDL
create table dwmid_daily_wxapp_doudi_page_stat_feature(
    ds bigint comment '日期分区 YYYYMMDD',
    query string comment 'query',
    doc_id string comment 'doc_id',
    doc_exp_cnt_1d int comment 'doc 1天的曝光数',
    doc_clk_cnt_1d int comment 'doc 1天的点击数',
    doc_exp_cnt_2d int comment 'doc 2天的曝光数',
    doc_clk_cnt_2d int comment 'doc 2天的点击数',
    doc_exp_cnt_3d int comment 'doc 3天的曝光数',
    doc_clk_cnt_3d int comment 'doc 3天的点击数',
    doc_exp_cnt_7d int comment 'doc 7天的曝光数',
    doc_clk_cnt_7d int comment 'doc 7天的点击数',
    query_doc_exp_cnt_1d int comment 'query_doc 1天的曝光数',
    query_doc_clk_cnt_1d int comment 'query_doc 1天的点击数',
    query_doc_exp_cnt_2d int comment 'query_doc 2天的曝光数',
    query_doc_clk_cnt_2d int comment 'query_doc 2天的点击数',
    query_doc_exp_cnt_3d int comment 'query_doc 3天的曝光数',
    query_doc_clk_cnt_3d int comment 'query_doc 3天的点击数',
    query_doc_exp_cnt_7d int comment 'query_doc 7天的曝光数',
    query_doc_clk_cnt_7d int comment 'query_doc 7天的点击数',
    doc_ctr_1d float comment 'doc 1天的点击率',
    doc_ctr_2d float comment 'doc 2天的点击率',
    doc_ctr_3d float comment 'doc 3天的点击率',
    doc_ctr_7d float comment 'doc 7天的点击率',
    query_doc_ctr_1d float comment 'query_doc 1天的点击率',
    query_doc_ctr_2d float comment 'query_doc 2天的点击率',
    query_doc_ctr_3d float comment 'query_doc 3天的点击率',
    query_doc_ctr_7d float comment 'query_doc 7天的点击率',
    doc_ctr_1d_smooth float comment 'doc 1天的点击率平滑',
    doc_ctr_2d_smooth float comment 'doc 2天的点击率平滑',
    doc_ctr_3d_smooth float comment 'doc 3天的点击率平滑',
    doc_ctr_7d_smooth float comment 'doc 7天的点击率平滑',
    query_doc_ctr_1d_smooth float comment 'query_doc 1天的点击率平滑',
    query_doc_ctr_2d_smooth float comment 'query_doc 2天的点击率平滑',
    query_doc_ctr_3d_smooth float comment 'query_doc 3天的点击率平滑',
    query_doc_ctr_7d_smooth float comment 'query_doc 7天的点击率平滑'
)comment 'developer=weihailu product=weihailu create_date=20200423 desc=兜底搜索精排训练数据'
PARTITION BY LIST(ds)
(
    PARTITION default
)
STORED AS ORCFILE COMPRESS

'''




import time
import numpy as np
from utils import list_day, utf8_encoder, delta_date
from pyspark.sql.functions import lit, mean
from pyspark.sql.types import StructField, StructType, StringType, IntegerType, LongType, FloatType, DoubleType
from pyspark.sql import SparkSession
from pytoolkit import TDWSQLProvider, TDWUtil
from pyspark import SparkContext, SQLContext, SparkConf
import json
import sys
reload(sys)
sys.setdefaultencoding("utf-8")


BANK_DB = 'wxg_wxplat_tdbank'
MMBIZ_DB = 'wxg_mmbiz_dw'
WXA_DB = 'wxg_wxa_offline_data'
ODS_TABLE = 'ods_hourly_wxapp_search_user_detail_info_at'
FEATURE_TB = 'dwmid_daily_wxapp_doudi_page_stat_feature'
EXP_TB = 'dwmid_daily_wxapp_doudi_page_exp_7d'



if __name__ == '__main__':
    try:
        if sys.argv.__len__() >= 2:
            for ele in sys.argv:
                print ele
            partition_ = sys.argv[1]
            user = sys.argv[2]
            passwd = sys.argv[3]

            conf = SparkConf().setAppName(
                'DAILY_VIDEO_PAGE_EXTRACT_LABEL_AND_FEATURE'
            ).set(
                'spark.driver.maxResultSize', '50g'
            ).set(
                "spark.network.timeout", "300s"
            )
            spark = SparkContext(conf=conf)
            sc = SparkSession(spark)
            wxa_util = TDWUtil(user=user, passwd=passwd, dbName=WXA_DB)
            wxa_tdw = TDWSQLProvider(sc, user=user, passwd=passwd, db=WXA_DB)
            bank_tdw = TDWSQLProvider(sc, user=user, passwd=passwd, db=BANK_DB)
            mmbiz_tdw = TDWSQLProvider(
                sc, user=user, passwd=passwd, db=MMBIZ_DB)

            # 输出表建立新分区
            if not wxa_util.partitionExist(FEATURE_TB, "p_" + partition_):
                wxa_util.createListPartition(
                    FEATURE_TB,
                    "p_" + partition_,
                    partition_
                )


            # 过去 7 天的分区，用于生成统计特征
            partitions = list_day(delta_date(partition_, 1), 7)

            ods_partitions = []
            for i in range(24):
                for partition in partitions:
                    ods_partitions.append('p_'+partition+str(i).rjust(2, '0'))


            ods_df = mmbiz_tdw.table(ODS_TABLE, ods_partitions)
            ods_df.createOrReplaceTempView('ods_tb')


            # 当天曝光的
            exp_df = wxa_tdw.table(EXP_TB, ["p_" + partition_])
            exp_df.createOrReplaceTempView('exp_tb')


            # label 和 统计类特征 
            feature_df = sc.sql(
                """
                select
                    a.query,
                    a.doc_id,
                    if(c.exp_cnt_1d is not null, c.exp_cnt_1d, 0) as doc_exp_cnt_1d,
                    if(c.clk_cnt_1d is not null, c.clk_cnt_1d, 0) as doc_clk_cnt_1d,
                    if(c.exp_cnt_2d is not null, c.exp_cnt_2d, 0) as doc_exp_cnt_2d,
                    if(c.clk_cnt_2d is not null, c.clk_cnt_2d, 0) as doc_clk_cnt_2d,
                    if(c.exp_cnt_3d is not null, c.exp_cnt_3d, 0) as doc_exp_cnt_3d,
                    if(c.clk_cnt_3d is not null, c.clk_cnt_3d, 0) as doc_clk_cnt_3d,
                    if(c.exp_cnt_7d is not null, c.exp_cnt_7d, 0) as doc_exp_cnt_7d,
                    if(c.clk_cnt_7d is not null, c.clk_cnt_7d, 0) as doc_clk_cnt_7d,

                    if(d.exp_cnt_1d is not null, d.exp_cnt_1d, 0) as query_doc_exp_cnt_1d,
                    if(d.clk_cnt_1d is not null, d.clk_cnt_1d, 0) as query_doc_clk_cnt_1d,
                    if(d.exp_cnt_2d is not null, d.exp_cnt_2d, 0) as query_doc_exp_cnt_2d,
                    if(d.clk_cnt_2d is not null, d.clk_cnt_2d, 0) as query_doc_clk_cnt_2d,
                    if(d.exp_cnt_3d is not null, d.exp_cnt_3d, 0) as query_doc_exp_cnt_3d,
                    if(d.clk_cnt_3d is not null, d.clk_cnt_3d, 0) as query_doc_clk_cnt_3d,
                    if(d.exp_cnt_7d is not null, d.exp_cnt_7d, 0) as query_doc_exp_cnt_7d,
                    if(d.clk_cnt_7d is not null, d.clk_cnt_7d, 0) as query_doc_clk_cnt_7d

                from
                (
                    select
                        query,
                        doc_id
                    from
                        exp_tb
                ) a
                left join
                (
                    select
                        doc_id,
                        sum(if(is_explosure=1 and dh >= %s,1,0)) as exp_cnt_1d,
                        sum(if(click_location > 0 and dh >= %s,1,0)) as clk_cnt_1d,
                        sum(if(is_explosure=1 and dh >= %s,1,0)) as exp_cnt_2d,
                        sum(if(click_location > 0 and dh >= %s,1,0)) as clk_cnt_2d,
                        sum(if(is_explosure=1 and dh >= %s,1,0)) as exp_cnt_3d,
                        sum(if(click_location > 0 and dh >= %s,1,0)) as clk_cnt_3d,
                        sum(if(is_explosure=1 and dh >= %s,1,0)) as exp_cnt_7d,
                        sum(if(click_location > 0 and dh >= %s,1,0)) as clk_cnt_7d
                    from
                    (
                        select
                            max(dh) as dh,
                            search_id,
                            doc_id,
                            is_explosure,
                            click_location
                        from
                            ods_tb
                        where substr(doc_id,0,1) = 'S' and is_explosure is not null
                        group by search_id, doc_id, is_explosure, click_location
                    ) t                    
                    group by doc_id
                ) c
                on a.doc_id = c.doc_id
                left join
                (
                    select
                        query,
                        doc_id,
                        sum(if(is_explosure=1 and dh >= %s,1,0)) as exp_cnt_1d,
                        sum(if(click_location > 0 and dh >= %s,1,0)) as clk_cnt_1d,
                        sum(if(is_explosure=1 and dh >= %s,1,0)) as exp_cnt_2d,
                        sum(if(click_location > 0 and dh >= %s,1,0)) as clk_cnt_2d,
                        sum(if(is_explosure=1 and dh >= %s,1,0)) as exp_cnt_3d,
                        sum(if(click_location > 0 and dh >= %s,1,0)) as clk_cnt_3d,
                        sum(if(is_explosure=1 and dh >= %s,1,0)) as exp_cnt_7d,
                        sum(if(click_location > 0 and dh >= %s,1,0)) as clk_cnt_7d
                    from
                    (
                        select
                            max(dh) as dh,
                            search_id,
                            query,
                            doc_id,
                            appid,
                            is_explosure,
                            click_location
                        from
                            ods_tb
                        where substr(doc_id,0,1) = 'S' and is_explosure is not null
                        group by search_id, query, doc_id, is_explosure, click_location, appid
                    ) t                    
                    group by query, doc_id
                ) d
                on a.doc_id = d.doc_id and a.query = d.query

                """ % (
                    delta_date(partition_, 1) +
                    '00', delta_date(partition_, 1) + '00',
                    delta_date(partition_, 2) +
                    '00', delta_date(partition_, 2) + '00',
                    delta_date(partition_, 3) +
                    '00', delta_date(partition_, 3) + '00',
                    delta_date(partition_, 7) +
                    '00', delta_date(partition_, 7) + '00',

                    delta_date(partition_, 1) +
                    '00', delta_date(partition_, 1) + '00',
                    delta_date(partition_, 2) +
                    '00', delta_date(partition_, 2) + '00',
                    delta_date(partition_, 3) +
                    '00', delta_date(partition_, 3) + '00',
                    delta_date(partition_, 7) +
                    '00', delta_date(partition_, 7) + '00'
                )
            )

            feature_df = feature_df.dropDuplicates(['query', 'doc_id'])

            mean_dict = {
                'mean_doc_exp_cnt_1d': 929,
                'mean_doc_clk_cnt_1d': 18.9,
                'mean_doc_exp_cnt_2d': 1788,
                'mean_doc_clk_cnt_2d': 35,
                'mean_doc_exp_cnt_3d': 2621,
                'mean_doc_clk_cnt_3d': 50.8,
                'mean_doc_exp_cnt_7d': 6018,
                'mean_doc_clk_cnt_7d': 116,

                'mean_query_doc_exp_cnt_1d': 718,
                'mean_query_doc_clk_cnt_1d': 12,
                'mean_query_doc_exp_cnt_2d': 1393,
                'mean_query_doc_clk_cnt_2d': 22.9,
                'mean_query_doc_exp_cnt_3d': 2051,
                'mean_query_doc_clk_cnt_3d': 33,
                'mean_query_doc_exp_cnt_7d': 4696,
                'mean_query_doc_clk_cnt_7d': 77
            }

            def __tran_feature_info(row, mean_dict= mean_dict):
                '''
                整理特征列表
                '''
                row = list(row)

                doc_ctr_1d = row[3] / float(row[2]) if row[2] > 0 else 0.0    # 计算 doc_ctr_1_day
                doc_ctr_2d = row[5] / float(row[4]) if row[4] > 0 else 0.0    # 计算 doc_ctr_2_day
                doc_ctr_3d = row[6] / float(row[6]) if row[6] > 0 else 0.0    # 计算 doc_ctr_3_day
                doc_ctr_7d = row[8] / float(row[8]) if row[8] > 0 else 0.0    # 计算 doc_ctr_7_day


                query_doc_ctr_1d = row[11] / float(row[10]) if row[10] > 0 else 0.0    # 计算 query_doc_ctr_1_day
                query_doc_ctr_2d = row[13] / float(row[12]) if row[12] > 0 else 0.0    # 计算 query_doc_ctr_2_day
                query_doc_ctr_3d = row[15] / float(row[14]) if row[14] > 0 else 0.0    # 计算 query_doc_ctr_3_day
                query_doc_ctr_7d = row[17] / float(row[16]) if row[16] > 0 else 0.0    # 计算 query_doc_ctr_7_day

                """ 平滑后的 ctr """
                doc_ctr_1d_smooth = (row[3] +  mean_dict['mean_doc_clk_cnt_1d'] ) / (float(row[2]) + mean_dict['mean_doc_exp_cnt_1d'] ) if row[2] > 0 else 0.0    
                doc_ctr_2d_smooth = (row[5] +  mean_dict['mean_doc_clk_cnt_2d'] ) / (float(row[4]) + mean_dict['mean_doc_exp_cnt_2d'] ) if row[4] > 0 else 0.0     
                doc_ctr_3d_smooth = (row[7] +  mean_dict['mean_doc_clk_cnt_3d'] ) / (float(row[6]) + mean_dict['mean_doc_exp_cnt_3d'] ) if row[6] > 0 else 0.0  
                doc_ctr_7d_smooth = (row[9] +  mean_dict['mean_doc_clk_cnt_7d'] ) / (float(row[8]) + mean_dict['mean_doc_exp_cnt_7d'] ) if row[8] > 0 else 0.0  

                query_doc_ctr_1d_smooth = (row[11] +  mean_dict['mean_query_doc_clk_cnt_1d'] ) / (float(row[10]) + mean_dict['mean_query_doc_exp_cnt_1d'] ) if row[10] > 0 else 0.0  
                query_doc_ctr_2d_smooth = (row[13] +  mean_dict['mean_query_doc_clk_cnt_2d'] ) / (float(row[12]) + mean_dict['mean_query_doc_exp_cnt_2d'] ) if row[12] > 0 else 0.0  
                query_doc_ctr_3d_smooth = (row[15] +  mean_dict['mean_query_doc_clk_cnt_3d'] ) / (float(row[14]) + mean_dict['mean_query_doc_exp_cnt_3d'] ) if row[14] > 0 else 0.0  
                query_doc_ctr_7d_smooth = (row[17] +  mean_dict['mean_query_doc_clk_cnt_7d'] ) / (float(row[16]) + mean_dict['mean_query_doc_exp_cnt_7d'] ) if row[16] > 0 else 0.0  

                return [int(partition_)] + row[:18] + [doc_ctr_1d, doc_ctr_2d, doc_ctr_3d, doc_ctr_7d, query_doc_ctr_1d, query_doc_ctr_2d, query_doc_ctr_3d, query_doc_ctr_7d] + [doc_ctr_1d_smooth, doc_ctr_2d_smooth, doc_ctr_3d_smooth, doc_ctr_7d_smooth, query_doc_ctr_1d_smooth, query_doc_ctr_2d_smooth, query_doc_ctr_3d_smooth, query_doc_ctr_7d_smooth]


            label_feature_rdd = feature_df.rdd.map(
                __tran_feature_info).filter(lambda x: x is not None)
            

            field_list = [
                ('ds', IntegerType()),
                ('query', StringType()),
                ('doc_id', StringType()),
                ('doc_exp_cnt_1d', IntegerType()),
                ('doc_clk_cnt_1d', IntegerType()),
                ('doc_exp_cnt_2d', IntegerType()),
                ('doc_clk_cnt_2d', IntegerType()),
                ('doc_exp_cnt_3d', IntegerType()),
                ('doc_clk_cnt_3d', IntegerType()),
                ('doc_exp_cnt_7d', IntegerType()),
                ('doc_clk_cnt_7d', IntegerType()),
                ('query_doc_exp_cnt_1d', IntegerType()),
                ('query_doc_clk_cnt_1d', IntegerType()),
                ('query_doc_exp_cnt_2d', IntegerType()),
                ('query_doc_clk_cnt_2d', IntegerType()),
                ('query_doc_exp_cnt_3d', IntegerType()),
                ('query_doc_clk_cnt_3d', IntegerType()),
                ('query_doc_exp_cnt_7d', IntegerType()),
                ('query_doc_clk_cnt_7d', IntegerType()),
                ('doc_ctr_1d', FloatType()),
                ('doc_ctr_2d', FloatType()),
                ('doc_ctr_3d', FloatType()),
                ('doc_ctr_7d', FloatType()),
                ('query_doc_ctr_1d', FloatType()),
                ('query_doc_ctr_2d', FloatType()),
                ('query_doc_ctr_3d', FloatType()),
                ('query_doc_ctr_7d', FloatType()),
                ('doc_ctr_1d_smooth', FloatType()),
                ('doc_ctr_2d_smooth', FloatType()),
                ('doc_ctr_3d_smooth', FloatType()),
                ('doc_ctr_7d_smooth', FloatType()),
                ('query_doc_ctr_1d_smooth', FloatType()),
                ('query_doc_ctr_2d_smooth', FloatType()),
                ('query_doc_ctr_3d_smooth', FloatType()),
                ('query_doc_ctr_7d_smooth', FloatType())
            ]


            _schema = StructType(
                [
                    StructField(ele[0], ele[1], True) for ele in field_list
                ]
            )

            label_feature_df = sc.createDataFrame(label_feature_rdd, _schema)

            wxa_tdw.saveToTable(
                label_feature_df, FEATURE_TB, 'p_' + partition_)


    finally:
        sc.stop()
        spark.stop()
















































































