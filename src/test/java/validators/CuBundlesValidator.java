package validators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import pojo.CuBundles.*;

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
	  
	
	  public void verifyRecordForQueriedRelatedProductISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion,HashMap<String,List<String>> isbnvalues) {
		  try{
			    int totalparentisbn = 0 ;
			    int j = 0,i = 0,k=0;
	        	boolean valueflag = false, sizeFlag=false;
	        	List<Record> Isbn13 = cubundles.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	System.out.println(" vallues are ==="+ isbnvalues.values());
	        	List<String> isbns_from_excel= new ArrayList<String>();
	        	 List<String> isbns_from_response= new ArrayList<String>();
	        	 List<Record> recordList = cubundles.getRecords();
	        	 if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("")) ||
	 	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no")) ||
	 	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("yes"))	){
	        		 for(Record record:recordList){
	        			 if(record.getIsbn13()== null || record.getIsbn13() == ""){
		            			failureResult.add("MISSING ISBN;");
		            	 }else if(record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
		 	            		failureResult.add("INCORRECT ISBN;");
		 	             }
	 	             }
	        		 
	 	               
	 	    	 }else if((parentInclusion.equalsIgnoreCase("yes") && relatedProductInclusion.equalsIgnoreCase("")) ||
		 	    	        (parentInclusion.equalsIgnoreCase("yes") && relatedProductInclusion.equalsIgnoreCase("no")) ||
		 	    	        (parentInclusion.equalsIgnoreCase("yes") && relatedProductInclusion.equalsIgnoreCase("yes"))	){
	 	    		//iterating over values only
	 	        	
	 	        	for (List<String> value : isbnvalues.values()) {
	 	        	    totalparentisbn = value.size();
	 	        	    System.out.println(value.size());
	 	        	    for (i = 0; i < value.size(); i++) {
	 	        	        System.out.println(value.get(i));
	 	        	        isbns_from_excel.add(value.get(i));
	 	        	    }
	 	        	}

	 	             if(cubundles.getTotalRecords() != totalparentisbn){
	 		        		failureResult.add("TOTAL RECORDS NOT EQUAL TO PARENT ");
	 		        }
	 	             
	 	             for(Record record:recordList){
	 	            	 isbns_from_response.add(record.getIsbn13());
	 	             }
	 	             
	 	             Collections.sort(isbns_from_excel);
	 	             Collections.sort(isbns_from_response);
	 	             System.out.println("isbns_from_excel--"+isbns_from_excel);
	 	            System.out.println("isbns_from_response--"+isbns_from_response);
	 	            
	 	           /* if(isbns_from_excel.size() != isbns_from_response.size()) {
	 	            	failureResult.add("PARENT ISBN COUNT AND TOTAL RECORD COUNT IS NOT EQUAL");
	 	            } */
	 	            
	 	           
	 	            for(int m=0;m<isbns_from_excel.size();m++) {
	 	            	if(!(isbns_from_excel.get(m).equals(isbns_from_response.get(m)))) {
	 	            		failureResult.add("PARENT ISBN DOES NOT MATCH ISBN13");
	 	            	}
	 	            }
		 	     }
	        	 
	        	 
	        	 
	        	

	        	
	        	
	        }catch(Exception e) {
	        	System.out.println("verify record ");
	            e.printStackTrace();
	        }
	  }
	  
	  
}
