
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import com.sun.glass.ui.CommonDialogs.Type;
import com.sun.org.apache.xpath.internal.operations.Lt;

/**
 * 
 * @author Alex
 * 转换格式
 *
 */
public class ChangeFormat { 
    public static void main(String args[]) { 
    	
    	String str = "085278386                   2017-09-14 17:10:302012216380800300401070000000251N56";
        String string = "9B800826F810000003BD8C000000000211090E10010745730939014E1E0D120200280000007B060100E7008F9D" ;
        String string2  = "800826F810000003BD8C000000000211090E10010745730939014E1E0D120200280000007B060100E700";
        String string3 = "800826001009B005810109E09A09D002110914072519457E010F064E203B18080004000D007D0A0140002B";
        String str1 = "9b9b8";
        changeFormat(str, 248+"");
        System.out.println("1:"+string3+"   长度："+string3.length());

        if(string3.contains("9A")){
        	string3 = string3.replaceAll("9A", "9A02");
        }
        if(string3.contains("9B")){
        	System.out.println("333");
        	string3 = string3.replaceAll("9B", "9A01");
        }
        if(string3.contains("9E")){
        	string3 = string3.replaceAll("9E", "9E02");
        }
        if(string3.contains("9D")){
        	string3 = string3.replaceAll("9D", "9E01");
        }
        System.out.println("2:"+string3+"   长度："+string3.length());
		
        if(str1.contains("9b")){
        	str1.replaceAll("9b", "9a01");
        }
        char[] a = string2.toCharArray();
        int[] b = hexStringToBytes(string2);
        int m=b[0]^b[1];
        for(int i=2;i<b.length;i++){
        	m = m^b[i];
        }
        //System.out.println(xor(string3, "6c"));
        //System.out.println(m);
        //System.out.println(hexStringToBytes(string2));
        
        
    } 
    
