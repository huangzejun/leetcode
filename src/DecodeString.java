import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author Alex
 *
 *s = "3[a]2[bc]", return "aaabcbc".
 *s = "3[a2[c]]", return "accaccacc".
 *s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {
	static String result = "";
	static int i = 0;
	public static void main(String[] args){
		System.out.println(decodeString("3[a]2[b4[F]c]"));
	}
	public static String decodeString(String s) {
        char[] list = s.toCharArray();
        int count = 0;
        String strcount = "";
        String result = "";
        String deep = "";
        while(i<list.length){
        	System.out.println(i + ":" +list[i]);
        	if(list[i]<='9'&&list[i]>='0'){
        		strcount += list[i];
        		i++;
        		continue;
        	}
        	if((list[i]<='z'&&list[i]>='a')||(list[i]<='Z'&&list[i]>='A')){
        		result += list[i];
        		i++;
        		continue;
        	}
        	if(list[i]=='['){
        		i++;
        		deep = decodeString(s);
        		if(strcount!=""){
                	count = Integer.parseInt(strcount);
                }
                for(int j=0;j<count;j++){
                	result += deep;
                }
                count = 0;
                strcount = "";
        		deep = "";
        		continue;
        	}
        	if(list[i]==']'){
        		if(i<(list.length-1)){
        			i++;
        		}
        		break;
        	}
        }
        return result;
    }
}
