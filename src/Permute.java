import java.util.ArrayList;
import java.util.List;

public class Permute {
	public static void main(String[] args){
		List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        result.add(list);
        for(List<Integer> list2 : result){
        	System.out.println(list2.size());
        }
	}
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        result.add(list);
        for(List<Integer> list2 : result){
        	System.out.println(list2.size());
        }
        //getresult(result, list, nums);
        return result;
        
    }
    public void getresult(List<List<Integer>> result, List<Integer> list, int[] nums){
        for(int i=0;i<nums.length;i++){
            if(list.size() == nums.length){
                result.add(list);
            }
            if(list.contains(nums[i]))
                continue;
            list.add(nums[i]);
            getresult(result, list, nums);
            list.remove(list.size()-1);
        }
    }
}
