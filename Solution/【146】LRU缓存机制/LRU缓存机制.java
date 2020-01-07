/*
（1）要让 get 的时间复杂度为 O(1)，则肯定是需要 hashmap；要让 put 的时间复杂度为 O(1)，则要使用 双向链表，拆入删除快。所以可以结合起来，使用 双向链表 + 哈希表。
（2）LRU 缓存算法的核心数据结构就是哈希链表，双向链表和哈希表的结合体
（3）自定义双向链表的结点类 以及 双向链表的方法（链表头部添加节点x、删除链表中x节点、删除链表最后一个节点并返回）
（4）LRU 的 get 方法就是判断 map 中是否存在 key，获取元素后 重新进行 put（会删去旧的节点，并且将新的结果拆入的头部）
（5）LRU 的 put 方法就是 删除旧的节点，并且将新的节点拆入到头部（此处要注意容量）
*/



class LRUCache {
    class Node{
        public int key, val;
        public Node next, prev;
        public Node(int k, int v){
            this.key = k;
            this.val = v;
        }
    }

    class DoubleList {  
        private Node head, tail; // 头尾虚节点
        private int size; // 链表元素数

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // 在链表头部添加节点 x
        public void addFirst(Node x) {
            x.next = head.next;
            x.prev = head;
            head.next.prev = x;
            head.next = x;
            size++;
        }

        // 删除链表中的 x 节点（x 一定存在）
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }
        
        // 删除链表中最后一个节点，并返回该节点
        public Node removeLast() {
            if (tail.prev == head)
                return null;
            Node last = tail.prev;
            remove(last);
            return last;
        }
        
        // 返回链表长度
        public int size(){
            return size; 
        }
    }
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DoubleList cache;
    // 最大容量
    private int cap;


    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }
    
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }
    
    public void put(int key, int value) {
        // 先把新节点 x 做出来
        Node x = new Node(key, value);
        
        if (map.containsKey(key)) {
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
            cache.addFirst(x);
            // 更新 map 中对应的数据
            map.put(key, x);
        } else {
            if (cap == cache.size()) {
                // 删除链表最后一个数据
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            // 直接添加到头部
            cache.addFirst(x);
            map.put(key, x);
        }
    }
}