package utils;

import java.net.URLEncoder;

import org.json.simple.JSONObject;

public class APIExecutor {
    @SuppressWarnings("deprecation")
	public static JSONObject executeProductAPI(String isbn)  throws Exception{
        String url = APIUrls.PRODUCT_URL + URLEncoder.encode(isbn);
        System.out.println("ISBN Value ==>" + isbn + " url is ==>" + url);
        return HttpClientHelper.getJsonObjectFromAPI(url);
   
    }
}