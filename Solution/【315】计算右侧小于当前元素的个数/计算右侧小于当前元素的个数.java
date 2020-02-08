/*
（1）利用归并排序，假设左右两边都已经排序好+计算好，将两个数组进行 merge 的时候，统计 左边数组出列的元素（切记是出列的元素 而且 要是左边的数组）的逆序数
（2）需要要一个 indexes、temp 存储排序过程中的 索引，temp 是复制 indexes 的，counter 存储结果
（3）设 i 是左边的指针，j 是右边的指针，i 和 j 的状态有以下四种
（4）如果 i 超过了 mid，也就是 左边数组都出列完了，而右边剩下的数组都比左边大，出列右边的元素，也就没有逆序数
（5）如果 j 超过了 right，也就是 右边数组都出列完了，而左边剩下的都是比右边大的，所以左边剩下的出列的时候，其逆序数的个数就是右边数组的全部
（6）如果 i 对应的元素 <= j 对应的元素，即两把都没用完，则 j 左边的 j - mid - 1 个元素都比 i 对应的元素小，构成逆序对
（7）如果 i 对应的元素 > j 对应的元素，这个时候出队的不是左边的元素，没有逆序数
*/



class Solution {
    private int[] temp;
    private int[] counter;
    private int[] indexes;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        temp = new int[len];
        counter = new int[len];
        indexes = new int[len];
        for (int i = 0; i < len; i++) {
            indexes[i] = i;
        }
        mergeAndCountSmaller(nums, 0, len - 1);
        for (int i = 0; i < len; i++) {
            res.add(counter[i]);
        }
        return res;
    }

    /**
     * 针对数组 nums 指定的区间 [l, r] 进行归并排序，在排序的过程中完成统计任务
     *
     * @param nums
     * @param l
     * @param r
     */
    private void mergeAndCountSmaller(int[] nums, int l, int r) {
        if (l == r) {
            // 数组只有一个元素的时候，没有比较，不统计
            return;
        }
        int mid = l + (r - l) / 2;
        mergeAndCountSmaller(nums, l, mid);
        mergeAndCountSmaller(nums, mid + 1, r);
        // 归并排序的优化，同样适用于该问题
        // 如果索引数组有序，就没有必要再继续计算了
        if (nums[indexes[mid]] > nums[indexes[mid + 1]]) {   // mid > mid + 1 则未全部有序
            mergeOfTwoSortedArrAndCountSmaller(nums, l, mid, r);
        }
    }

    /**
     * [l, mid] 是排好序的
     * [mid + 1, r] 是排好序的
     *
     * @param nums
     * @param l
     * @param mid
     * @param r
     */
    private void mergeOfTwoSortedArrAndCountSmaller(int[] nums, int l, int mid, int r) {
        // 3,4  1,2
        for (int i = l; i <= r; i++) {
            temp[i] = indexes[i];
        }
        int i = l;
        int j = mid + 1;
        // 左边出列的时候，计数
        for (int k = l; k <= r; k++) {
            if (i > mid) {     // 左边的都遍历完了，没有逆序对
                indexes[k] = temp[j];
                j++;
            } else if (j > r) {   // 右边的全部用完，逆序对个数为 右边区间的长度
                indexes[k] = temp[i];
                i++;
                // 此时 j 用完了，[7,8,9 | 1,2,3]
                // 之前的数就和后面的区间长度构成逆序
                counter[indexes[k]] += (r - mid);
            } else if (nums[temp[i]] <= nums[temp[j]]) {   // 两边都没用完，且 左边指针元素 < 右边指针元素
                indexes[k] = temp[i];            //  j 左边的 j - mid - 1 个元素都比 i 的元素小，构成逆序对
                i++;
                // 此时 [4,5, 6   | 1,2,3 10 12 13]
                //           mid          j
                counter[indexes[k]] += (j - mid - 1);
            } else {       // 两边都没用完，且 左边指针元素 > 右边指针元素，但只在出列的时候计算，所以此处不计算
                indexes[k] = temp[j];
                j++;
            }
        }
    }
}
