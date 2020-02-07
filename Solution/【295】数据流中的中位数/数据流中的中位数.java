/*
（1）最简单的办法是新输入一个元素后都重新进行一次排序，但是这在大数据情况下很难实现
（2）其实我们只关心中间的那两个数，没必要用全部的数进行排序，所以可以用 最大堆+最小堆，但是关键问题是如何设计规则使得两个堆相互平衡
（3）我们要保证的是 小顶堆中的元素 全部大于 大顶堆中的元素
（4）如果当前元素是奇数（也可以反过来），则把元素加入大顶堆中，拿到大顶堆重新调整后的最大元素，将其加入小顶堆
（5）如果当前元素是偶数（也可以反过来），则把元素加入小顶堆中，拿到小顶堆重新调整后的最小元素，将其加入大顶堆
（6）上面的两个过程实现了 两个堆的平衡，其数据量差距不超过 1
（7）PriorityQueue 默认是 小顶堆，如果需要 大顶堆 的话，则实现 Comparator 接口
*/



class MedianFinder {
    int count = 0;
    PriorityQueue<Integer> minHeap;  
    PriorityQueue<Integer> maxHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();

        // PriorityQueue 默认是小顶堆，实现大顶堆，需要反转默认排序器
        maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>(){  
            public int compare(Integer o1 , Integer o2){
                return o2 - o1;
            }
        });
    }
    
    public void addNum(int num) {
        count ++;

        if( (count % 2) == 0 ){  //当数据总数为偶数时，新加入的元素，应当进入小根堆
                             //（注意不是直接进入小根堆，而是经大根堆筛选后取大根堆中最大元素进入小根堆）

            
            maxHeap.offer(num);       // 新加入的元素先入到大根堆，由大根堆筛选出堆中最大的元素（自动完成）
            num = maxHeap.poll();
            minHeap.offer(num);          // 筛选排序后的【大根堆中的最大元素】进入小根堆
        }
        else{             //当数据总数为奇数时，新加入的元素，应当进入大根堆（自动完成）
                    //（注意不是直接进入大根堆，而是经小根堆筛选后取小根堆中最大元素进入大根堆）
             
            minHeap.offer(num);  // 新加入的元素先入到小根堆，由小根堆筛选出堆中最小的元素
            num = minHeap.poll();
            maxHeap.offer(num);   // 筛选后的【小根堆中的最小元素】进入大根堆
        }
         //   count ++;   如果在这个位置，最后的 GetMedian 中count为奇数的时候是 result = minHeap.peek()  
    }
    
    public double findMedian() {
        double result;
        if((count % 2) == 1)
            result = maxHeap.peek();
        else
            result = ( minHeap.peek() + maxHeap.peek() ) / 2.0;
        return result;
    }
}