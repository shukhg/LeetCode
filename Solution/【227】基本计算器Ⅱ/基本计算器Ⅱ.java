/*
（1）先将中缀表达式转化为后缀表达式，再计算后缀表达式的值
（2）中缀转后缀：
    - 数字直接输出到后缀表达式
    - 栈为空时，遇到运算符，直接入栈
    - 遇到运算符，弹出所有优先级大于或等于该运算符的栈顶元素，并将该运算符入栈
    - 将栈中元素依次出栈
（3）后缀表达式计算，这个简单
（4）Character.isDigit(char ch) 可以快速判断是否为数字
*/




public class Solution{
    public int calculate(String exp) {
		return calRst(backTempExp(exp));
	}
// 计算后缀表达式
	public static LinkedList<String> backTempExp(String exp) {

		Stack<String> stkEles = new Stack<String>();
		LinkedList<String> tempBackExp = new LinkedList<String>();
		for (int i = 0; i < exp.length(); i++) {
			// 1.遇到了数字
			if (Character.isDigit(exp.charAt(i))) {
				// 注意多位数的获取
				int k = i + 1;
				for (; k < exp.length() && Character.isDigit(exp.charAt(k)); k++) {

				}
				tempBackExp.add(exp.substring(i, k));
				i = k - 1; // 更新 i
				continue;
			}
			// 2.遇到了乘除运算符
			if (exp.charAt(i) == '/' || exp.charAt(i) == '*') {

				while (!stkEles.isEmpty() && (stkEles.lastElement().equals("/") || stkEles.lastElement().equals("*"))) {
					tempBackExp.add(stkEles.pop()); // 弹出优先级相同或以上的栈内运算符
				}
				stkEles.add(String.valueOf(exp.charAt(i))); // 运算符入栈
				continue;
			}
			// 3.遇到了加减运算符
			if (exp.charAt(i) == '+' || exp.charAt(i) == '-') {
				while (!stkEles.isEmpty() && !isNumeric(stkEles.lastElement())) {
					tempBackExp.add(stkEles.pop()); // 弹出优先级相同或以上的栈内运算符
				}
				stkEles.add(String.valueOf(exp.charAt(i))); // 运算符入栈
				continue;
			}
		}
		// 4.最后弹出栈内所有元素到表达式
		while (stkEles.size() > 0) {
			tempBackExp.add(stkEles.pop());
		}
		return tempBackExp;
	}

	// 计算最终的结果
	public static int calRst(LinkedList<String> tempBackExp) {
		Stack<Integer> calStk = new Stack<Integer>();
		for (String c : tempBackExp) {
			// 1.数字，入栈
			if (isNumeric(c)) {
				calStk.push(Integer.valueOf(c)); // string to int
				continue;
			}
			// 2.非数字，则为符号，出栈两个元素计算出结果然后再入栈该计算值
			else {
				int a = calStk.pop();
				int b = calStk.pop();
				switch (c.toCharArray()[0]) {
				// 注意减法和除法时，注意出栈的顺序与原表达式是相反的

				case '+':
					calStk.push(b + a);
					continue;
				case '-':
					calStk.push(b - a);
					continue;
				case '*':
					calStk.push(b * a);
					continue;
				case '/':
					calStk.push(b / a);
					continue;
				}
			}
		}
		return calStk.pop();
	}

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}










