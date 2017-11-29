import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import sun.security.util.Length;

public class SocketTest {
	public static void main(String[] args) {  
		ChangeFormat changeFormat = new ChangeFormat();
        try {  
            Socket s = new Socket("111.205.85.53",15400);  
              
            //构建IO  
            InputStream in = s.getInputStream();  
            OutputStream os = s.getOutputStream();  
            PrintWriter out = new PrintWriter(s.getOutputStream());
            login(out,in,"Administrator","F051B03456C04E1E1");
            String string = "085365476                   2017-09-20 17:19:192010823240000304807080483140251N56";
            String string2 = "085365503                   2017-09-20 17:19:112010823240300304807040423250251N5A";
            String[] strings = {"9B800826001000000593BB0000000002110915031512456D012C064E1E381901000A000600000A01320015CB9D",
            		"9B800826001000000593BD000000000211091503161F456D012D054E1E381902000D000400000901220017D09D",
            		"9B800826001000000593BF000000000211091503141A456D0131024E1E381A00000D006000520A012B000BE99D"};
              
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));  
            //向服务器端发送一条消息  
            //out.print(changeFormat.changeFormat(string, ""+0));
            for(int i=0;i<strings.length;i++){
            	System.out.println("报文:"+strings[1]);
            	out.print(strings[1]);
            	out.flush();
            	
                byte[] res = new byte[140];
        		in.read(res);
        	    for (byte b : res)
        	        System.out.print((char)(b & 0xff));
        	    System.out.println(); 
            }
            //out.print("9B800826001000000593BF0000000002110915030B1A456D0023044E1E380A02000B00A100520A014800154B9D");
            //out.flush();
            //bw.write("9B800826001000000581010000000002110914082026457E0227004E203A2106000F000900820701340031D19D");  
            //bw.flush();  
              
//            byte[] res = new byte[140];
//    		in.read(res);
//    	    for (byte b : res)
//    	        System.out.print((char)(b & 0xff));
//    	    System.out.println(); 
    	    //out.print(changeFormat.changeFormat(string2, ""+0));
            //out.print("9B800826000000000593BB0000000002110914080928456C161D074E1E280D0900010096002B09013700215F9D");
//            out.flush();
//            in.read(res);
//    	    for (byte b : res)
//    	        System.out.print((char)(b & 0xff));
//    	    System.out.println();

        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
	public static void login(PrintWriter out,InputStream in,String username,String password)
    {
    	LoginToServer loginToServer = new LoginToServer();
    	out.print(loginToServer.GetHexString(loginToServer.login(username, password)));
        System.out.println(loginToServer.GetHexString(loginToServer.login(username, password)));
        out.flush();
        byte[] res = new byte[50];
        try {
			in.read(res);
	        for (byte b : res)
	            System.out.print((char)(b & 0xff));
	        System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
