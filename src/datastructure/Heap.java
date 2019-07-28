package datastructure;

public class Heap {
	public static void main(String[] args) {
		int[] nums = {0, 1, 2, 3,4,5,6,7,8,9,11,89,8};
		Heap heap = new Heap();
		heap.HeapSort(nums);
		for(int i:nums){
			System.out.println(i);
		}
		System.out.println(nums);
	}
	public void HeapSort(int[] nums){
		for(int i=nums.length/2;i>=0;i--){
			shiftdown(nums, i);
		}
	}
	public void shiftdown(int[] nums, int k){
		while((k+1)*2<nums.length){
			int l = (k+1)*2;
			if(nums[l-1]>nums[l]){
				l--;
			}
			if(nums[k]>nums[l])
				break;
			swap(nums, k, l);
			k = l;
		}
	}
	public void swap(int[] nums, int i, int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
