package validators;

import java.util.HashSet;
import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import pojo.CuBundles.*;

import pojo.CuBundles.CuBundles;

public class CuBundlesValidator {
	CuBundles cubundles;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
		
	
	  @SuppressWarnings("unused")
		public CuBundlesValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        cubundles = gson.fromJson(jsonString, CuBundles.class);
	        failureResult= new HashSet<String>();
	    }
	  
	
	  public void verifyRecordForQueriedRelatedProductISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = cubundles.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("") && relatedProductInclusion.equalsIgnoreCase("yes")) ||
	    	        (parentInclusion.equalsIgnoreCase("") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                if(cubundles.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	       
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("no")){
	        		boolean parentFlag=false,relatedFlag=false;
	        		 for (Record record:recordList){
	        			 System.out.println("getIsbn13 is=======>" + record.getIsbn13());
	        			 if(relatedProductInclusion.equalsIgnoreCase("yes")){
	        				 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
			            			failureResult.add("MISSING ISBN;");
			            	 }/*else if((!record.getIsbn13().contains(expectedParentISBNValue)) && (!record.getIsbn13().contains(expectedRelatedProductISBNValue))){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }*/
	        				 else if(record.getIsbn13().equals(expectedRelatedProductISBNValue)) {
	        					 failureResult.add("PRODUCT ISBN EXIST IN THE RECORD");
	        				 }
	        				 
	        				 if(cubundles.getTotalRecords()==null || cubundles.getTotalRecords().equals("")) {
	        					 failureResult.add(" Total Record does not exist");
	        				 }
	        				 
	        				 else if(!cubundles.getTotalRecords().equals(1)) {
	        					 failureResult.add("Total record missmatch");
	        				 }
	        				 
	        				 
	        			 }if(relatedProductInclusion.equalsIgnoreCase("no")){
	        				 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
			            			failureResult.add("MISSING ISBN;");
			            	 }/*else if((!record.getIsbn13().contains(expectedParentISBNValue)) && (!record.getIsbn13().contains(expectedRelatedProductISBNValue))){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }*/
	        				 else if(record.getIsbn13().equals(expectedRelatedProductISBNValue)) {
	        					 failureResult.add("PRODUCT ISBN EXIST IN THE RECORD");
	        				 }
	        				 
	        				 if(cubundles.getTotalRecords()==null || cubundles.getTotalRecords().equals("")) {
	        					 failureResult.add(" Total Record does not exist");
	        				 }
	        				 
	        				 else if(!cubundles.getTotalRecords().equals("1")) {
	        					 failureResult.add("Total record missmatch");
	        				 }	        				
	        			 }
	        		}
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  
}
