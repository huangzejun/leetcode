import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ClassEqualTest {
	public static void main(String[] args) throws IOException{
//		HashMap<Pair, Integer> map = new HashMap<>();
//		Pair pair1 = new Pair("123456", "123");
//		Pair pair2 = new Pair("123456", "123");
//		map.put(pair1, 2);
//		System.out.println(map.get(pair2));
		Socket socket = null;
		int index = 0;
		try {
			socket = new Socket("122.226.21.229", 5089);
//			while(true){
//				System.out.println(socket.getInputStream().toString());
//			}
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
			String info="";
			String temp=null;//临时变量
			while((temp=bufferedReader.readLine())!=null){
				info+=temp;
			    System.out.println(index++ + " 客户端接收服务端发送信息："+info);
			    
			}
			             
			bufferedReader.close();
			inputStream.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			socket.close();
		}
		
	}
	
}
