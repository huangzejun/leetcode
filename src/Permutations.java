import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
	public static void main(String[] args){
		int[] nums = {1, 1, 2};
		List<List<Integer>> list = permuteUnique(nums);
		for(List<Integer> list2 : list){
			for(int i : list2){
				System.out.print(i);
			}
			System.out.println();
		}
	}
	public static List<List<Integer>> permuteUnique(int[] nums) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
	    return list;
	}

	private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
	    if(tempList.size() == nums.length){
	        list.add(new ArrayList<>(tempList));
	    } else{
	        for(int i = 0; i < nums.length; i++){
	            //if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
	        	if(used[i]) continue;
	            used[i] = true; 
	            tempList.add(nums[i]);
	            backtrack(list, tempList, nums, used);
	            used[i] = false; 
	            tempList.remove(tempList.size() - 1);
	        }
	    }
	}
}
