
public class MergeBinary {
	public static void main(String args[]){
		String s = "hello" ;
		System.out.println(solution(s));
	}
	
	public static String solution(String s){
		char[] array = s.toCharArray();
		int length = array.length;
		char[] reversearray = new char[length];
		for(int i=0;i<length;i++){
			reversearray[i] = array[length-i-1];
		}
		String string = new String(reversearray);
		return string ;
	}
}




