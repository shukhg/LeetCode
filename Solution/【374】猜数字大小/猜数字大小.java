/*
就是简单的二分查找，注意理解题目的意思就可以了
*/

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        int mid ;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(guess(mid) == -1){
                right = mid - 1;
            }
            else if(guess(mid) == 1){
                left = mid + 1;
            }
            else{
                return mid;
            }
        }
        return 0;
    }
}