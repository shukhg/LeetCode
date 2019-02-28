/*
（1）想清楚 if 语句中的逻辑，尤其是 nums[i] != nums[nums[i] - 1]。要保证交换前后不等，如果相等的话，两个相同的数一直交换，会造成死循环
（2）发生交换的话，不需要 i++的。以后记住这种交换都是发生交换就不需要 i++
*/

public class Solution {
    public int firstMissingPositive(int[] nums) {
		int len = nums.length;
		int i = 0;
        while( i < len ){                         //小于数组长度 并且 此元素不等于交换后位置的元素 
			if(nums[i] != i+1 && nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i]-1] ){  
                                                             //如果 nums[i]不在指定位置并且大于0 并且
				swap(i,nums[i]-1,nums);                    // 把 nums[i] - 1  位置的元素 与 i 位置的元素交换
			}
            else{
                i ++;
            }
		}
		for(int j = 1; j <= len; j++){      //正数，所以从0 开始  
			if(nums[j - 1] != j){
				return j;
			}
		}
		return len+1;
    }
	
	public void swap(int i, int j, int[] nums) {   //要传入 nums数组，不然无法实现数组的变化
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}