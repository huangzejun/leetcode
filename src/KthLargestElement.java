
public class KthLargestElement {
	public static void main(String[] args){
		//int[] nums = {3,2,1,5,6,4};
		int[] nums = {99,99};
		KthLargestElement kthLargestElement = new KthLargestElement();
		System.out.println(kthLargestElement.findKthLargest(nums, 1));
		kthLargestElement.getloc(nums, 0, nums.length-1);
//		System.out.println(nums);
//		for(int i : nums){
//			System.out.println(i);
//		}
	}
	public int findKthLargest(int[] nums, int k) {
		int a = nums.length-k;
		int start = 0;
		int end = nums.length-1;
		int mid = getloc(nums, start, end);
		
		while(mid!=a){
			if(mid<a){
				start = mid+1;
				mid = getloc(nums, start, end);
			}else if(mid>a){
				end = mid -1;
				mid = getloc(nums, start, end);
			}
		}
        return nums[mid];
    }
	public int getloc(int[] nums, int start, int end){
		if(start==end){
			return start;
		}
		int a = nums[start];
		int low = start;
		int high = end;
		int temp = 0;
		while(end>start){
			while(end>start&&nums[end]>=a){
				end--;
			}
			temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			while(end>start&&nums[start]<a){
				start++;
			}
			temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
		}
		//getloc(nums, low, start-1);
		//getloc(nums, start+1, high);
		return start;
	}
}
