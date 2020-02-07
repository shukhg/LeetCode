/*
（1）暴力法思路很简单，每次遍历当前窗口，选出当前窗口内最小的一个元素即可，时间复杂度为 O(nk)
（2）如何优化时间复杂度呢？首先想到的是使用堆，在大小为 k 的堆中插入一个元素消耗 log(k) 时间，因此算法的时间复杂度为 O(Nlog(k))
（3）我们可以使用双向队列，该数据结构可以从两端以 常数时间 压入 和 弹出元素
（4）双端队列中放入的是元素的索引，以后遇到的 最小栈 也都是放索引
（5）双端队列从 队尾->队首 是 小->大
（6）如果当前元素小于队尾的元素，就把队尾的元素出队，直到符合要求
（7）队首的元素即为当前窗口内的最大值。但是，要注意队首是否不在窗口范围内，每次循环的时候判断队首元素是否在范围内 if(queue.peek() <= i - k)  注意这里是 i-k 需要理解
（8）双端队列的方法：addFirst()、addLast()、pollFirst()、pollLast()、peekFirst()、peekLast()
*/




class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        int[] result = new int[nums.length - k + 1];      // 结果数组
        for(int i = 0; i < nums.length; i ++){
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            queue.addLast(i);   // 往双端队列中添加当前值对应的数组下标
            if(queue.peekFirst() <= i - k){    // 判断当前队列中队首的值是否有效
                queue.pollFirst();   
            } 
            if(i + 1 >= k){    // 当窗口长度为k时 保存当前窗口中最大值
                result[i + 1 - k] = nums[queue.peekFirst()];
            }
        }
        return result;
    }
}