    public static String changeFormat(String str, String serialnumber){
    	String head = "800826";
    	String num = StringtoHex(serialnumber);
    	for(int i=0;i<2-num.length();i++) {
			num = "0" + num;
		}
    	head = head+num;
    	String operateType = "00";
    	String strEquipmentId = str.substring(3, 28).trim(); // 数据段1终端号,去空格
		String strDate = str.substring(30, 38); // 数据段2:日期年月

		String data3 = str.substring(39);// 数据段3
		String time = data3.substring(0, 8);// 日期时分秒

		String data = data3.substring(8); // 时间之后的后半段
		String strType = data.substring(0, 1);

		String longitudeHour = data.substring(1, 5);
		String longitudeMin = data.substring(5, 7);
		String longitudeSec = data.substring(7, 9);
		String longitudeMsec = data.substring(9, 11);
		String latitudeHour = data.substring(11, 15);
		String latitudeMin = data.substring(15, 17);
		String latitudeSec = data.substring(17, 19);
		String latitudeMesc = data.substring(19, 21);

		String speed = data.substring(21, 24); // 速度
		
		if(!"2".equals(strType)) {
			operateType = "10";
		}
		String derection = "0";
		try {
			derection = data.substring(24, 27); // 方向
		} catch (Exception ed) {
		}
		String equipmentid = StringtoHex(strEquipmentId);
		String sequipment = "";
		for(int i=0;i<10-equipmentid.length();i++) {
			sequipment += "0";
		}
		//System.out.println("equipmentid:"+sequipment+equipmentid); //设备号
		String platform = "0000000002";
		String hextime = "";
		String t = getHexUtc(strDate+" "+time);
		String[] tdate = t.split(" ");
		String[] tt = tdate[0].split("-");
		String[] dd = tdate[1].split(":");
		for(String aString : tt){
			hextime += intToHex(Integer.parseInt(aString), 1).toUpperCase();
			//System.out.println(Integer.parseInt(aString));
		}
		for(String dString : dd){
			hextime += intToHex(Integer.parseInt(dString), 1).toUpperCase();
			//System.out.println(Integer.parseInt(dString));
		}
		//System.out.println("hextime:"+hextime);
//		String[] date = strDate.split("-");
//		for(String s : date){
//			String st = StringtoHex(s);
//			int l = st.length();
//			for(int i=0;i<2-l;i++)
//				st = "0"+st;
//			hextime += st;
//		}
//		String[] strtime = time.split(":"); 
//		for(String s : strtime){
//			String st = StringtoHex(s);
//			int l = st.length();
//			for(int i=0;i<2-l;i++)
//				st = "0"+st;
//			hextime += st;
//		}
		//System.out.println("hextime:"+hextime);
		String lindex = longitudeHour.substring(0, 1);
		String longitude = "";
		if("0".equals(lindex)){
			String type = "69";     //东经‘E’ ASCALL码为69
			longitude += StringtoHex(type);		
		}else{
			String type = "87";		////西经‘W’ ASCALL码为87
			longitude += StringtoHex(type);	
		}
		String hexlongitudeHour = StringtoHex(longitudeHour.substring(1, 4));
		int hourlength = hexlongitudeHour.length();
		for(int i=0;i<2-hourlength;i++) {
			hexlongitudeHour = "0"+hexlongitudeHour;
		}
		longitude += hexlongitudeHour;
		String hexlongitudeMin = StringtoHex(longitudeMin);
		int minlength = hexlongitudeMin.length();
		for(int i=0;i<2-minlength;i++) {
			hexlongitudeMin = "0"+hexlongitudeMin;
		}
		longitude += hexlongitudeMin;
		String hexlongitudeSec = StringtoHex(longitudeSec);
		int seclength = hexlongitudeSec.length();
		for(int i=0;i<2-seclength;i++) {
			hexlongitudeSec = "0"+hexlongitudeSec;
		}
		longitude += hexlongitudeSec;
		String hexlongitudeMesc = StringtoHex(longitudeMsec);
		int mesclength = hexlongitudeMesc.length();
		for(int i=0;i<2-mesclength;i++) {
			hexlongitudeMesc = "0"+hexlongitudeMesc;
		}
		longitude += hexlongitudeMesc;
		//System.out.println("longitude:"+longitude);
		
		String latindex = latitudeHour.substring(0, 1);
		String latitude = "";
		if("0".equals(latindex)){
			String type = "78";     //北纬‘N’ ASCALL码为78
			latitude += StringtoHex(type);		
		}else{
			String type = "83";		////南纬‘S’ ASCALL码为83
			latitude += StringtoHex(type);	
		}
		String hexlatitudeHour = StringtoHex(latitudeHour.substring(1, 4));
		int lhourlength = hexlatitudeHour.length();
		for(int i=0;i<2-lhourlength;i++) {
			hexlatitudeHour = "0"+hexlatitudeHour;
		}
		latitude += hexlatitudeHour;
		String hexlatitudeMin = StringtoHex(latitudeMin);
		int lminlength = hexlatitudeMin.length();
		for(int i=0;i<2-lminlength;i++) {
			hexlatitudeMin = "0"+hexlatitudeMin;
		}
		latitude += hexlatitudeMin;
		String hexlatitudeSec = StringtoHex(latitudeSec);
		int lseclength = hexlatitudeSec.length();
		for(int i=0;i<2-lseclength;i++) {
			hexlatitudeSec = "0"+hexlatitudeSec;
		}
		latitude += hexlatitudeSec;
		String hexlatitudeMesc = StringtoHex(latitudeMesc);
		int lmesclengh = hexlatitudeMesc.length();
		for(int i=0;i<2-lmesclengh;i++) {
			hexlatitudeMesc = "0"+hexlatitudeMesc;
		}
		latitude += hexlatitudeMesc;
		//System.out.println("latitude:"+latitude);
		
		String high = StringtoHex(new Random().nextInt(16)+"");	//高度
		int highlength = high.length();
		for(int i=0;i<4-highlength;i++)
		 {
			high = "0"+high;
		//System.out.println("high:"+high);
		}
		
		String hexspeed = StringtoHex(speed);
		int speedlength = hexspeed.length();
		for(int i=0;i<4-speedlength;i++) {
			hexspeed = "0"+hexspeed;
		}
		String hexderection = StringtoHex(derection);
		int derectionlength = hexderection.length();
		for(int i=0;i<4-derectionlength;i++) {
			hexderection = "0"+hexderection;
		}
		String Hexstatus = "060100E700";	//卫星数状态精度系数估计误差
		int maxsatellitenum = 11;	//最大卫星数
		int minsatellitenum = 7;	//最少卫星数
		String satellitenum = StringtoHex(new Random().nextInt(maxsatellitenum-minsatellitenum+1)+minsatellitenum+"");
		int satellitenumlength = satellitenum.length();
		for(int i=0;i<2-satellitenumlength;i++) {
			satellitenum = "0"+satellitenum;
		}
		String status = "01";	//状态
		int maxaccuracy = 77;	//最大精度系数
		int minaccuracy = 30;	//最小精度系数
		String accuracy = StringtoHex(new Random().nextInt(maxaccuracy-minaccuracy+1)+minaccuracy+""); 		//精度系数
		int accuracylength = accuracy.length();
		for(int i=0;i<2-accuracylength;i++) {
			accuracy = "0"+accuracy;
		}
		int maxdeviation = 51;	//最大误差估计
		int mindeviation = 11;	//最小误差估计
		String deviation = StringtoHex(new Random().nextInt(maxdeviation-mindeviation+1)+mindeviation+"");	//误差估计
		int deviationlength = deviation.length();
		for(int i=0;i<4-deviationlength;i++)
		 {
			deviation = "0"+deviation;
		//System.out.println("hexspeed:"+hexspeed+"\nhexderection:"+hexderection);
		}
		
		String message = head+operateType+sequipment+equipmentid+platform+hextime+
				longitude+latitude+high+hexspeed+hexderection+satellitenum+status+accuracy+deviation;
		//String message = head+operateType+sequipment+equipmentid+platform+hextime+longitude+latitude+high+hexspeed+hexderection+Hexstatus;	
		char[] a = message.toCharArray();
        int[] b = hexStringToBytes(message);
        int m=b[0]^b[1];
        for(int i=2;i<b.length;i++){	//校验码计算
        	m = m^b[i];
        }
        String jiaoyan = intToHex(xor(message), 1);
        //System.out.println("校验码："+jiaoyan);
        message = message + jiaoyan.toUpperCase();
        //message = message +jy;
        if(message.contains("9A")){
        	message = message.replaceAll("9A", "9A02");
        }
        if(message.contains("9B")){
        	message = message.replaceAll("9B", "9A01");
        }
        if(message.contains("9E")){
        	message = message.replaceAll("9E", "9E02");
        }
        if(message.contains("9D")){
        	message = message.replaceAll("9D", "9E01");
        }
        message = "9B"+message+"9D";
        //System.out.println("message:"+message+"\n长度:"+message.length());
		return message.toUpperCase();
    }
    
