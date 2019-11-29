



class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> result;
        if(n <= 0){
            return result;
        }
        string str = "";
        dfs(result, str, n, n);
        return result;

    }
    void dfs(vector<string>& result, string str, int left, int right){
        if(left == 0 && right == 0){
            result.push_back(str);
        }
        if(left > right){
            return;
        }
        if(left != 0){
            dfs(result, str + "(", left - 1, right);
        }
        if(right != 0){
            dfs(result, str + ")", left, right - 1);
        }
    }
};
