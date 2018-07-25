package validators;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import pojo.CoursewareSurcharge.*;

import pojo.CoursewareSurcharge.Record;
import pojo.CoursewareSurcharge.Surchage;
import pojo.CuBundles.CuBundles;

import java.util.HashSet;
import java.util.List;


public class SurchageValidator 
{
	Surchage surchase;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
		
	
	  @SuppressWarnings("unused")
		public SurchageValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        surchase = gson.fromJson(jsonString, Surchage.class);
	        failureResult= new HashSet<String>();
	    }
	  
	  public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = surchase.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                System.out.println(surchase.getTotalRecords());
	        		if(surchase.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	       
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		boolean parentFlag=false,relatedFlag=false;
	        			 if((relatedProductInclusion.equalsIgnoreCase("no")) || (relatedProductInclusion.equalsIgnoreCase(""))){
	    	        		 for (Record record:recordList){
		        				 System.out.println(record.getIsbn13());
	    	        			 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().equals(expectedParentISBNValue)) {
				            		 parentFlag=true;
		        				 }
		        				 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().equals(expectedRelatedProductISBNValue)) {
		        					 relatedFlag=true;
		        				 }
	    	        		 }
	    	        		 if(!parentFlag && relatedFlag){
				 	            	failureResult.add("INCORRECT ISBN;");
				 	         }else  if(!parentFlag && !relatedFlag){
				 	            	failureResult.add("INCORRECT ISBN;");
				 	         }
	    	        		 
	        			 }
	        		
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	
	  public void verifyRecordForQueriedRelatedProductISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = surchase.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("yes") && relatedProductInclusion.equalsIgnoreCase("")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase(""))){
	                if(surchase.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	       
	    	     }
	        	
	        	if(relatedProductInclusion.equalsIgnoreCase("no")){
	        		boolean parentFlag=false,relatedFlag=false;
       			 	if((parentInclusion.equalsIgnoreCase("yes")) || (parentInclusion.equalsIgnoreCase("no"))){
       			 		 if(surchase.getTotalRecords() != 1) {
		   					 failureResult.add("TOTAL RECORDS MISMATCH;");
		   				 }
		        		 for (Record record:recordList){
		        			 System.out.println("getIsbn13 is=======>" + record.getIsbn13());
		        				 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().contains(expectedRelatedProductISBNValue)) {
		        					 failureResult.add("PRODUCT ISBN EXIST IN THE RECORD");
		        				 }
		        		}
       			 	} 
	        		
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  
}
