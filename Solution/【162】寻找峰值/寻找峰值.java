/*
（1）当中间元素大于其相邻的后续元素时，说明中间元素左侧（包含中间元素）必然包含一个局部最大值
    这时，中间元素也可能是峰值点，所以移动时，end = mid, 而不能跨过中间元素end = mid -1
    而相反情况，中间元素小于其向相邻后续元素，则中间元素右侧比包含一个局部最大值
    这时候中间元素一定不是局部最大点，start = mid + 1.
（2）我们选择左右边沿相遇作为结束条件，以及只和后续元素mid+1比较
    可以避免考虑当mid为0时，mid-1为负值的特殊情况
    题目中只是让我们假设num[-1] = num[n] = 负无穷
    实际上，数组是无法得到这两个值的，指针会越界。
*/


class Solution {
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while(left <= right){
            if(left == right)
                return left;
            mid = (left + right) / 2;
            if(nums[mid] < nums[mid + 1]){   // 则左边（包括mid）一定包含峰值
                left = mid + 1;
            }
            else{            // 右边（不包括mid）一定包含峰值
                right = mid;
            }
        }
        return 0;
    }
}