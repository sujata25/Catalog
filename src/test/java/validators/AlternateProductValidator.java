package validators;

import com.google.gson.Gson;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONObject;

import pojo.CoursewareAlternateProduct.AlternateProduct;
import pojo.CoursewareAlternateProduct.Record;
import pojo.CoursewareRental.Rental;
import pojo.CoursewareSurcharge.Surchage;

import java.util.HashSet;
import java.util.List;


public class AlternateProductValidator {

	 AlternateProduct alternateProduct;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
		
		HashSet<String> set=new HashSet<String>();
	
	  @SuppressWarnings("unused")
		public AlternateProductValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        alternateProduct = gson.fromJson(jsonString, AlternateProduct.class);
	        failureResult= new HashSet<String>();
	    }
	  
	  public void verifyIsbn13InRecords(String expectedValue) {
	        try{
	        	List<Record> recordList = alternateProduct.getRecords();
	            for (Record record:recordList){
	            	if(record.getIsbn13()== null || record.getIsbn13().isEmpty()){
	            		failureResult.add("MISSING ISBN;");
	            	}else if(!record.getIsbn13().equalsIgnoreCase(expectedValue)){
	            		failureResult.add("INCORRECT ISBN;");
	            	}
	            }
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	
}
