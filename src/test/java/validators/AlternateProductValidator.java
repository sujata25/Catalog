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
		
	
	  @SuppressWarnings("unused")
		public AlternateProductValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        alternateProduct = gson.fromJson(jsonString, AlternateProduct.class);
	        failureResult= new HashSet<String>();
	    }
	  
	  public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = alternateProduct.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase(""))){
	                if(alternateProduct.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		 for (Record record:recordList){
	        			 if((relatedProductInclusion.equalsIgnoreCase("yes")) || 
	        					 (relatedProductInclusion.equalsIgnoreCase("no")) ||
	        					 (relatedProductInclusion.equalsIgnoreCase(" "))){
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
	        	List<Record> recordList = alternateProduct.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase(" "))){
	                if(!alternateProduct.getTotalRecords().toString().equals("0")){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	       
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		boolean parentFlag=false,relatedFlag=false, totalRecord = false;
	        		 
	        			 if(relatedProductInclusion.equalsIgnoreCase("yes")){
	        				 if(!(parentFlag || relatedFlag && totalRecord)){
				 	            	failureResult.add("MISSING ISBN and total records is missing");
				 	             }if(alternateProduct.getTotalRecords().equals(2)) {
					 	            	failureResult.add("TOTAL RECORDS MISSING");
					 	         }
	        				 
				 	            for (Record record:recordList){
			        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
					            			failureResult.add("MISSING ISBN;");
					            	 }else if(record.getIsbn13().contains(expectedParentISBNValue)){
			        					  parentFlag =true;
					 	             }
			        				 
			        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
					            			failureResult.add("MISSING ISBN;");
					            	 }else  if(record.getIsbn13().contains(expectedRelatedProductISBNValue)){
					 	            	relatedFlag =true;
					 	             }
				 	            }
			 	             
				 	             
			 	         	 
	        			 }if((relatedProductInclusion.equalsIgnoreCase("no")) || (relatedProductInclusion.equalsIgnoreCase(" "))){
	        				 for (Record record:recordList){
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
	        			 
	        			 
	        		//}
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  
}
