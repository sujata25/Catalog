package validators;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import pojo.CoursewareAlternateProduct.AlternateProduct;

import pojo.CoursewareSurcharge.Record;
import pojo.CoursewareSurcharge.Surchage;

import java.util.HashSet;
import java.util.List;


public class SurchageValidator 
{

	 Surchage surchage;
	 
	 JSONObject jsonObject;
	 
	 HashSet<String> failureResult;
	 	List<Record> recordList;
		public  HashSet<String> failureResult() 
		{
			return failureResult;
		}
		
		@SuppressWarnings("unused")
		public SurchageValidator(JSONObject jsonObject) 
	  {
		  	this.jsonObject = jsonObject;
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        surchage = gson.fromJson(jsonString, Surchage.class);
	        failureResult= new HashSet<String>();
	        recordList =  surchage.getRecords();
	    }
	  


	  
	  public void verifyIsbn13InRecords(String expectedValue) 
	  {
	        try{
	       
	            for (Record record:recordList)
	            {
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
	  
	  public void findTotalRecordsAndIsbn13NotEqualsQueriedISBN(String expectedValue)
	  {
		  if(surchage.getTotalRecords()!=1)
		  {
				failureResult.add("TOTAL RECORDS NOT 1");
	       }
		  
		  try{
		       
	            for (Record record:recordList)
	            {
	            	if(record.getIsbn13()== null || record.getIsbn13().isEmpty()){
	            		failureResult.add("MISSING ISBN;");
	            	}else if(record.getIsbn13().equalsIgnoreCase(expectedValue)){
	            		failureResult.add("INCORRECT ISBN;");
	            	}
	            }
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
		  
	  }
	  
	  public void findTotalRecordsAndEmptyRecordSetArray()
	  {
		  if(surchage.getTotalRecords()!=0)
		  {
				failureResult.add("TOTAL RECORDS NOT 0");
	       }
		  if (!recordList.isEmpty()) 
		  {
	        		failureResult.add("RECORDLIST NOT EMPTY");
		  }
		  
	  }

	public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) 
	{
	 	
    	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
    	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
    	System.out.println("parentInclusion is=========="+ parentInclusion);
    	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
    	
    	
    	if(parentInclusion.equalsIgnoreCase("yes") && (relatedProductInclusion.equals("")|| relatedProductInclusion.equalsIgnoreCase("no")))
    	{
    		verifyIsbn13InRecords(expectedParentISBNValue);
    	}
    	else if(parentInclusion.equalsIgnoreCase("no") &&( relatedProductInclusion.equals("")||relatedProductInclusion.equals("no")))
    	{
    		findTotalRecordsAndEmptyRecordSetArray();
    		
    	}
    
		
	}

	public void verifyRecordForQueriedRelatedProductISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) 
	{
		System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
    	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
    	System.out.println("parentInclusion is=========="+ parentInclusion);
    	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
		
		if(parentInclusion.equals("") && (relatedProductInclusion.equalsIgnoreCase("yes") || relatedProductInclusion.equalsIgnoreCase("no")))
		{
			findTotalRecordsAndEmptyRecordSetArray();
		}
		else if(parentInclusion.equalsIgnoreCase("no") && (relatedProductInclusion.equalsIgnoreCase("yes") || relatedProductInclusion.equalsIgnoreCase("no")))
		{
			findTotalRecordsAndIsbn13NotEqualsQueriedISBN(expectedParentISBNValue);
		}
	}


	
}
