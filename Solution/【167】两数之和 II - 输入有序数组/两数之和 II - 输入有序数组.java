/*
简单的双指针
*/



class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int index1 = 0;
        int index2 = 0;
        int[] ans =  new int[2];
        int i = 0 ;
        int j = numbers.length - 1;
        while(i <= j){
            if(numbers[i] + numbers[j] == target && i != j){
                ans[0] = i + 1;
                ans[1] = j + 1;
            }
            if(numbers[i] + numbers[j] < target)  i++;
            else  j --;
        }
        return ans;
    }
}