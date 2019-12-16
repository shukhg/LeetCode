/*
（1）对于任意位置 i，以其位置为基础的最大矩形面积与其左右两边小于该位置高度的 left 和 right 有关，所以暴力方法就是从左到右逐步遍历
（2）将数组首尾填充 0，后续计算要方便很多
（3）可以维持一个单调栈，遇到更小的元素就出栈直到小于该元素。
（4）出栈的时候，则以出栈的元素为中心，栈顶元素为 left，当前元素为 right，即可实现（1）中的方法，这也是两端添加 0 的原因
*/




class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int result = 0;
        Stack<Integer> stack = new Stack<>();   // 维持一个单调栈，遇到更小的元素就出栈
        int[] new_heights = new int[heights.length + 2];
        for(int i = 1; i < heights.length + 1; i ++){   // 首尾填充 0 
            new_heights[i] = heights[i - 1];
        }
        for(int i = 0; i < new_heights.length; i ++){
            while(!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]){  // 当前元素小于栈顶索引对应的元素，则出栈
                int cur = stack.pop();
                result = Math.max(result, (i - stack.peek() - 1) * new_heights[cur]);
            }
            stack.push(i);
        }
        return result;
    }
}

