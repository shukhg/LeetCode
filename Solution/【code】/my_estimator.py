import numpy as np
import tensorflow as tf
import pandas as pd
from tensorflow import estimator
from collections import defaultdict
from sklearn.metrics import roc_auc_score




def emb_init(feat_num, embedding_size, name='category_feature_embedding', zero_first_row=True, pre_trained=False, trained_emb_path=None):
    """
    功能: embedding 层
    参数:
        feat_num: 类别特征的总数, 这个要统计的
        zero_first_row: 第一行是否全 0
        pre_trained: 是否有预训练的 embedding
        trained_emb_path: 预训练 embedding 的路径
    返回:
        特征的 embedding, 维度为 [feat_num, embedding_size]
    """

    if not pre_trained:
        with tf.variable_scope("weight_matrix", reuse=tf.AUTO_REUSE):
            embeddings = tf.get_variable(name=name,
                                         dtype=tf.float32,
                                         shape=(feat_num, embedding_size),
                                         initializer=tf.contrib.layers.xavier_initializer())

        if zero_first_row:  #  第一行以 0 开头
            embeddings = tf.concat((tf.zeros(shape=[1, embedding_size]), embeddings[1:]), 0)
    else:
        with tf.variable_scope("weight_matrix"):
            load_emb = np.load(tf.gfile.GFile(trained_emb_path, "rb"))
            embeddings = tf.get_variable(name=name, shape=(feat_num, embedding_size), initializer=tf.constant_initializer(load_emb), trainable=True)

            sys.stdout.flush()

    return embeddings



def mask_emb_init(embedding_size, name='mask_embedding'):
    """
    功能: mask embedding 层
    返回:
        进行 mask 的 embedding, 维度为 [2, embedding_size] 这个 2 是 0 和 1 两种, embedding_size 维度也是全 0 或者 全 1
    """
    
    with tf.variable_scope("mask_embedding", reuse=tf.AUTO_REUSE):
        zeros = tf.zeros(shape=[1, embedding_size], name='zeros')
        ones = tf.ones(shape=[1, embedding_size], name='ones')
        mask_embeddings = tf.concat((zeros, ones), 0)
        mask_embeddings = tf.cast(mask_embeddings, tf.float32)
        mask_padding_lookup_table = tf.get_variable(name=name,
                                                    initializer=mask_embeddings,
                                                    dtype=tf.float32,
                                                    trainable=False,
                                                    )

    return mask_padding_lookup_table



def generate_label_embedding(label_name, labels_dict, params):
    """ 获取 label 的 embedding 矩阵 和 embedding_lookup_table """
    
    label_embedding_size = params[label_name + "_embedding_size"]
    label_num = params[label_name + "_feat_num"]
    embeddings_lookup_table = emb_init(feat_num = label_num, embedding_size=label_embedding_size, name= 'get_label_embedding_lookup_table', pre_trained=True, trained_emb_path="/data/user_embedding/ft_local/new_index_label_embedding.npy" )

    label = labels_dict[label_name]    # [batch_size]
    label_embedding = tf.nn.embedding_lookup(embeddings_lookup_table, label)  # [batch_size, feature_embedding_size]
    
    return label_embedding, embeddings_lookup_table







from collections import defaultdict
from sklearn.metrics import roc_auc_score


def cal_group_auc(labels, preds, user_id_list):
    """
    group auc 的计算, 如果全是 1 就会报错
    """

    print('*' * 50)
    if len(user_id_list) != len(labels):
        raise ValueError(
            "impression id num should equal to the sample num," \
            "impression id num is {0}".format(len(user_id_list)))
    group_score = defaultdict(lambda: [])
    group_truth = defaultdict(lambda: [])
    for idx, truth in enumerate(labels):
        user_id = user_id_list[idx]
        score = preds[idx]
        truth = labels[idx]
        group_score[user_id].append(score)
        group_truth[user_id].append(truth)

    group_flag = defaultdict(lambda: False)
    for user_id in set(user_id_list):
        truths = group_truth[user_id]
        flag = False
        for i in range(len(truths) - 1):
            if truths[i] != truths[i + 1]:
                flag = True
                break
        group_flag[user_id] = flag

    impression_total = 0
    total_auc = 0
    
    for user_id in group_flag:
        if group_flag[user_id]:
            auc = roc_auc_score(np.asarray(group_truth[user_id]), np.asarray(group_score[user_id]))
            total_auc += auc * len(group_truth[user_id])
            impression_total += len(group_truth[user_id])
    group_auc = float(total_auc) / impression_total
    group_auc = round(group_auc, 4)
    return group_auc




