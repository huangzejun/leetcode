

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 序列化、反序列化Json工具
 * @author RD
 *
 */
public class JsonTools {
	/**
	 * 将对象序列化的工具对象
	 */
	private static ObjectMapper mapper = new MyObjectMapper();

	/**
	 * 定义时间序列化格式
	 */
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		//mapper.setSerializationInclusion(Include.NON_NULL); 
		mapper.setDateFormat(dateFormat);
	}

	/**
	 * 将对象序列化
	 * 
	 * @param t
	 *            将被序列化的对象
	 * @return Json数据
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> String SerializeList(T t) throws JsonGenerationException,
			JsonMappingException, IOException {

		return mapper.writeValueAsString(t);
	}

	public static <T> String SerializeObject(T t)
			throws JsonGenerationException, JsonMappingException, IOException {

		return SerializeList(t);
	}

	/**
	 * 将Json字符串反序列化成对象
	 * 
	 * @param json
	 * @param className
	 * @return T类型对象
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T DeserializeList(String json, Class className)
			throws JsonParseException, JsonMappingException, IOException {
		if (json == null) {
			return null;
		} else {
			return (T) mapper.readValue(json, className);
		}
	}

	public static <T> T DeserializeObject(String json, Class className)
			throws JsonParseException, JsonMappingException, IOException {
		return DeserializeList(json, className);
	}
	
	public static <T> T parserXML(String xml) {  
        ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());  
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(in));  
        decoder.close();  
        return (T) decoder.readObject();  
    }  
  
    public static <T> String formatXML(T entity) {  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(out));  
        encoder.writeObject(entity);  
        encoder.close();  
        return out.toString();  
    }  
}

class MyObjectMapper extends ObjectMapper{
	public MyObjectMapper(){
		super();
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
			@Override
			public void serialize(Object value, JsonGenerator jgen,
					SerializerProvider provider) throws IOException,
					JsonProcessingException {
				jgen.writeString("");
			}
			
		});
		//this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		//this.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
	}
}
