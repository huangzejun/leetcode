import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class topKFrequent {
	public static void main(String[] args){
		int[] nums = {1,1,1,2,2,3};
		solution(nums, 0);
		List<Integer> list = new ArrayList<>();
	}
	public static List<Integer> solution(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }else{
                map.put(nums[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet()); 
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> arg0, Entry<Integer, Integer> arg1) {
				// TODO Auto-generated method stub
				return arg0.getValue().compareTo(arg1.getValue());
			}
            
        });
        for(Map.Entry<Integer, Integer> mEntry : list){
        	System.out.println("key:"+mEntry.getKey() + " value:"+mEntry.getValue());
        }
        return null;
    }
}