def recall_N(y_true, y_pred, N=10):
    return len(set(y_pred[:N]) & set(y_true)) * 1.0 / len(set(y_true))


def precision_N(y_true, y_pred, N=10):
    return len(set(y_pred[:N]) & set(y_true)) * 1.0 / min(N, len(set(y_pred)))




def parse_function(example_proto, is_predict):
    """
    功能: 解析tfrecord 中的每行
    参数:
        example_proto: 二进制后的 example
        is_predict: 如果是 predict, 输入的 label 是一个列表
    返回:
        features 字典和 label
    """

    dicts = {}
    category_feature_list = ['uin', 'region_code', 'grade', 'gender', 'age', 'platform', 'city_level', 'device']

    multi_category_feature_list_20 = ['tag_ad_day_7_after_hash', 'tag_ecc_day_7_after_hash', 'tag_app_day_7_after_hash', 'tag_news_day_7_after_hash', 'tag_social_day_7_after_hash', 
                                'tag_ad_day_14_after_hash', 'tag_ecc_day_14_after_hash', 'tag_app_day_14_after_hash', 'tag_news_day_14_after_hash', 'tag_social_day_14_after_hash', 
                                'tag_ad_day_30_after_hash', 'tag_ecc_day_30_after_hash', 'tag_app_day_30_after_hash', 'tag_news_day_30_after_hash', 'tag_social_day_30_after_hash',
                                'tag_ad_day_60_after_hash', 'tag_ecc_day_60_after_hash', 'tag_app_day_60_after_hash', 'tag_news_day_60_after_hash', 'tag_social_day_60_after_hash',
                                'tag_ad_day_180_after_hash', 'tag_ecc_day_180_after_hash', 'tag_app_day_180_after_hash', 'tag_news_day_180_after_hash', 'tag_social_day_180_after_hash',
                                'tag_ad_day_360_after_hash', 'tag_ecc_day_360_after_hash', 'tag_app_day_360_after_hash', 'tag_news_day_360_after_hash', 'tag_social_day_360_after_hash' ]
                                
    multi_category_feature_list_10 =  ['user_install_app_after_hash', 'user_install_ecc_app_after_hash', 'user_used_wxapp_after_hash', 'user_used_ecc_wxapp_after_hash', 'user_used_wxapp_after_group' ]

    label_list = ['pairwise_label_hot_random', 'pairwise_label_mix', 'pairwise_label_random', 'pairwise_label_uin_hot_random', 'pairwise_weight']
    
    appuin_list = ['appuin_label_random', 'appuin_label_weight']
    
    for scalar_feat in category_feature_list:
        dicts[scalar_feat] = tf.FixedLenFeature(shape=[], dtype=tf.int64)

    for vector_feat in multi_category_feature_list_20:
        dicts[vector_feat] = tf.FixedLenFeature(shape=[10], dtype=tf.int64)   

    for vector_feat in multi_category_feature_list_10:
        dicts[vector_feat] = tf.FixedLenFeature(shape=[10], dtype=tf.int64)   

    for vector_feat in label_list:
        dicts[vector_feat] = tf.FixedLenFeature(shape=[10], dtype=tf.int64)   
        
    for vector_feat in appuin_list:
        dicts[vector_feat] = tf.FixedLenFeature(shape=[5], dtype=tf.int64)   
    
    parsed_features = tf.parse_single_example(example_proto, dicts)
    return parsed_features



