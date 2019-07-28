import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Test1 {
	public static void main(String[] args){
		int a = 3;
		int b = 4;
		//System.out.println((double)(a+b)/2);
		int t = 13;
		System.out.println(Integer.toBinaryString(t));
		System.out.println(Integer.toBinaryString(t>>1));
		System.out.println(Integer.toBinaryString(t>>>1));
		System.out.println(Integer.toBinaryString(t<<1));
		t = -13;
		System.out.println(Integer.toBinaryString(t));
		System.out.println(Integer.toBinaryString(t>>1));
		System.out.println(Integer.toBinaryString(t>>>1));
		System.out.println(Integer.toBinaryString(t<<1));
//		String string ="OK";
//		String string3 = "OK";
//		String tString = new String("OK");
//		String string2 = new String(tString);
//		Integer aInteger = 888;
//		Integer bInteger = 888;
//		double largest = Double.NEGATIVE_INFINITY;
//		System.out.println("largest:"+largest);
//		System.out.println("aInteger==bInteger:"+(aInteger==bInteger));
//		System.out.println("string==string3:"+(string==string3));
//		int[][] nums = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
//		for(int i=0;i<nums.length;i++)
//            for(int j=0;j<nums[i].length;j++)
//                System.out.println(nums[i][j]);
		//System.out.println("结果"+findUnsortedSubarray(nums));
		//System.out.println(string.equals(string2));
//		
//		Date date1 = new java.sql.Date(16, 7, 19);
//		Date date2 = new Date(16, 7, 19);
//		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		String date = "2016-07-19";
//		try {
//			System.out.println(format1.parse(date));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(date2);
//		System.out.println("ttt");
//		System.out.println("two cangku");
//		String string = "JSXX085370245                   2018-02-10 17:15:012012200420900294342060000000041N53JSXX085370159                   2018-02-10 17:15:012012218290400295642070050000061N56JSXX085360617                   2018-02-10 17:15:012012222260300295109070000000061N56JSXX085360610                   2018-02-10 17:15:012012222260200295109080000000061N5FJSXX085273244                   2018-02-10 17:15:012012246060300304229010010000071N59JSXX085360743                   2018-02-10 17:15:012012217240400295632000000000061N5DJSXX085365788                   2018-02-10 17:15:012012215270200295713010000000111N5DJSXX085206248                   2018-02-10 17:15:012012224540700295520070000000071N50JSXX085361660                   2018-02-10 17:15:012012213320100294529080000000101N5DJSXX085365508                   2018-02-10 17:15:012012242080300301221090000000101N58JSXX085365645                   2018-02-10 17:15:012012227340300304411040000000101N53JSXX085362180                   2018-02-10 17:15:013012211410100295925070000000131N5DJSXX085370575                   2018-02-10 17:15:012012218070500295644050000000041N52JSXX085326699                   2018-02-10 17:15:012012220410600295728080000000051N5DJSXX085370534                   2018-02-10 17:15:012012210390200300654060000000061N58JSXX085370667                   2018-02-10 17:15:012012328280700300944040213410081N55JSXX085361699                   2018-02-10 17:15:012012210260300295746060000000081N52JSXX085362149                   2018-02-10 17:15:012012217270900295636020000000091N54JSXX085362029                   2018-02-10 17:15:012012205520400303636000000000091N53JSXX085206255                   2018-02-10 17:15:012012211500700294158040000000061N56JSXX085360742                   2018-02-10 17:15:012012227290400304402060000000041N5EJSXX085361675                   2018-02-10 17:15:012012216010000294458060000000101N54JSXX085360844                   2018-02-10 17:15:012012224190800295659010010000101N5DJSXX085361855                   2018-02-10 17:15:012012248550100304258010010000091N53JSXX085278419                   2018-02-10 17:15:011012216550400300401070000000071N54JSXX085360624                   2018-02-10 17:15:012012222150700295211070000000071N59JSXX085356150                   2018-02-10 17:15:012012224210300295659080000000061N5BJSXX085326678                   2018-02-10 17:15:012012155240200291121050010000071N51JSXX085370292                   2018-02-10 17:15:012012212550200294636000000000051N56JSXX085360914                   2018-02-10 17:15:012012202020100301154010000000071N5FJSXX085365741                   2018-02-10 17:15:012012217010900295628010000000081N54JSXX085362139                   2018-02-10 17:15:012012219120900295742060030000081N5FJSXX085370030                   2018-02-10 17:15:012012217580000295640000000000061N55JSXX085360747                   2018-02-10 17:15:012012218320900295644010000000051N5FJSXX085361956                   2018-02-10 17:15:012012216020500295644050000000081N58JSXX085361935                   2018-02-10 17:15:012012245490600304234040010000091N5CJSXX085362090                   2018-02-10 17:15:012012238260800301156010000000091N52JSXX085369992                   2018-02-10 17:15:012012208080700300722020093450041N53JSXX085361715                   2018-02-10 17:15:012012227280500304353060010000101N5AJSXX085361923                   2018-02-10 17:15:012012249010200304258060000000101N5EJSXX0853";
//		System.out.println(string);
//		String tString = "085361923                   2018-02-10 17:15:012012249010200304258060000000101N5E";
//		System.out.println(tString.length());
//		String[] aString = string.split("JSXX");
//		System.out.println(aString[aString.length-1]);
//		int a = 7;
//		for(int i=0;i<32;i++){
//			System.out.println(a);
//			System.out.println(a&1);
//			a = a>>1;
//		}
//		Integer.bitCount(7);
	}
	
	public static int findUnsortedSubarray(int[] nums) {
        int min = -1;
        int max = -1;
        for(int i=0;i<nums.length-1;i++){
        	System.out.println(i);
            if(nums[i]>=nums[i+1]){
                min = i;
                break;
            }
        }
        for(int j=(nums.length-1);j>0;j--){
        	System.out.println(nums[j]+":"+min);
            if(nums[j]>=nums[min]&&nums[j]<nums[j-1]){
                max = j;
                break;
            }
            if(nums[j]<nums[min]&&nums[j]<=nums[j-1]){
            	System.out.println("nums[j]<min:"+nums[j]);
                max = j;
                break;
            }
        }
        if(min == -1)
            return 0;
        else
            return max-min+1;
    }
}
