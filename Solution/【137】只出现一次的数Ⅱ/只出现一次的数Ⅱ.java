/*
（1）出现几次的问题，基本都是可以用 Map的
（2）也可以先排序，这样问题就好解决了
（3）利用位操作，想清楚逻辑。
    首先，出现第一次的时候，first 变为 nums[i] , second 还是 0 
    然后，出现第二次的时候，first 变为  0   , second 变为 nums[i]
    最后，出现第三次的时候，first 还是  0   , second 变为  0 
    这样使得出现 3 次的元素经过运算后变为 0 ， 而只出现 1 次的元素存储在 first 中
*/ 


// 利用位操作
class Solution {
    public int singleNumber(int[] nums) {
        int first = 0, second = 0;
        for(int i = 0; i < nums.length ; i++) {
            first = (nums[i] ^ first) & ~second;
            second = (nums[i] ^ second) & ~first;
        }
        return first;
    }
}




// 其他数字出现 k次，一个数字出现 1 次的统一解法。
/*
（1）利用 32维 的数组存储全部数组的每一位的出现次数
（2）找到出现次数不为 k 的倍数的部分
（3）将这部分相加即可得到结果
*/
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        //考虑每一位
        for (int i = 0; i < 32; i++) {
            int count = 0;
            //考虑每一个数
            for (int j = 0; j < nums.length; j++) {
                //当前位是否是 1
                if ((nums[j] >>> i & 1) == 1) {
                    count++;
                }
            }
            //1 的个数是否是 3 的倍数
            if (count % 3 != 0) {
                result = result | 1 << i;
            }
        }
        return result;
    }
}








// 利用 Map 和位操作，其实也可以只利用 Map，但是多了一趟循环
class Solution {
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        HashMap<Integer , Integer> hashmap = new HashMap<>();  // value 是出现的次数
        int result = 0;
        for(int i = 0 ; i < nums.length ; i ++){
            if(hashmap.containsKey(nums[i]) == false){
                hashmap.put(nums[i] , 1);
                result = result ^ nums[i];
            }
            else{
                if(hashmap.get(nums[i]) == 1){
                    hashmap.put(nums[i] , 2 );
                    result = result ^ nums[i];
                }
                
            }
        }
        return result;
    }
}