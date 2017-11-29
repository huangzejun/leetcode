import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class FindAnagrams {
	public static void main(String[] args){
		System.out.println(findAnagrams("ababababab", "aab"));
	}
	public static List<Integer> findAnagrams(String s, String p) {
        char[] pchar = p.toCharArray();
        List<Integer> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<Character, Integer>() ;
        Hashtable<Character, Integer> hashtable = new Hashtable<>();
        HashSet<Character> hashset = new HashSet<>();
//        for(int i=0;i<pchar.length;i++){
//        	if(hashset.contains(pchar[i])){
//        		map.put(pchar[i], map.get(pchar[i])+1);
//        	}else{
//        		hashset.add(pchar[i]);
//        		map.put(pchar[i], 0);
//        	}
//        }
        for(int i=0;i<pchar.length;i++){
        	if(hashtable.containsKey(pchar[i])){
        		System.out.println("i=:"+i);
        		System.out.println("包含："+pchar[i]);
        		hashtable.put(pchar[i], hashtable.get(pchar[i])+1);
        	}else{
        		System.out.println("i="+i);
        		hashtable.put(pchar[i], 1);
        	}
        }
        int end = s.length()-p.length();
        int l = p.length();
        System.out.println(end);
        System.out.println(l);
        for(int j=0;j<end+1;j++){
            System.out.println(j);
            //System.out.println(hashset);
            @SuppressWarnings("unchecked")
			HashSet<Character> set = (HashSet<Character>) hashset.clone();
            Hashtable<Character, Integer> table = (Hashtable<Character, Integer>) hashtable.clone();
            if(isAnagram(s.substring(j, j+l), set, table)){
                list.add(j);
            }
        }
        return list;
    }
    public static boolean isAnagram(String s, HashSet<Character> hashset, Hashtable<Character, Integer> table){
        char[] schar = s.toCharArray();
        System.out.println(table);
        for(int i=0;i<schar.length;i++){
//            if(hashset.contains(schar[i])){
            	if(table.containsKey(schar[i])){
            		System.out.println("i="+i);
            		table.put(schar[i], table.get(schar[i])-1);
            		if(table.get(schar[i])<0)
            			return false;
            	}else{
            		System.out.println("i=:"+i);
            		return false;
            	}
//            	if(map.get(schar[i])>0){
//            		map.put(schar[i], map.get(schar[i])-1);
//            	}else{
//            		hashset.remove(schar[i]);
//            	}
//            }else{
//                return false;
//            }
        }
        return true;
    }
}