	public static String getUTC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yy-MM-dd hh:mm:ss");
		TimeZone timezone = TimeZone.getTimeZone("GMT-0");
		format.setTimeZone(timezone);
		return format.format(date);
	}	
    
	/**
	 * 得到一个时间的hex数字
	 * 
	 * @param strtime
	 * @return String
	 */
	public static String getHexUtc(String strtime) {
		String ret = "";
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = df.parse(strtime);// "2010-1-10 9:7:54"
			//System.out.println(d1);
			return getUTC(d1);
		} catch (Exception e) {
		}
		return ret;
	}
    
    public static String intToHex(int value, int bytes) {
		String m = "00000000000000000000" + Integer.toHexString(value);
		return m.substring(m.length() - bytes * 2, m.length());
	}
    
 // 异或求检验码
    public static int xor(String str) {
     int x = 0;
     if (str != null && str.length() >= 2) {
      x = Integer.parseInt(str.substring(0, 2), 16);
      for (int i = 2; i < str.length(); i += 2) {
       x = x ^ Integer.parseInt(str.substring(i, i + 2), 16);
      }
     }
     return x;
    }

    public static boolean xor(String str, String crc) {
     if (str != null && str.length() >= 2) {
      int x = xor(str);
      if (x == Integer.parseInt(crc, 16)) {
       return true;
      }
     }
     return false;
    }
    
    public static String StringtoHex(String string){
    	return Integer.toHexString(Integer.valueOf(string)).toUpperCase();
    }
    
    public static int[] hexStringToBytes(String hexString) {   
        if (hexString == null || hexString.equals("")) {   
            return null;   
        }   
        hexString = hexString.toUpperCase();   
        int length = hexString.length() / 2;   
        char[] hexChars = hexString.toCharArray();   
        int[] d = new int[length];   
        for (int i = 0; i < length; i++) {   
            int pos = i * 2;   
            d[i] = (int) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
        }   
        return d;   
    }   
    
    private static byte charToByte(char c) {   
        return (byte) "0123456789ABCDEF".indexOf(c); 
    }
} 