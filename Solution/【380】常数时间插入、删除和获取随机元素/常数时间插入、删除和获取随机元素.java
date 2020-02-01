/*
（1）O(1)解法，组合使用哈希表和数组
（2）插入时：用哈希表来判断是否已存在 O(1)，数组末尾增加一个元素 O(1)，哈希表记录｛值：索引｝O(1)
（3）删除时：用哈希表来定位 O(1)，根据定位将数组中的被删除元素用一个常量代替 O(1)，更新哈希表 O(1)
（4）取随机数时：随机从数组里面挑一个 O(1)

*/



class RandomizedSet {
    HashMap<Integer, Integer> map;
    List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer,Integer>();
        list = new ArrayList<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val)){
            map.put(val,list.size());
            list.add(val);
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            int index = map.remove(val);
            list.set(index, Integer.MIN_VALUE);
            return true;
        }
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rm = new Random();
        int i = rm.nextInt(list.size());
        while(list.get(i) == Integer.MIN_VALUE){
            i = rm.nextInt(list.size());
        }
        
        return list.get(i);
    }
}