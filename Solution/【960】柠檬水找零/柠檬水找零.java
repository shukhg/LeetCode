/*
（1）每次将 5元 和 10元存起来
（2）遍历，判断手中 5元和 10元的数量
*/



class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for(int b : bills){
            if(b == 5) five ++;
            else if(b == 10) {
                if(five-- == 0) return false;
                ten++;
            }
            else {
                if(five > 0 && ten > 0){
                    five--; ten--;
                }
                else if(five > 2){
                    five -= 3;
                }
                else return false;
            }
        }
        return true;
    }
}