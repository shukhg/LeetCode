/*
*  想清楚里面一步步的逻辑
*/



class Solution {
    public String convert(String s, int numRows) {
        int length = s.length();
        if(length==0||length<=numRows||numRows==1){
			return s;
		}
		int gap = numRows-2;
		String ans = "";
		int k1 = numRows -1 + gap + 1;
		for(int i=0;i<numRows;i++){
			int location = 0;
			if(i==0||i==numRows-1){
				ans = ans+s.charAt(i);
				location = i+k1;
				while(location<length){
					ans = ans+s.charAt(location);
					location = location + k1;
				}
			}else{
				ans = ans + s.charAt(i);
				int k2 = k1 - i*2;
				location  = location + k2 + i;
				while(location<length){
					ans = ans + s.charAt(location);
					k2 = k1 - k2;
					location  = k2 + location;
				}
			}
		}	
		return ans;
    }
}