import java.util.ArrayList;

public class KeyboardRow {
	public static void main(String args[]){
		String[] strs = {"Hello", "Alaska", "Dad", "Peace"} ;
		findWords(strs) ;
	}
	
	public static String[] findWords(String[] words){ 
		@SuppressWarnings("serial")
		ArrayList<Character> arrayList1 = new ArrayList<Character>(){{add('q');add('w');add('e');add('r');
		add('t');add('t');add('y');add('u');add('i');add('o');add('p');}};
		@SuppressWarnings("serial")
		ArrayList<Character> arrayList2 = new ArrayList<Character>(){{add('a');add('s');add('d');add('f');
		add('g');add('h');add('j');add('k');add('l');}};
		@SuppressWarnings("serial")
		ArrayList<Character> arrayList3 = new ArrayList<Character>(){{add('z');add('x');add('c');add('v');
		add('b');add('n');add('m');}};
		ArrayList<String> result =new ArrayList<>();
		for(int i=0;i<words.length;i++){
			char[] letter = words[i].toLowerCase().toCharArray();
			boolean index = true;
			if(arrayList1.contains(letter[0])){
				for(char a : letter){
					index &= arrayList1.contains(a);
				}
			}
			if(arrayList2.contains(letter[0])){
				for(char a : letter){
					index &= arrayList2.contains(a);
				}
			}
			if(arrayList3.contains(letter[0])){
				for(char a : letter){
					index &= arrayList3.contains(a);
				}
			}
			if(index)
				result.add(words[i]);
		}
		String[] resultword = result.toArray(new String[result.size()]);
 		return resultword ;
	}
}