def input_fn(file_dir_list, params, is_predict=False):

    data_set = tf.data.TFRecordDataset(file_dir_list)\
                .map(lambda x: parse_function(x, is_predict), num_parallel_calls=3) 

    if is_predict == False:
        data_set = data_set.shuffle(buffer_size=params['batch_size'] * 2)
    
    data_set = data_set.batch(params['batch_size']) \
            .prefetch(params['batch_size'])
    
    iterator = data_set.make_one_shot_iterator()
    features_dict = iterator.get_next()
    
    return features_dict, features_dict



def get_file_list(input_path):
    """
    功能: 得到 hdfs 下面全部的 part-##### 文件
    参数:
        input_path: 路径
    返回:
        文件列表的 list
    """
    file_list = tf.gfile.ListDirectory(input_path)
    print("file_list_len:", len(file_list))
    file_dir_list = []
    for file in file_list:
        file_path = input_path + file
        file_dir_list.append(file_path)

    return file_dir_list





def user_feature_encoder(features, params, mode):
    """ 对输入的 features 进行 encoder, 相当于对 user_features 进行 encode 得到 user_embedding """
    
    """ 解析不同类型的 feature """
    continue_feature_name_list = params['continue_feature']
    category_feature_name_list = params['category_feature']
    locard_multi_category_feature_name_list = params['locard_multi_category_feature']
    user_used_app_and_wxapp_feature_name_list = params['user_used_app_and_wxapp_feature']



    """ 将不同的 feature 进行 embedding, 2 种类别特征的维度都是 [batch_size, feature_embedding_size] 这个 feature_embedding_size 是不同 feature 可能不同的  """
    # continue_feature_list = [features[continue_feature_name] for continue_feature_name in continue_feature_name_list]  # 不进行 embedding, 而是直接拿, 目前这个先不用
    category_feature_embedding_list = [generate_feature_embedding(category_feature_name, features, params, feature_type='category_feature')   for category_feature_name in category_feature_name_list]
    locard_multi_category_feature_embedding_list = [generate_feature_embedding(locard_multi_category_feature_name, features, params, feature_type='locard_multi_category_feature')   for locard_multi_category_feature_name in locard_multi_category_feature_name_list]

    user_used_app_and_wxapp_feature_name_list = [generate_feature_embedding(user_used_app_and_wxapp_feature_name, features, params, feature_type='user_used_app_and_wxapp_feature')   for user_used_app_and_wxapp_feature_name in user_used_app_and_wxapp_feature_name_list]

    
    feature_embedding = merge_diverse_feature(continue_feature_list=user_used_app_and_wxapp_feature_name_list, 
                                    category_feature_embedding_list=category_feature_embedding_list, 
                                    locard_multi_category_feature_embedding_list=locard_multi_category_feature_embedding_list, 
                                    params=params, 
                                    category_feature_method='concat', 
                                    mode=mode,
                                    locard_multi_category_feature_diverse_merge_method=params['locard_multi_category_feature_diverse_merge_method'])
    
    return feature_embedding

def label_encoder(labels, params, mode=tf.estimator.ModeKeys.TRAIN):
    """ 对 label 进行 encode 得到 label_embedding """
    label_name_list = params['label']
    label_weight_list = params['label_weight']


    """ 获取到不同 feature 输入 tensor """ 
    label = labels[label_name_list[0]]   # 现在只有单任务, 只取一个, [batch_size, sequence_size]
    label_weight = labels[label_weight_list[0]]   # 权重, 维度和 label 一样
    

    label_embedding, full_label_embedding_lookup_table = generate_label_embedding(label_name_list[0], labels, params)  # [batch_size, label_sequence_size, label_embedding_size] 这个不同于两种类别特征
    return label, label_weight, label_embedding, full_label_embedding_lookup_table




