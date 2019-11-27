




class Solution {
public:
    int maxArea(vector<int>& height) {
        if(height.empty() || height.size() == 0){
            return 0;
        }
        int result = 0;
        int left = 0;
        int right = height.size() - 1;
        while(left <= right){
            int area = min(height[left], height[right]) * (right - left);
            if(area > result){
                result = area;
            }
            if(height[left] < height[right]){
                left ++;
            }
            else{
                right -- ;
            }
        }
        return result;
    }
};


