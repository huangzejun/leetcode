import java.awt.image.BufferedImage;  
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.FileImageInputStream;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder; 

public class ImageConvert {
	
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
  
    public static void main(String[] args) {  
        System.out.println(getImageBinary());  
        base64StringToImage(getImageBinary());  
    }  
  
    /** 
     * 将图片转换成二进制 
     *  
     * @return 
     */  
    
    private static Object[] readTiff(File tifFile) {
        ImageReader reader = null;
        FileImageInputStream fis = null;
        Object[] res = null;
        try {

            reader = ImageIO.getImageReadersByFormatName("tiff").next();
            fis = new FileImageInputStream(tifFile);
            reader.setInput(fis);
            res = new Object[]{reader, fis};
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }finally {


        }
        return res;
    }
    
    public static long[] getTiffDPI(ImageReader reader, int index) {
        long[] dpi = new long[2];

        IIOMetadata meta = null;
        try {

            meta = reader.getImageMetadata(index);
            org.w3c.dom.Node n = meta.getAsTree("javax_imageio_1.0");
            n = n.getFirstChild();

            while (n != null) {

                if (n.getNodeName().equals("Dimension")) {

                    org.w3c.dom.Node n2 = n.getFirstChild();

                    while (n2 != null) {

                        if (n2.getNodeName().equals("HorizontalPixelSize")) {

                            org.w3c.dom.NamedNodeMap nnm = n2.getAttributes();

                            org.w3c.dom.Node n3 = nnm.item(0);

                            float hps = Float.parseFloat(n3.getNodeValue());

                            dpi[0] = Math.round(25.4f / hps);

                        }

                        if (n2.getNodeName().equals("VerticalPixelSize")) {

                            org.w3c.dom.NamedNodeMap nnm = n2.getAttributes();

                            org.w3c.dom.Node n3 = nnm.item(0);

                            float vps = Float.parseFloat(n3.getNodeValue());

                            dpi[1] = Math.round(25.4f / vps);
                        }

                        n2 = n2.getNextSibling();

                    }

                }

                n = n.getNextSibling();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dpi;
    }

    
    
    public static BufferedImage loadTiff(File tifFile) {

        ImageReader reader = null;
        FileImageInputStream fis = null;
        BufferedImage res = null;
        try {
            Object[] src = readTiff(tifFile);
            if(src == null) {
                return null;
            }
            reader = (ImageReader) src[0];
            fis = (FileImageInputStream) src[1];
            if (reader != null) {
                int numPages = reader.getNumImages(true);
                if (numPages > 0) {
                    //long[] dpiArr = getTiffDPI(reader, 0);
                    //dpiData[0] = dpiArr[0];
                    //dpiData[1] = dpiArr[1];
                    res = reader.read(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {

            if(reader != null) {
                reader.dispose();
            }

            if (fis != null) {
                try {
                    fis.flush();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return res;
    }
    
    static String getImageBinary() {  
        File f = new File("D://image2.tiff");  
        BufferedImage bi;  
        try {  
            bi = loadTiff(f);  
            if(bi!=null){
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	            ImageIO.write(bi, "tiff", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真  
	            byte[] bytes = baos.toByteArray();  
	  
	            return encoder.encodeBuffer(bytes).trim(); 
            }
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /** 
     * 将二进制转换为图片 
     *  
     * @param base64String 
     */  
    static void base64StringToImage(String base64String) {  
        try {  
            byte[] bytes1 = decoder.decodeBuffer(base64String);  
  
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);  
            BufferedImage bi1 = ImageIO.read(bais);  
            File w2 = new File("D://test.tiff");// 可以是jpg,png,gif格式  
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