from tensorflow.python.ops import array_ops
def focal_loss(prediction_tensor, target_tensor, weights=None, alpha=0.25, gamma=2):
    r"""Compute focal loss for predictions.
        Multi-labels Focal loss formula:
            FL = -alpha * (z-p)^gamma * log(p) -(1-alpha) * p^gamma * log(1-p)
                 ,which alpha = 0.25, gamma = 2, p = sigmoid(x), z = target_tensor.
    Args:
     prediction_tensor: A float tensor of shape [batch_size, num_anchors,
        num_classes] representing the predicted logits for each class
     target_tensor: A float tensor of shape [batch_size, num_anchors,
        num_classes] representing one-hot encoded classification targets
     weights: A float tensor of shape [batch_size, num_anchors]
     alpha: A scalar tensor for focal loss alpha hyper-parameter
     gamma: A scalar tensor for focal loss gamma hyper-parameter
    Returns:
        loss: A (scalar) tensor representing the value of the loss function
    """
    sigmoid_p = tf.nn.sigmoid(prediction_tensor)
    zeros = array_ops.zeros_like(sigmoid_p, dtype=sigmoid_p.dtype)
    
    # For poitive prediction, only need consider front part loss, back part is 0;
    # target_tensor > zeros <=> z=1, so poitive coefficient = z - p.
    pos_p_sub = array_ops.where(target_tensor > zeros, target_tensor - sigmoid_p, zeros)
    
    # For negative prediction, only need consider back part loss, front part is 0;
    # target_tensor > zeros <=> z=1, so negative coefficient = 0.
    neg_p_sub = array_ops.where(target_tensor > zeros, zeros, sigmoid_p)
    per_entry_cross_ent = - alpha * (pos_p_sub ** gamma) * tf.log(tf.clip_by_value(sigmoid_p, 1e-8, 1.0)) \
                          - (1 - alpha) * (neg_p_sub ** gamma) * tf.log(tf.clip_by_value(1.0 - sigmoid_p, 1e-8, 1.0))
    return tf.reduce_mean(per_entry_cross_ent)






def model_fn(features, labels, mode, params):
    """
    功能: estimator 的 model_fn 函数
    """

    tf.set_random_seed(2020)

    feature_embedding = user_feature_encoder(features, params, mode)   # 拿到 user_embedding
    
    label, label_weight, label_embedding, full_label_embedding_lookup_table = label_encoder(features, params, mode)   # 从 features 里面拿

    if mode == tf.estimator.ModeKeys.PREDICT:    # predict, 为了拿到 离线验证
        

        feature_embedding = tf.squeeze(feature_embedding)
        
        match_out_full = tf.tensordot(feature_embedding, tf.transpose(full_label_embedding_lookup_table, perm=[1, 0]), axes=1)  # [batch_size, label总数]
        match_top_k_value,  match_top_k_index= tf.nn.top_k(match_out_full, k=params['top_k'])

        predictions = {
                        "uin": features['uin'],
                        "user_embedding": feature_embedding,
                        "label": features[params['label'][0]], 
                        "label_weight": features['pairwise_weight'],
                        "match_top_k_value": tf.nn.sigmoid(match_top_k_value),
                        "match_top_k_index": match_top_k_index,
                        "attention_weight": match_top_k_index
                    }
        export_outputs = {"predictions": tf.estimator.export.PredictOutput(outputs=predictions)}
        return tf.estimator.EstimatorSpec(mode=mode, 
                                            predictions=predictions, 
                                            export_outputs=export_outputs)



    """ 计算 user_embedding 和 label_embedding 的匹配分 """

    with tf.name_scope("user_label_match"):
    
        feature_embedding = tf.expand_dims(feature_embedding, -1, name='expand_dim_for_last_dim')  # [batch_size, hidden_size, 1]
        match_out = tf.matmul(label_embedding, feature_embedding, name='feature_and_labels_match')  # [batch_size, sequence_len, 1]
        match_out = tf.squeeze(match_out, name='sequeeze_last_dim')  # [batch_size, sequence_len]

        label_weight = tf.cast(label_weight, dtype=tf.float32, name='convert_label_weight_to_float')  # [batch_size, sequence_len], 前面是 1 后面是 0, 区分出了正负样本
