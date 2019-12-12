/*
（1）从右边往左边找到第一个非递增的元素。如果没有找到，则全部反转
（2）从右边往左边找到第一个大于第一个的元素
（3）交换找到的两个元素的位置
（4）将第一个元素 右边的元素全部反转
*/





class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        if(nums.empty() || nums.size() == 0){
            return ;
        }
        int first_index = -1;
        for(int i = nums.size() - 2; i >=0; i --){  // 从右往左找 第一个不递增的
            if(nums[i] < nums[i + 1]){
                first_index = i;
                break;
            }
        }
        if(first_index == -1){       // 如果不存在，就整个序列全部反转
            reverse(nums, 0, nums.size() - 1);
            return ;
        }
        int second_index = -1;        // 从右往左，找到 大于 前面找到的元素的第一个元素
        for(int i = nums.size() - 1; i >= 0; i --){
            if(nums[i] > nums[first_index]){
                second_index = i;
                break;
            }
        }
        swap(nums, first_index, second_index);         // 交换两个元素的位置
        reverse(nums, first_index + 1, nums.size() - 1);     // 将 第一个元素右边的全部元素反转
        return ;
    }
    void swap(vector<int>& nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    void reverse(vector<int>& nums, int left, int right){
        while(left < right){
            swap(nums, left, right);
            left ++;
            right --;
        }
    }
};