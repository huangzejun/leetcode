import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class MaxSubArray {
	public static void main(String[] args){
		String string = "{\"userId\":\"drotc1SkMLUXeiE6lsNNIv+ARfNSNFJA\",\"user_type\":\"user\",\"onlyShowCTBTShips\":\"\",\"locationType\":\"110\",\"ipAddress\":\"\",\"macAddress\":\"704D7B494C61  \",\"trueAddress\":\"无法获取IP归属地\",\"clientType\":\"PC\",\"startDateTime\":\"2017/10/20 10:17:45\",\"endDateTime\":\"2017/10/21 11:17:45\",\"pagerStr\":\"{\\\"total_page_count\\\":0,\\\"total_record_count\\\":0,\\\"current_page_number\\\":1,\\\"page_size\\\":15,\\\"first_page_number\\\":1,\\\"pre_page_number\\\":0,\\\"next_page_number\\\":0,\\\"last_page_number\\\":0,\\\"is_first_page\\\":false,\\\"is_last_page\\\":false,\\\"list\\\":[]}\"}" ;
		try {
			Map<String, String> map = JsonTools.DeserializeObject(
					string, Map.class);
			System.out.println(map.get("pagerStr"));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
