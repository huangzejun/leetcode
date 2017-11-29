
public class DistributeCandies {
	public static void main(String args[]){
		int[] a = {1,1,2,3} ;
		System.out.println(distributeCandies(a)) ;
	}
	
	public static int distributeCandies(int[] candies) {
		int num = 1 ;
		QuitSort(candies, 0, candies.length-1);
		int size = candies[0] ;
		for(int de : candies){
			System.out.println("size="+size+",de="+de);
			if(de > size)
			{
				size = de ;
				num ++ ;
			}
		}
		if(num>(candies.length/2))
			return candies.length/2 ;
		else 
			return num ;
    }
	
	public static void QuitSort(int[] array, int start, int end){
		if(start<end){
			int b = Partition(array, start, end) ;
			QuitSort(array, start, b-1) ;
			QuitSort(array, b+1, end) ;
		}
	}
	
	public static int Partition(int[] array, int start, int end){
		int index = array[start] ;
		int i = start , j = end ;
		while(i<j){
			while(array[j]>=index&&i<j)
				j-- ;
			while(array[i]<=index&&i<j)
				i++ ;
			Swap(array, i, j);
		}
		Swap(array, start, i);
		array[i] = index ;
		return i ;
	}
	
	public static void Swap(int[] array, int a, int b){		
		int mid = array[a] ;
		array[a] = array[b] ;
		array[b] = mid ;		
	}
}
