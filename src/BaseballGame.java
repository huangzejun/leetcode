import java.util.ArrayList;
import java.util.HashSet;

public class BaseballGame {
	public static void main(String[] args){
		String string = "12";
		int[] a = {1,3,2,4,3};
		int i =-1 ;
		ArrayList<Integer> arrayList = new ArrayList<>();
		HashSet<Integer> hashSet = new HashSet<>();
		hashSet.add(1);
		hashSet.add(2);
		hashSet.add(5);
		arrayList.add(1);
		Integer[] c = new Integer[arrayList.size()];
		arrayList.toArray(c);
		for(int j=0;i<arrayList.size();i++){
			arrayList.get(j);
		}
		System.out.println(hashSet.size());
		hashSet.remove(5);
		System.out.println(hashSet.size());
//		arrayList.add(Integer.parseInt(string));
//		arrayList.add(3);
//		arrayList.remove(new Integer(3));
//		assert i>=0 ;
//		System.out.println(arrayList.get(i));
		
	}
	
//    public int calPoints(String[] ops) {
//        for(String s : ops){
//            switch(s){
//                case "+":
//                    break;
//                case "D":
//                    break;
//                case "C":
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
}
