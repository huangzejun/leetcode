package ssm;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
	public static void main(String[] args) {
		QuickSort sort = new QuickSort();
		for(int i=0;i<100;i++){
			int[] nums = sort.generateNums();
			System.out.println("ÅÅÐòÇ°£º"+Arrays.toString(nums));
			sort.sort(nums, 0, nums.length-1);
			System.out.println("ÅÅÐòºó£º"+Arrays.toString(nums));
			if (!sort.check(nums)) {
				//System.out.println("ÅÅÐòºó£º"+Arrays.toString(nums));
				System.out.println("Ê§°Ü£¡");
				break;
			}
		}
	}
	public void sort(int[] nums, int low, int high){
		if(low<high){
			int mid = Partititon(nums, low, high);
			sort(nums, low, mid-1);
			sort(nums, mid+1, high);
		}
	}
	public int Partititon(int[] nums, int low, int high){
		int key = nums[low];
		while(low<high){
			while(low<high&&nums[high]>=key){
				high--;
			}
			if(low<high){
				nums[low] = nums[high];
			}
			while(low<high&&nums[low]<=key){
				low++;
			}
			if(low<high){
				nums[high] = nums[low];
			}
		}
		nums[low] = key;
		return low;
	}
	public int[] generateNums(){
		Random random = new Random();
		int[] nums = new int[100];
		for(int i=0;i<nums.length;i++){
			nums[i] = random.nextInt(100);
		}
		return nums;
	}
	public boolean check(int[] nums){
		for(int i=1;i<nums.length;i++){
			if(nums[i-1]>nums[i]){
				return false;
			}
		}
		return true;
	}
//	public void sort(int[] nums, int low, int high){
//		if(high>low){
//			int mid = partition(nums, low, high);
//			sort(nums, low, mid-1);
//			sort(nums, mid+1, high);
//		}
//	}
//	public int partition(int[] nums, int start ,int end){
//		int key = nums[start];
//		int low = start;
//		int high = end;
//		while(high>low){
//			while(nums[high]>key&&high>low){
//				high--;
//			}
//			if(high>low){
//				nums[low] = nums[high];
//				low++;
//			}
//			while(nums[low]<=key&&high>low){
//				low++;
//			}
//			if(high>low){
//				nums[high] = nums[low];
//				high--;
//			}
//		}
//		nums[low] = key;
//		return low;
//	}
}
