import java.util.Stack;

public class ValidParentheses {
	public static void main(String[] args){
		System.out.println(isvalid("()"));
	}
	public static boolean isvalid(String s){
		char[] schar = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<schar.length;i++){
			if(!stack.isEmpty()){
				if(schar[i]==')'&&stack.peek()=='('){
					stack.pop();
				}else if(schar[i]==']'&&stack.peek()=='['){
					stack.pop();
				}else if(schar[i]=='}'&&stack.peek()=='{'){
					stack.pop();
				}else{
					stack.push(schar[i]);
				}
			}else{
				stack.push(schar[i]);
			}
		}
		return stack.isEmpty();
	}
}
