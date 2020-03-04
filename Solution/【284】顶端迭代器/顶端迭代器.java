/*
（1）因为在 peek() 的时候也要取元素，所以可以设计一个 cache 存储
（2）如果 cache 等于 null，则说明没有使用 peek()
（3）如果 cache 不等于 null，则说明使用了 peek()
（4）当 cache == null 的时候，再使用 next() 的时候需要将 cache 中的元素输出，而且清空 cache
*/




// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer cache = null;

	public PeekingIterator(Iterator<Integer> iter) {
	    // initialize any member here.
	    iterator = iter;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(cache == null){
            cache = this.iterator.next();
        }
        return cache;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if(cache == null){
            return this.iterator.next();
        }
        else{
            Integer temp = cache;
            cache = null;
            return temp;
        }
	}

	@Override
	public boolean hasNext() {
	    return cache != null || iterator.hasNext();
	}
}