/*
    先填充最左边和最右边，再处理中间
*/



class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if(numRows == 0 )
            return result;
        
        List<Integer> list_1 = new ArrayList<>();   // 先处理第一行
            list_1.add(1);
            result.add(list_1);
        if(numRows == 1){
            return result ;
        }
        
        for(int i = 1 ; i < numRows ; i ++){
            List<Integer> list = new ArrayList<>();
            list.add(1);        // 最左边
            List<Integer> temp = result.get(i - 1);
            for(int j = 1 ; j < i  ; j ++){
                list.add(temp.get( j - 1) + temp.get(j));
            }
            list.add(1);        //  最右边 
            result.add( new ArrayList<Integer>(list));
        }
        return result ;
    }
}