#         loss = tf.reduce_mean(tf.nn.sigmoid_cross_entropy_with_logits(logits=match_out, labels=label_weight, name='get_multi_class_loss'), axis=1, name='reduce_mean_the_loss')
#         loss = tf.reduce_mean(loss)  # 求一个 batch 的平均
        
        loss = focal_loss(match_out, label_weight)


        """ tag 向量模长约束 """
        label_embedding_square = tf.square(label_embedding, name='get_tag_embedding_square') # [batch_size, seq_len, embedding_size]
        label_embedding_square_sum  = tf.reduce_sum(label_embedding_square, axis=-1, name='get_tag_embedding_square_sum')  # 最后一个维度, # [batch_size, seq_len]
        label_embedding_sqrt = tf.sqrt(label_embedding_square_sum, name='get_tag_embedding_sum_sqrt')   # [batch_size, seq_len]
        ones_tensor = tf.ones_like(label_embedding_sqrt, name='get_ones_tensor')   # [batch_size, seq_len]
        label_embedding_reg_raw = tf.subtract(label_embedding_sqrt, ones_tensor, name='get_tag_mode_len_sub_one')  # [batch_size, seq_len]
        label_embedding_reg_abs = tf.abs(label_embedding_reg_raw)  # [batch_size, seq_len]
        label_embedding_reg = tf.reduce_mean(label_embedding_reg_abs, axis=-1, name='reduce_mean_for_sequence')
        label_embedding_reg = tf.reduce_mean(label_embedding_reg, name='reduce_mean_for_batch')

        loss = tf.add(10 * loss, params['embedding_reg'] * label_embedding_reg, name='add_loss_and_mode_reg')
        

    if mode == tf.estimator.ModeKeys.TRAIN:    # train
#         learning_rate = tf.train.exponential_decay(learning_rate=params['learning_rate'], global_step=tf.train.get_global_step(), decay_steps= 100000, decay_rate=params['learning_rate_decay'])
#         optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate)
        optimizer = tf.train.AdamOptimizer(learning_rate=params['learning_rate'])
        train_op = optimizer.minimize(loss, global_step=tf.train.get_global_step())
    else:         # eval
        train_op = None

    return tf.estimator.EstimatorSpec(
        mode=mode,
        loss=loss,
        eval_metric_ops={},
        train_op=train_op)



def model_estimator(params):

    tf.reset_default_graph()
    
    session_config = tf.ConfigProto(log_device_placement=False, allow_soft_placement=True)
    session_config.gpu_options.per_process_gpu_memory_fraction = 0.45
    session_config.gpu_options.allow_growth = True # 自适应

    config = tf.estimator.RunConfig() \
        .replace( 
                 session_config=session_config,
                 log_step_count_steps=params['log_step_count_steps'],
                 save_checkpoints_steps=params['save_checkpoints_steps'],
                 keep_checkpoint_max=params['keep_checkpoint_max'],
                 save_summary_steps=params['save_summary_steps'])

    model = tf.estimator.Estimator(
        model_fn=model_fn,
        config=config,
        model_dir=params['model_dir'],
        params=params,
    )
    return model







def model_predict(model, predict_input_fn, full_label_embedding, params):
    """
    功能: 模型的预测, 计算各种指标
    参数:
        model: Estimator 对象
        predict_input_fn: 经过 input_fn 后得到的特征迭代器
    """

    """ 获取 label、label_weight、user_embedding  """
    prediction_result = model.predict(predict_input_fn)   # predict 模型中的 predicts, 每一行都是一个字典
    user_embedding_list = []
    label_list = []
    label_weight_list = []
    uin_list = []
    top_k_value_list = []
    similarity_top_k_list = []
    attention_weight_list = []
    
    for item in prediction_result:
        user_embedding_list.append(item['user_embedding'])
        label_list.append(item['label'])
        label_weight_list.append(item['label_weight'])
        uin_list.append(item['uin'])
        top_k_value_list.append(item['match_top_k_value'])
        similarity_top_k_list.append(item['match_top_k_index'])
        attention_weight_list.append(item['attention_weight'])

    """ 利用矩阵相乘计算相似度 """
