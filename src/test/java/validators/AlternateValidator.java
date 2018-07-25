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


public class AlternateValidator {

	 AlternateProduct alternateProduct;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
		
	
	  @SuppressWarnings("unused")
		public AlternateValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        alternateProduct = gson.fromJson(jsonString, AlternateProduct.class);
	        failureResult= new HashSet<String>();
	    }
	  
	  public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = alternateProduct.getRecords();
	        	boolean parentFlag=false,relatedFlag=false;
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
	        			 if((relatedProductInclusion.equalsIgnoreCase("yes")) || 
	        					 (relatedProductInclusion.equalsIgnoreCase("no")) ||
	        					 (relatedProductInclusion.equalsIgnoreCase(""))){
	        				 for (Record record:recordList){
	        					 System.out.println("isbn  " + record.getIsbn13());
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
	        	List<Record> recordList = alternateProduct.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
        		boolean parentFlag=false,relatedFlag=false;

	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase(""))){
	                System.out.println(alternateProduct.getTotalRecords());
	        		if(alternateProduct.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	    }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		 	 if(relatedProductInclusion.equalsIgnoreCase("yes")){
	        		 		 	System.out.println(alternateProduct.getTotalRecords());
	        				 	if(alternateProduct.getTotalRecords() != 2){
	        				 		failureResult.add("TOTAL RECORDS NOT 2;");
	        				 	}
	        				    for (Record record:recordList){
		        		 		 	System.out.println("1 :" + record.getIsbn13());
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
	        				    if(!parentFlag && relatedFlag){
				 	            	failureResult.add("INCORRECT ISBN");
				 	            }else if(parentFlag && !relatedFlag){
				 	            	failureResult.add("INCORRECT ISBN");
				 	            }else if(!parentFlag && !relatedFlag){
				 	            	failureResult.add("INCORRECT ISBN");
				 	            }
			 	     	 }if((relatedProductInclusion.equalsIgnoreCase("no")) || (relatedProductInclusion.equalsIgnoreCase(""))){
	        				 for (Record record:recordList){
		        		 		 System.out.println("2 :" + record.getIsbn13());
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
	        				 
	        				  if(!parentFlag && relatedFlag){
				 	            	failureResult.add("INCORRECT ISBN");
				 	          }else if(!parentFlag && !relatedFlag){
				 	            	failureResult.add("INCORRECT ISBN");
				 	          }
	        				 
	        			 }
	        		
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  
}
