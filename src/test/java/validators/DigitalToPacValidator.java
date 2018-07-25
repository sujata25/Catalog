package validators;

import java.util.HashSet;
import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import pojo.DigitalToPac.*;
import pojo.DigitalToPac.DigitalToPac;

public class DigitalToPacValidator {
	DigitalToPac digitalPac;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
		
	
	  @SuppressWarnings("unused")
		public DigitalToPacValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        digitalPac = gson.fromJson(jsonString, DigitalToPac.class);
	        failureResult= new HashSet<String>();
	    }
	  
	  public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = digitalPac.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                System.out.println(digitalPac.getTotalRecords());
	        		if(digitalPac.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		if(relatedProductInclusion.equalsIgnoreCase("no") || relatedProductInclusion.equalsIgnoreCase("")){
	        			System.out.println(digitalPac.getTotalRecords());
	        			if(digitalPac.getTotalRecords()==null || digitalPac.getTotalRecords().equals("")) {
		   					 failureResult.add(" Total Record does not exist");
		   				}else if(digitalPac.getTotalRecords() != 1){
			        			failureResult.add("INCORRECT TOTAL RECORDS");
			        	}        		
		        		for (Record record:recordList){
		        			System.out.println(record.getIsbn13());
		        			System.out.println(record.getPacIsbn13());
		        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
				 	            		failureResult.add("INCORRECT ISBN;");
				 	             }
		        				 if(record.getPacIsbn13()== null || record.getPacIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(!record.getPacIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
		 	 	            		failureResult.add("INCORRECT PAC ISBN;");
		 	 	            	}
		        		}
	        		}
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	
	  public void verifyRecordForQueriedRelatedProductISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = digitalPac.getRecords();
	        	boolean parentFlag=false, relatedFlag=false;
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                System.out.println(digitalPac.getTotalRecords());
	        		if(digitalPac.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	       
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
        			if((relatedProductInclusion.equalsIgnoreCase("")) || (relatedProductInclusion.equalsIgnoreCase("no"))){
    	                System.out.println(digitalPac.getTotalRecords());
        				if(digitalPac.getTotalRecords()==null || digitalPac.getTotalRecords().equals("")) {
          					 failureResult.add(" Total Record does not exist");
          				 	}else if(digitalPac.getTotalRecords() != 1){
       	        			failureResult.add("INCORRECT TOTAL RECORDS;");
       	        		}
       	        		
       	        		for (Record record:recordList){
       		                	System.out.println(record.getIsbn13());
       		                	System.out.println(record.getPacIsbn13());

       	        				 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
       			            			failureResult.add("MISSING ISBN;");
       			            	 }else if(record.getIsbn13().equals(expectedParentISBNValue)) {
       			            		parentFlag=true;
       	        				 }if(record.getPacIsbn13()== null || record.getPacIsbn13() == ""){
       			            			failureResult.add("MISSING ISBN;");
       			            	 }else if(record.getPacIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
       	 	 	            		relatedFlag=true;
       	 	 	            	 }
       	        		}
       	        		
       	        		if(!parentFlag && relatedFlag){
       	        			failureResult.add("INCORRECT PARENT ISBN;");
       	        		}else if(parentFlag && !relatedFlag){
       	        			failureResult.add("INCORRECT RELATED PRODUCT ISBN;");
       	        		}else if(!parentFlag && !relatedFlag){
       	        			failureResult.add("INCORRECT PARENT ISBN; INCORRECT RELATED PRODUCT ISBN;");
       	        		}
       	        		
        			}	
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  
}
