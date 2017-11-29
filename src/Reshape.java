
public class Reshape {
	public static void main(String args[]){
		int[][] ab = {{1,2},{3,4}} ;
		
		matrixReshape(ab, 2, 4) ;
		//System.out.println(ab[0].length);
		
	}
	
	public static int[][] matrixReshape(int[][] nums, int r, int c) {
		int length = 0 ;
		int[][] result = new int[r][c] ;
		for(int[] demon1 : nums)
			for(int demon2 : demon1)
				length++ ;
		if((r*c)!=length)
			return nums ;
		int[] mid = new int[length] ;
		length = 0 ;
		for(int[] demon1 : nums)
			for(int demon2 : demon1)
				mid[length++] = demon2 ;
		length = 0 ;
		for(int i=0;i<result.length;i++)
			for(int j=0;j<result[i].length;j++)
				result[i][j] = mid[length++] ;
        return result ;
    }
}
