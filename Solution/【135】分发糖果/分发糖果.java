/*
（1）要满足相邻的孩子中评分最高的孩子获得更多的糖果，也就是满足两个条件
    左规则：ratings[B] > ratings[A] 时，B 的糖果比 A 多
    右规则：ratings[A] > ratings[B] 时，A 的糖果比 B 多
    相邻的学生中，评分高的学生必须获得更多的糖果 等价于 所有学生满足左规则且满足右规则
（2）left 数组初始化全1，然后从左到右遍历，如果满足左规则那就 +1，否则不变
（3）right 数组初始化也为全1，从右往左遍历，满足右规则也 +1
（4）每个位置的分配糖果数目是 左右规则的最大值
*/







class Solution {
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for(int i = 1; i < ratings.length; i ++){
            if(ratings[i] > ratings[i - 1]){
                left[i] = left[i - 1] + 1;
            }
        }
        int count = left[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i --){
            if(ratings[i] > ratings[i + 1]){
                right[i] = right[i + 1] + 1;
            }
            count = count + Math.max(left[i], right[i]);
        }
        return count;
    }
}