#     user_embedding_matrix = np.array(user_embedding_list)  # [样本数目, embedding_size]

    """ 选取 top_k, 得到的结果是 [样本数目, k] """
    top_k = params['top_k']        # 这个 k 要取大一些, 因为后面还是会算 hit_3, hit_5, hit_10, hit_30 等


    """ 计算 hit_rate """
    label_matrix = np.array(label_list)
    label_matrix = label_matrix[:, :top_k]   # 每一行只保留前 topk
    label_weight_matrix = np.array(label_weight_list)
    label_weight_matrix = label_weight_matrix[:, :top_k]   # 每一行只保留前 topk
    
    hit_rate_list_1 = []
    hit_rate_list_3 = []
    hit_rate_list_5 = []
    hit_rate_list_10 = []
    hit_rate_list_15 = []
    hit_rate_list_20 = []
    hit_rate_list_30 = []
    hit_rate_list_50 = []
    hit_rate_list_100 = []
    
    for i, pred in tqdm(enumerate(similarity_top_k_list), desc='computer_hit_rate'):
        label = label_matrix[i]
        label_weight = label_weight_matrix[i]
        pos_label_count = sum(label_weight)   # 求和, 拿到正样本数目
        label = label[: pos_label_count]    # 截断至正样本数目
        
        """ 计算 hit_rate """
        recall_1 = recall_N(label, pred, N=1)
        hit_rate_list_1.append(recall_1)
        recall_3 = recall_N(label, pred, N=3)
        hit_rate_list_3.append(recall_3)
        recall_5 = recall_N(label, pred, N=5)
        hit_rate_list_5.append(recall_5)
        recall_10 = recall_N(label, pred, N=10)
        hit_rate_list_10.append(recall_10)
        recall_15 = recall_N(label, pred, N=15)
        hit_rate_list_15.append(recall_15)
        recall_20 = recall_N(label, pred, N=20)
        hit_rate_list_20.append(recall_20)
        recall_30 = recall_N(label, pred, N=30)
        hit_rate_list_30.append(recall_30)
        recall_50 = recall_N(label, pred, N=50)
        hit_rate_list_50.append(recall_50)
        recall_100 = recall_N(label, pred, N=100)
        hit_rate_list_100.append(recall_100)


    
    hit_rate_top_1 = 1.0 * sum(hit_rate_list_1) / len(hit_rate_list_1)
    hit_rate_top_3 = 1.0 * sum(hit_rate_list_3) / len(hit_rate_list_3)
    hit_rate_top_5 = 1.0 * sum(hit_rate_list_5) / len(hit_rate_list_5)
    hit_rate_top_10 = 1.0 * sum(hit_rate_list_10) / len(hit_rate_list_10)
    hit_rate_top_15 = 1.0 * sum(hit_rate_list_15) / len(hit_rate_list_15)
    hit_rate_top_20 = 1.0 * sum(hit_rate_list_20) / len(hit_rate_list_20)
    hit_rate_top_30 = 1.0 * sum(hit_rate_list_30) / len(hit_rate_list_30)
    hit_rate_top_50 = 1.0 * sum(hit_rate_list_50) / len(hit_rate_list_50)
    hit_rate_top_100 = 1.0 * sum(hit_rate_list_100) / len(hit_rate_list_100)


    print("hit_rate_top_1: " + str(hit_rate_top_1))
    print("hit_rate_top_3: " + str(hit_rate_top_3))
    print("hit_rate_top_5: " + str(hit_rate_top_5))
    print("hit_rate_top_10: " + str(hit_rate_top_10))
    print("hit_rate_top_15: " + str(hit_rate_top_15))
    print("hit_rate_top_20: " + str(hit_rate_top_20))
    print("hit_rate_top_30: " + str(hit_rate_top_30))
    print("hit_rate_top_50: " + str(hit_rate_top_50))
    print("hit_rate_top_100: " + str(hit_rate_top_100))

    
    tag_name_to_index = load_pickle("/data/user_embedding/result_save/tag_dict/name_to_new_index.pkl")
    tag_index_to_name = load_pickle("/data/user_embedding/result_save/tag_dict/new_index_to_name.pkl")

    """ 从相似度 matrix 中选出最多的 k 个标签 """

    predict_tag_list = []
    for tag_list in similarity_top_k_list:
        tag_list = tag_list[:20]
        predict_tag_list.extend(list(tag_list))
    
    

    from collections import Counter
    values_counts = Counter(predict_tag_list)

    tag_count_in_pred = dict(values_counts)

    tag_count_in_pred = dict(sorted(tag_count_in_pred.items(),key = lambda x:x[1],reverse = True))

    tag_index_in_pred_list = list(tag_count_in_pred.keys())
    tag_name_in_pred_list = list(map(lambda x: tag_index_to_name.get(x, 'none'), tag_index_in_pred_list))
    tag_count_in_pred_list = list(tag_count_in_pred.values())

    tag_cover_in_pred_list = list(map(lambda x: x/len(similarity_top_k_list), tag_count_in_pred_list))
    
    tag_name_in_pred_count = dict(zip(tag_name_in_pred_list, tag_cover_in_pred_list))
    
    print(len(tag_name_in_pred_count))
    i_d = 1
    for tag_name in tag_name_in_pred_count.keys():
        print(tag_name, tag_name_in_pred_count[tag_name])
        i_d = i_d + 1
        if i_d == 101:
            break
    
    return hit_rate_top_100



