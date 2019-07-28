package ssm;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
	public static void main(String[] args) {
		HeapSort sort = new HeapSort();
		for(int i=0;i<100;i++){
			int[] nums = sort.generateNums();
			System.out.println("ÅÅÐòÇ°£º"+Arrays.toString(nums));
			sort.sort(nums);
			System.out.println("ÅÅÐòºó£º"+Arrays.toString(nums));
			if (!sort.check(nums)) {
				//System.out.println("ÅÅÐòºó£º"+Arrays.toString(nums));
				System.out.println("Ê§°Ü£¡");
				break;
			}
		}
	}
	public void sort(int[] nums){
		for(int i=nums.length/2;i>=0;i--){
			shiftDown(nums, nums.length-1, i);
		}
		for(int i=nums.length-1;i>0;i--){
			swap(nums, i, 0);
			shiftDown(nums, i-1, 0);
		}
	}
	public void shiftDown(int[] nums, int n, int m){
		while(2*m+1<=n){
			int j = 2*m+1;
			if(j+1<=n&&nums[j+1]>nums[j]){
				j++;
			}
			if(nums[m]>nums[j]){
				break;
			}
			swap(nums, m, j);
			m = j;
		}
	}
	public void swap(int[] nums, int a, int b){
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
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
//	public void sort(int[] nums){
//		for(int i=nums.length/2;i>=0;i--){
//			shiftDown(nums, i, nums.length-1);
//		}
//		for(int i=nums.length-1;i>0;i--){
//			swap(nums, i, 0);
//			shiftDown(nums, 0, i-1);
//		}
//	}
//	public void shiftDown(int[] nums, int m, int n){
//		while(2*m+1<=n){
//			int k = 2*m+1;
//			if(k+1<=n&&nums[k+1]>nums[k]){
//				k++;
//			}
//			if(nums[m]>=nums[k]){
//				break;
//			}
//			swap(nums, m, k);
//			m = k;
//		}
//	}
//	public void swap(int[] nums, int a, int b){
//		int temp = nums[a];
//		nums[a] = nums[b];
//		nums[b] = temp;
//	}
}
