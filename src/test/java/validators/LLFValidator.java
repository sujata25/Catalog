package validators;

import com.google.gson.Gson;

import pojo.CoursewareLLF.CoursewareToLLF;
import pojo.CoursewareLLF.Record;


import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONObject;

import java.util.HashSet;
import java.util.List;


public class LLFValidator {

	 CoursewareToLLF llf;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
		
	
	  @SuppressWarnings("unused")
		public LLFValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        llf = gson.fromJson(jsonString, CoursewareToLLF.class);
	        failureResult= new HashSet<String>();
	    }
	  
	  public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = llf.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	boolean parentFlag=false, relatedFlag=false;
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                System.out.println("llf total recorDS :" + llf.getTotalRecords());
	        		if(llf.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		 	 if((relatedProductInclusion.equalsIgnoreCase("")) || 
	        					 (relatedProductInclusion.equalsIgnoreCase("no"))){
	        				 for (Record record:recordList){
	        					 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
				 	            	parentFlag=true;
				 	             }
		        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
		 	 	            		relatedFlag=true;
		 	 	            	 }
		        			 }
	        				 System.out.println("parent flag===> " + parentFlag + " relatedFlag is====> " + relatedFlag);
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
	        	List<Record> recordList = llf.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("yes") && relatedProductInclusion.equalsIgnoreCase("")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase(""))){
	                System.out.println(llf.getTotalRecords());
	        		if(llf.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	       
	    	     }
	        	
	        	if(relatedProductInclusion.equalsIgnoreCase("no")){
	        		boolean parentFlag=false,relatedFlag=false;
	       			 if((parentInclusion.equalsIgnoreCase("yes")) || (parentInclusion.equalsIgnoreCase("no"))){
	       				 System.out.println(llf.getTotalRecords());
	       				 if(llf.getTotalRecords() != 1) {
	      					 failureResult.add("TOTAL RECORD MISMATCH;");
	      				 	}
	   	        		 for (Record record:recordList){
	   	        			 System.out.println("getIsbn13 is=======>" + record.getIsbn13());
	   	        				 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
	   			            			failureResult.add("MISSING ISBN;");
	   			            	 }else if(record.getIsbn13().equals(expectedRelatedProductISBNValue)) {
	   	        					 failureResult.add("INCORRECT RELATED PRODUCT ISBN;");
	   	        				 }
	   	        		}
	       			 }
	        		
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  
}