def embedding_lookup_for_np(embedding_lookup_table, indices):
    """ 
    embedding_lookup_table: [class_num, embedding_size], nparray 类型
    indices: [None, sequence_len], nparray 类型
    return:
            [None, sequence_len, embedding_size], nparray 类型
    """
    embedding_list = []
    for i, index in enumerate(indices):
        embedding = embedding_lookup_table[index]
        embedding_list.append(embedding)
    return np.array(embedding_list)




def model_early_stop(valid_metric_list, num=3):
    """
    early stopping 
    """
    max_metric = max(valid_metric_list)
    sum_list = len(valid_metric_list)
    if sum_list > 3:
        count_early_stop = 0
        for i in range(num):
            if valid_metric_list[-1 * (i + 1)] < max_metric:
                count_early_stop += 1
        if count_early_stop == num:
            return 1
    return 0




def model_fit(model, params, train_dir, eval_dir, predict_dir):
    """
    功能: model fit
    参数:
        model: 初始化的 estimator 模型
        params: 参数字典
        train_files: 训练数据的文件, 里面是 csv 文件, 是 features + labels
        test_files: 测试数据的文件
    返回:
       estimator 模型文件
    """

    train_files = get_file_list(train_dir)
    eval_files = get_file_list(eval_dir)[:10]
    predict_files = get_file_list(predict_dir)[:10]

#     hook = tf.train.ProfilerHook(save_steps=2000, output_dir= params['model_dir'])
    
    valid_metric_list = []  # pair_wise 下只能写 loss, 如果是 point_wise 再写 auc
    for epoch in range(params['epochs']):
        model.train(input_fn=lambda: input_fn(train_files, params))   
        print("start to eval ----------------------------------")
        results = model.evaluate(input_fn=lambda: input_fn(eval_files, params))  # evaluate 会返回 loss
        print("epoch: " + str(epoch) + " -----  the loss on eval data is: " + str(results['loss']) )
        print("-----------------------------------------------------------")

        predict_input_fn = lambda: input_fn(predict_files, params, is_predict=True)  # predict 只有一个小文件, 直接用 

        """ 这里先拿到全量 label 的 embedding """
        full_label_embedding = model.get_variable_value("weight_matrix/get_label_embedding_lookup_table")   # 这个固定了, 得到 numpy 矩阵

        # 使用筛选的少部分数据进行 predict
        hit_rate_top_100 = model_predict(model, predict_input_fn, full_label_embedding, params)

        valid_metric_list.append(hit_rate_top_100)

        if model_early_stop(valid_metric_list):
            print("early_stop, stop to train")
            model = model_estimator(params)
            params['learning_rate'] = 0.000002
            model_fit(model, params, params['train_dir'], params['eval_dir'], params['predict_dir'])





