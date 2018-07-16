package utils;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;

public class HttpClientHelper {

   /* public static void main(String[] args) {
        JSONObject jsonObject = getJsonObjectFromAPI("https://s-www.cengage.com/ws/endeca/cengage/products?dims=unlimited&expanded=true&q=9781337769754");
        System.out.println(jsonObject.toJSONString());
        JSONObject params = (JSONObject) jsonObject.get("params");
        System.out.println(params.get("q").toString());
    }*/

    public static JSONObject getJsonObjectFromAPI(String url) throws Exception{
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        HttpResponse response = null;
        ResponseHandler<String> handler = new BasicResponseHandler();
        JSONObject jsonObject = new JSONObject();
        //try {
             response = httpClient.execute(get);
            String json = handler.handleResponse(response);
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(json);
        /*} catch (ParseException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
    		System.out.println("No response exists for ISBN value ==>" + url.substring(url.indexOf("q")+ 2, url.length()-1));
        	e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return jsonObject;
    }

}