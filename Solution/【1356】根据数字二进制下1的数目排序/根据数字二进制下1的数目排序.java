/*
（1）方法很容易想到，就是写一个统计二进制中1 的个数的函数，然后计算数组中每个元素的 二进制 1 的个数
（2）直接根据二进制 1 的个数进行排序，实现 Comparator 接口
（3）Comparator 和 compare 中必须是 引用类型，比如 Integer，而不能直接对原先的 int[] 进行排序
（4）注意 sort 实现 Comparator 和 PriorityQueue 实现 Comparator 的不同，PriorityQueue 是 Integer 类型，而 sort 传入的是 int 类型
（5）将 int数组 变成 Integer数组 的快捷方法  Integer[] result = Arrays.stream(arr).boxed().toArray(Integer[]::new)
（6）将 Integer数组 变成 int数组 的快捷方法  int[] temp = Arrays.stream(result).mapToInt(Integer::valueOf).toArray()
*/







import java.util.Arrays;
class Solution {
    public int[] sortByBits(int[] arr) {
		Integer[] result = Arrays.stream(arr).boxed().toArray(Integer[]::new);
		Arrays.sort(result, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
                if(getNum(a) == getNum(b)) return a-b;
				return getNum(a) - getNum(b);
			}
		});
		return Arrays.stream(result).mapToInt(Integer::valueOf).toArray();
	}

	public int getNum(int value) {
		int count = 0;
		while (value > 0) {
			value = value & (value - 1);
			count++;
		}
		return count;
	}
}