params = {
    'batch_size': 512,
    'embedding_size': 16,
    'learning_rate': 0.00002,
    'epochs': 200,
    'learning_rate_decay': 0.95,
    'dropout_prob': 0.5,
    'embedding_reg': 0.5,
    
    'mode': "train",

    'train_dir': "/data/user_embedding/tfrecord_data/tfrecord_from_hdfs/new_index/ft_local/train/",
    'eval_dir': "/data/user_embedding/tfrecord_data/tfrecord_from_hdfs/new_index/ft_local/eval/",
    'predict_dir': "/data/user_embedding/tfrecord_data/tfrecord_from_hdfs/new_index/ft_local/test/",

    'locard_multi_category_feature_diverse_merge_method': 'attention',
    'locard_multi_category_method': 'attention',
    'user_used_app_and_wxapp_feature_method': 'mean',
    
    'continue_feature': ['continue_feature'],
    

    'category_feature': ['region_code', 'grade', 'gender', 'age', 'device', 'city_level', 'platform'],


    'category_feature_embedding_size': 32,
    'category_feature_attention_size': 32,
    
    'region_code_feat_num' : 100,
    'region_code_embedding_size':16,
    'region_code_attention_size':16,

    'grade_feat_num' : 10,
    'grade_embedding_size':8,
    'grade_attention_size':8,

    'gender_feat_num' : 4,
    'gender_embedding_size':4,
    'gender_attention_size':4,

    'age_feat_num' : 99,
    'age_embedding_size':16,
    'age_attention_size':16,

    'platform_feat_num' : 6,
    'platform_embedding_size':4,
    'platform_attention_size':4,

    'city_level_feat_num' : 10,
    'city_level_embedding_size':4,
    'city_level_attention_size':4,

    'device_feat_num' : 757,
    'device_embedding_size':32,
    'device_attention_size':32,
    
    
    'locard_multi_category_feature': ['tag_ad_day_7_after_hash', 'tag_ecc_day_360_after_hash', 'tag_app_day_7_after_hash', 'tag_news_day_7_after_hash', 'tag_social_day_7_after_hash'],
    
    'locard_multi_category_feature_feat_num': 50000,
    'locard_multi_category_feature_embedding_size': 64,
    'locard_multi_category_feature_attention_size': 64,
    

    'user_used_app_and_wxapp_feature': ['user_install_app_after_hash', 'user_install_ecc_app_after_hash', 'user_used_ecc_wxapp_after_hash', 'user_used_wxapp_after_hash', 'user_used_wxapp_after_group'], # user_used_wxapp_after_group

    'user_used_app_and_wxapp_feature_feat_num': 50000,
    'user_used_app_and_wxapp_feature_embedding_size': 64,
    'user_used_app_and_wxapp_feature_attention_size': 64,

    
    'user_install_app_after_hash_feat_num': 1000,
    'user_install_app_after_hash_embedding_size': 64,
    'user_install_app_after_hash_attention_size': 64,

    'user_install_ecc_app_after_hash_feat_num': 200,
    'user_install_ecc_app_after_hash_embedding_size': 64,
    'user_install_ecc_app_after_hash_attention_size': 64,

    'user_used_wxapp_after_hash_feat_num': 50000,
    'user_used_wxapp_after_hash_embedding_size': 64,
    'user_used_wxapp_after_hash_attention_size': 64,

    'user_used_ecc_wxapp_after_hash_feat_num': 200,
    'user_used_ecc_wxapp_after_hash_embedding_size': 64,
    'user_used_ecc_wxapp_after_hash_attention_size': 64,

    'user_used_wxapp_after_group_feat_num': 4500,
    'user_used_wxapp_after_group_embedding_size': 64,
    'user_used_wxapp_after_group_attention_size': 64,

    'label': ['pairwise_label_mix'],
    'label_weight': ['pairwise_weight'],
    'pairwise_label_mix_feat_num':21020,
    'pairwise_label_mix_embedding_size':200,
    
    
    'feature_hidden_units': [200],
    'num_cross_layers': 2,


    'log_step_count_steps':200,
    'save_checkpoints_steps':5000,
    'keep_checkpoint_max':6,
    'save_summary_steps':5000,
    
    'top_k': 200,
    
    'model_dir':"/data/user_embedding/model_save/tag/modify_head_tag/new_index_2/"
}




model = model_estimator(params)

model_fit(model, params, params['train_dir'], params['eval_dir'], params['predict_dir'])








