import java.util.Date;

public class Test {
	public static void main(String[] args){
//		String string ="OK";
//		String string3 = "OK";
//		String tString = new String("OK");
//		String string2 = new String(tString);
//		Integer aInteger = 888;
//		Integer bInteger = 888;
//		double largest = Double.NEGATIVE_INFINITY;
//		System.out.println("largest:"+largest);
//		System.out.println("aInteger==bInteger:"+(aInteger==bInteger));
//		System.out.println("string==string3:"+(string==string3));
//		int[][] nums = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
//		for(int i=0;i<nums.length;i++)
//            for(int j=0;j<nums[i].length;j++)
//                System.out.println(nums[i][j]);
		//System.out.println("结果"+findUnsortedSubarray(nums));
		//System.out.println(string.equals(string2));
		
		Date date1 = new Date("Aug 20 11:58:20 GMT+00:00 2017");
		System.out.println(date1);
	}
	
	public static int findUnsortedSubarray(int[] nums) {
        int min = -1;
        int max = -1;
        for(int i=0;i<nums.length-1;i++){
        	System.out.println(i);
            if(nums[i]>=nums[i+1]){
                min = i;
                break;
            }
        }
        for(int j=(nums.length-1);j>0;j--){
        	System.out.println(nums[j]+":"+min);
            if(nums[j]>=nums[min]&&nums[j]<nums[j-1]){
                max = j;
                break;
            }
            if(nums[j]<nums[min]&&nums[j]<=nums[j-1]){
            	System.out.println("nums[j]<min:"+nums[j]);
                max = j;
                break;
            }
        }
        if(min == -1)
            return 0;
        else
            return max-min+1;
    }
}
