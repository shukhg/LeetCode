/*
（1）实现 Comparator 接口，自定义一个比较的函数
（2）比如两个数字，我们要得到的是 [a, b] 和 [b, a] 的大小关系
（3）利用字符串的 compareTo()方法，比较 a + b 和 b + a
*/


class Solution {
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }
    public String largestNumber(int[] nums) {
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(asStrs, new LargerNumberComparator());

        if (asStrs[0].equals("0")) {
            return "0";
        }
        // Build largest number from sorted array.
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }
        return largestNumberStr;
    }
}
