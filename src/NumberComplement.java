
public class NumberComplement {
	public static void main(String args[]){
		String string = "abcdef ghi" ;
		String[] cString = string.split("") ;
//		for(int i=0;i<cString.length;i++){
//			System.out.println(cString[i]);
//		}
		findComplement(5) ;
		
	}
	public static int findComplement(int num) {
		String bin = Integer.toBinaryString(num) ;
		String[] bit = bin.split("") ;
		int len = bit.length ;
		int[] result= new int[len] ;
		int a = 0 ;
		for(int i=0;i<len;i++){
			result[i]=Integer.parseInt(bit[i])^1 ;
			a+=(Integer.parseInt(bit[i])^1)*(Math.pow(2, len-i-1)) ;
		}
		return a ;
    }
}
