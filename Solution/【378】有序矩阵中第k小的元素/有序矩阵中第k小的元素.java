/*
（1）利用二维矩阵中的数进行二分查找，最小的数 left，最大的数 right，那么第k小的数必定在left~right之间。注意这里不是用索引，而是直接用值
（2）每次统计二维矩阵中小于等于 mid 的元素个数 count
（3）如果这个 count 小于 k，表明第 k 小的数在右半部分且不包含 mid，即 left=mid+1, right=right，又保证了第k小的数在left~right之间
（4）如果这个 count 大于 k，表明第 k 小的数在左半部分且可能包含 mid，即 left=left, right=mid，又保证了第k小的数在left~right之间
（5）为啥没有 count == k 直接返回 mid 呢？因为 mid 是值的一半，而不是索引的一半，所以可能 mid 在矩阵中并不存在
（6）在进行二分查找的时候，如果需要找到一个确切的位置，可以使用 left < right（同时初始化 right = num.length），这样返回的时候 left 和 right 都在同一个位置了
（7）为什么保证找到的值一定在矩阵中呢？只要保证第 k 小的数在 left 和 righ t之间，不停缩小 left 和 right 直到 left==right 时，第 k 小的数就是 left 也就是 right
*/




class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length - 1][matrix[0].length - 1];
        while(left < right){
            int mid = (left + right) / 2;
            int count = searchSmallerThanMid(matrix, mid);
            if(count < k){  // 在 mid 右边且不包含 mid
                left = mid + 1;
            }
            else{    // 在 mid 左边，可能包含 mid
                right = mid;
            }
        }
        return right;
    }
    public int searchSmallerThanMid(int[][] matrix, int mid){  // 找到小于等于 mid 的元素的个数
        int count = 0;
        int row = matrix.length - 1, col = 0;  // 从左下角开始
        while(row >= 0 && col < matrix[0].length){
            if(matrix[row][col] <= mid){
                count = count + row + 1;   // 当前列上方的元素肯定比 mid 小
                col ++;
            }
            else{   // 当前元素大于 mid 但是此列上面的元素可能比 mid 小，所以往上走
                row --;
            }
        }
        return count;
    }
}