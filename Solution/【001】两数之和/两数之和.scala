import scala.collection.mutable

object Solution {
    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
        if(nums == null || nums.length == 0){
            return 0;
        }
        val hashmap: mutable.HashMap[Int, Int] = mutable.HashMap()
        for (i <- 0 until nums.length){
            val div = target - nums(i)
            if(hashmap.contains(div)){
                return Array(hashmap(div), i)
            }
            else{
                hashmap(nums(i)) = i
            }
        }
        return Array(-1, -1)
    }
}