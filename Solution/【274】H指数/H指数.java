/*
（1）如果数组是从大到小排序，那么所求的结果就是数组图像左边的一个正方形
（2）先将数组进行排序即可
*/



class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0){
            return 0;
        }
        Arrays.sort(citations);  // 排序的结果是从小到大
        int result;
        for(int i = 0; i < citations.length; i ++){
            result = citations.length - i;
            if(result <= citations[i]){
                return result;
            }
        }
        return 0;
    }
}