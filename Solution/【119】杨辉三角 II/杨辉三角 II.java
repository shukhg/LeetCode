/*
利用一个数组存储数字，比用list 效率高而且操作方便。
想清楚内、外两层循环的循环次数以及跳出的条件。
内层循环从右到左，因为只有从右到左才可以实现 nums[j] = nums[j] + nums[j-1] 
*/




class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        if(rowIndex == 0)   return result;
        for(int i = 1; i <= rowIndex; i ++){
            for(int j = i - 1; j > 0; j --){
                result.set(j, result.get(j) + result.get(j - 1));
            }
            result.add(1);
        }
        return result;
    }
}



class Solution {
    public List<Integer> getRow(int rowIndex) {
        int[] nums = new int[rowIndex + 1];
        nums[0] = 1;
        for(int i = 1 ; i < rowIndex + 1 ; i ++){
            for(int j = i ; j >= 1 ; j --){            // 此处一定要从右往左边处理  
                nums[j] = nums[j] + nums[j - 1];
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < rowIndex + 1 ; i ++)
            result.add(nums[i]);
        return result ;
    }
}


