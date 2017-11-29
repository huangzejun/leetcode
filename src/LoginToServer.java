

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Gracecoder on 2017/9/15.
 */
public class LoginToServer {


    public static byte[] login(String username, String pass) {

        byte[] head = new byte[]{(byte) 0x9b};
        byte[] tail = new byte[]{(byte) 0x9d};


        byte[] business_type = new byte[]{0x00};
        byte[] attribute = new byte[2];

        int len = 40;
        //长度过长要分包
        attribute[1] = (byte) (len & 0xff);

        attribute[0] = (byte) (len >> 8 & 0x03);

        byte x = 0;
        byte net = 0x01;
        attribute[0] = (byte) (attribute[0] | x << 2);
        attribute[0] = (byte) (attribute[0] | net << 3);

        byte[] flow = new byte[]{(byte) 0xf7};

        byte[] message_head = concat(business_type, attribute);
        message_head = concat(message_head, flow);
        // message_head = concat(message_head, wrapper);

        byte[] usrname = fixLengthBytes(username,20);


        byte[] password = fixLengthBytes(pass,20);

        byte[] message_body = concat(usrname, password);

        byte[] check_code = new byte[]{getCheckCode(concat(message_head, message_body))};

        byte[] res = concat(head, message_head);
        res = concat(res, message_body);
        res = concat(res, check_code);
        res = concat(res, tail);


        return res;
    }

    public static byte[] fixLengthBytes(String str,int len)
    {
        byte[] res = new byte[len];

        byte[] strBytes = str.getBytes();
        int strLen = strBytes.length;

        System.arraycopy(strBytes,0,res,0,strLen);



        return res;

    }


    public static byte[] reverse(byte[] bytes)
    {
        byte[] res = new byte[bytes.length];
        int k = 0;
        for (int i = bytes.length - 1; i>=0 ; i--)
            res[k++] = bytes[i];
        
        for (byte b : res)
            System.out.println(b);
            

        return res;
    }

    public static byte[] concat(byte[] a, byte[] b) {
        byte[] res = new byte[a.length + b.length];
        System.arraycopy(a, 0, res, 0, a.length);
        System.arraycopy(b, 0, res, a.length, b.length);
        return res;
    }

    public static byte getCheckCode(byte[] bytes) {
        byte res = bytes[0];
        for (int i = 1; i < bytes.length; i++)
            res ^= bytes[i];

        return res;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }



    public static String GetHexString(byte[] b) {

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            res.append(hex.toUpperCase());
        }

        return new String(res);

    }



    public static void main(String[] args) {

        try {

            String ip = "111.205.85.53";
            String[] ipStr = ip.split("\\.");
            byte[] ipBuf = new byte[4];
            for (int i = 0; i < 4; i++) {
                ipBuf[i] = (byte) (Integer.parseInt(ipStr[i]) & 0xff);
            }

            

//            printHexString(func("Administrator", "F051B03456C04E1E1"));
//            Socket socket = new Socket("111.205.85.53",15400);
            Socket socket = new Socket(InetAddress.getByAddress(ipBuf), 15400);
//            Socket socket = new Socket("10.65.1.50",8888);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);
            byte[] mes = new byte[]{0x39,0x42,0x30,0x30,0x30,0x38,0x32,0x38,0x46,0x37,0x34,0x31,0x36,0x34,0x36,0x44,0x36,0x39,0x36,0x45,0x36,0x39,0x37,0x33,0x37,0x34,0x37,0x32,0x36,0x31,0x37,0x34,0x36,0x46,0x37,0x32,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x34,0x36,0x33,0x30,0x33,0x35,0x33,0x31,0x34,0x32,0x33,0x30,0x33,0x33,0x33,0x34,0x33,0x35,0x33,0x36,0x34,0x33,0x33,0x30,0x33,0x34,0x34,0x35,0x33,0x31,0x34,0x35,0x33,0x31,0x30,0x30,0x30,0x30,0x30,0x30,0x43,0x46,0x39,0x44};
            writer.print(GetHexString(login("Administrator", "F051B03456C04E1E1")));


            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            
//            System.out.println(reader.readLine());
            

            byte[] res = new byte[50];
            in.read(res);

            for (byte b : res)
                System.out.print((char)(b & 0xff));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
