/*
（1）肯定一开始要用 hashmap 统计次数，接下来的想法是可以根据出现次数排序，选出出现次数最高的几个元素
（2）但是怎么对 map 中的 value 进行排序呢？
（3）因为我们只需要拿 topk 个就够了，所以可以利用优先队列，一般 topk 都应该想到优先队列
（4）优先队列中有个 compare 函数要实现，我们所需要的功能是根据 key 选择出 value 排 topk 的元素，
    所以 compare 是用 map.get(key1) map.get(key2) 进行比较，以实现对 map.keySet() 的排序
（5）优先队列的方法是 size()、add(key)、remove()、isEmpty()、peek() 和 普通的 队列几乎差不多，只是 poll 换成了 remove 
*/



class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
               map.put(num, map.get(num) + 1);
             } else {
                map.put(num, 1);
             }
        }
        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {   // 新的 compare 实现 map.keySet() 中元素的比较
                return map.get(a) - map.get(b);
            }
        });
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } 
            else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }
}