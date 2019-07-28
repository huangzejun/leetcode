import java.util.*;

public class TaskScheduler {
	public static void main(String[] args){
		int[] map = new int[26];
		map[1] = 3;
		map[2] = 3;
		Arrays.sort(map);
		for(int a : map){
			System.out.println(a);
		}
	}
}
