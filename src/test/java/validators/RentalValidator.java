package validators;

import com.google.gson.Gson;

import pojo.CoursewareRental.Record;
import pojo.CoursewareRental.Rental;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONObject;

import java.util.HashSet;
import java.util.List;


public class RentalValidator {

	 Rental rental;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
		
	
		@SuppressWarnings("unused")
		public RentalValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        rental = gson.fromJson(jsonString, Rental.class);
	        failureResult= new HashSet<String>();
	    }
	  
	  public void verifyIsbn13InRecords(String expectedValue) {
	        try{
	        	List<Record> recordList = rental.getRecords();
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
	  
	  public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = rental.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                if(rental.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		for (Record record:recordList){
       			
	        			if((relatedProductInclusion.equalsIgnoreCase("")) || (relatedProductInclusion.equalsIgnoreCase("no"))){
	        				if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
			 	            		failureResult.add("INCORRECT PARENT ISBN;");
			 	             }
	        				if(record.getIsbn13()== null || record.getIsbn13() == ""){
		            			failureResult.add("MISSING ISBN;");
	        				}else if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
		 	            		failureResult.add("INCORRECT RELATED PRODUCT ISBN;");
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
	        	List<Record> recordList = rental.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("yes") && relatedProductInclusion.equalsIgnoreCase("") ) ||
		    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase(""))){
		                if(rental.getTotalRecords() != 0){
		    	        		failureResult.add("TOTAL RECORDS NOT 0;");
		    	        }if (!recordList.isEmpty()) {
		    	        		failureResult.add("RECORDLIST NOT EMPTY;");
						}
		    	       
		    	     }
		        	
		        	if(relatedProductInclusion.equalsIgnoreCase("no")){
		        		boolean parentFlag=false,relatedFlag=false;
		        		 for (Record record:recordList){
		        			 System.out.println("getIsbn13 is=======>" + record.getIsbn13());
		        			 if((parentInclusion.equalsIgnoreCase("yes")) || (parentInclusion.equalsIgnoreCase("no")) ){
		        				 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(!record.getIsbn13().equals(expectedRelatedProductISBNValue)) {
		        					 failureResult.add("PRODUCT ISBN EXIST IN THE RECORD");
		        				 }
		        				 
		        				 if(rental.getTotalRecords()==null || rental.getTotalRecords().equals("")) {
		        					 failureResult.add(" Total Record does not exist");
		        				 }else if(!rental.getTotalRecords().equals(1)) {
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
