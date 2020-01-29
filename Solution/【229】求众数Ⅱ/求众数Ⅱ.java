/*
（1）对比多数投票法（某个元素出现次数大于一半）中每次消除两个元素时，多数元素 还会是最多的。可以应用到此处，每次消除三个元素，超过 1/3 次数的元素还会超过 1/3
（2）所以也就有 candidateA、candidateB 和 countA、countB，新元素等于某个候选数字的时候就将相应的 count ++
（3）如果出现非候选的新数字，则一次性消除三个，也就是 countA --, countB --
（4）因为出现次数大于 1/3 的元素可能有一个也可能有两个，最后还需遍历一次确定 candidateA 和 candidateB 的出现次数是否大于 1/3
*/




class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0)    return result;
        int candidateA = nums[0];
        int candidateB = nums[0];
        int countA = 0;
        int countB = 0;
        for(int i = 0; i < nums.length; i ++){
            int num = nums[i];
            if(num == candidateA){
                countA ++;
                continue;
            }
            if(num == candidateB){
                countB ++;
                continue;
            }
            if(countA == 0){   // 此时当前值和AB都不等，检查是否有票数减为0的情况，如果为0，则更新候选人
                candidateA = num;
                countA = 1;
                continue;
            }
            if(countB == 0){
                candidateB = num;
                countB = 1;
                continue;
            }
            countA --;   // 若此时两个候选人的票数都不为0，且当前元素不投AB，那么A,B对应的票数都要--;
            countB --;
        }
        countA = 0;
        countB = 0;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == candidateA){
                countA ++;
            }
            else if(nums[i] == candidateB){
                countB ++;
            }
        }
        if(countA > nums.length / 3){
            result.add(candidateA);
        }
        if(countB > nums.length / 3){
            result.add(candidateB);
        }
        return result;
    }
}