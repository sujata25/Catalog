package utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;

public class HttpClientHelper {

   
    public static JSONObject getJsonObjectFromAPI(String url) throws Exception{
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        HttpResponse response = null;
        ResponseHandler<String> handler = new BasicResponseHandler();
        JSONObject jsonObject = new JSONObject();
        //try {
        	String json = null;
             response = httpClient.execute(get);
             System.out.println("status code is==>" + response.getStatusLine().getStatusCode());
             if(response.getStatusLine().getStatusCode()>300){
                 System.out.println("if  code is==>" + response.getStatusLine().getStatusCode());
            	 json= EntityUtils.toString(response.getEntity());
             }
             else{
            	 System.out.println("else loop");
                 json = handler.handleResponse(response);
            }
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(json);
           // System.out.println("json object "+  jsonObject);
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