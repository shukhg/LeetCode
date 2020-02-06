/*
使用Map储存"文件内容"->"对应的文件路径"，有相同文件内容的路径存入List，最后遍历Map vaule，判断List.size()>1，如果>1则表示有重复文件
（1）使用 hashmap 存储 "文件内容"-> "文件路径 的 List"
（2）如果遇到相同的内容，就在 "文件路径的 List" 中添加一个新的路径
（3）注意 split 对 '(' 进行操作的时候需要 split("\\(")
*/



class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> result = new ArrayList<>();
        if(paths.length == 0) return result;
        HashMap<String, List<String>> map = new HashMap<>();   //储存 "文件内容"->对应的文件路径
        for(String path : paths){
            //路径、文件、文件
            String[] fields = path.split(" ");
            String directortPath = fields[0];
            for(int i = 1;i < fields.length;i++){
                // 1.txt abcd)
                String[] strs = fields[i].split("\\(");
                //文件内容
                String content = strs[1].substring(0, strs[1].length()-1);  // 除去最后的 )
                String totalPath = directortPath + "/" + strs[0];
                List<String> totalPathList = map.get(content);
                if(totalPathList == null)
                    totalPathList = new ArrayList<>();
                //放入map
                totalPathList.add(totalPath);
                map.put(content, totalPathList);
            }
        }
        //遍历map，获取结果
        for (List<String> list : map.values()) {
            //表示有重复文件
            if(list.size() > 1)
                result.add(list);
        }
        return result;
    }
}