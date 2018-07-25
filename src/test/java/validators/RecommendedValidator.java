package validators;

import java.util.HashSet;
import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import pojo.RecommendedProduct.RecommendedProduct;
import pojo.RecommendedProduct.Record;

public class RecommendedValidator {
	RecommendedProduct recommendedProduct;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
		
	
	  @SuppressWarnings("unused")
		public RecommendedValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        recommendedProduct = gson.fromJson(jsonString, RecommendedProduct.class);
	        failureResult= new HashSet<String>();
	    }
	  
	  public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
			  int countCUIsbn=0;
			  boolean parentFlag=false,relatedFlag=false;
	        	List<Record> recordList = recommendedProduct.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase(""))||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("yes"))
	    	        ){
	        			System.out.println(recommendedProduct.getTotalRecords());
		                if(recommendedProduct.getTotalRecords() != 0){
		    	        	failureResult.add("TOTAL RECORDS NOT 0;");
		    	        }if (!recordList.isEmpty()) {
		    	        	failureResult.add("RECORDLIST NOT EMPTY;");
						}
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        			 if(relatedProductInclusion.equalsIgnoreCase("no")){
	        				 for (Record record:recordList){
	        					 System.out.println(record.getIsbn13());
	        					 System.out.println(record.getCuRecIsbns());

	        					 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
				 	            		parentFlag=true;
				 	             }
		        				 if(record.getCuRecIsbns().isEmpty() || record.getCuRecIsbns()== null || record.getCuRecIsbns().toString() == "") {
		        					 failureResult.add("MISSING ISBN;");
		        				 }else if(record.getCuRecIsbns().toString().contains(expectedRelatedProductISBNValue)){
		        					 relatedFlag=true;
		 	 	            	 }
	        				 }
	        				 
	        				 if(!parentFlag && relatedFlag){
	        					 failureResult.add("INCORRECT ISBN");
	        				 }else if(!parentFlag && !relatedFlag){
	        					 failureResult.add("INCORRECT PARENT ISBN AND RELATEDPRODUCT ISBN AVAILABLE;");
	        				 }
	        			 }else if((relatedProductInclusion.equalsIgnoreCase("yes")) || (relatedProductInclusion.equalsIgnoreCase(""))){
	        				 for (Record record:recordList){
		        				 System.out.println("getCuRecIsbns is ====>" + record.getCuRecIsbns());
	        					 if(record.getCuRecIsbns().isEmpty() || record.getCuRecIsbns()== null || record.getCuRecIsbns().toString() == "") {
	        						 failureResult.add("MISSING ISBN;");
		        				 }else if(record.getCuRecIsbns().toString().contains(expectedRelatedProductISBNValue)){
		        					 countCUIsbn++;
		 	 	            	 }
	        				 }
	        				 System.out.println("countCUIsbn is ====>" + countCUIsbn);
	        				 if(countCUIsbn < 1){
	        					 failureResult.add("ISBN DOES NOT EXIST IN RECORDS;");
	        				 }
        					 else if(countCUIsbn > 1){
	        					 failureResult.add("MULTIPLE CUREC ISBN IN RECORDS;");
	        				 }
	        			 }
	        	}	        
	      
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	
	  public void verifyRecordForQueriedRelatedProductISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
		  try{
	        	List<Record> recordList = recommendedProduct.getRecords();
	        	boolean parentFlag=false,relatedFlag=false;
				 
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if (parentInclusion.equalsIgnoreCase("no")){
	        		if(relatedProductInclusion.equalsIgnoreCase("no")){
	        			if(recommendedProduct.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
		    	        }if (!recordList.isEmpty()) {
		    	        		failureResult.add("RECORDLIST NOT EMPTY;");
						}
	        		}else if(relatedProductInclusion.equalsIgnoreCase("yes")){
	        			 for (Record record:recordList){
	        				 System.out.println(record.getIsbn13());
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
	        			 if(parentFlag && !relatedFlag){
			 	            	failureResult.add("INCORRECT ISBN;");
			 	         }else if(!parentFlag && !relatedFlag){
			 	            	failureResult.add("INCORRECT ISBN;");
			 	         }
	        		}
	        		// to be checked again
	        		else if(relatedProductInclusion.equalsIgnoreCase("")){
	        			if(recommendedProduct.getTotalRecords() !=0){
	        				for (Record record:recordList){
	        					if(record.getCuRecIsbns().isEmpty() || record.getCuRecIsbns()== null ){
			            			failureResult.add("MISSING ISBN;");
				            	}else if(!record.getCuRecIsbns().contains(expectedRelatedProductISBNValue)){
				 	            		failureResult.add("INCORRECT RELATED PRODUCT ISBN;");
				 	            }
	        				}
	    	        	}
	        			// to be checked
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
	        			if(parentFlag && !relatedFlag){
			 	            failureResult.add("INCORRECT ISBN;");
			 	        }if(!parentFlag && !relatedFlag){
		 	            	failureResult.add("INCORRECT ISBN;");
		 	        }
	        			
	        			
	        		}
	        	}
	        	
	        	
	        	else if(parentInclusion.equalsIgnoreCase("yes")){
	        			 if(relatedProductInclusion.equalsIgnoreCase("yes")){
	        				 System.out.println(recommendedProduct.getTotalRecords());
	        				 if(recommendedProduct.getTotalRecords() == null || recommendedProduct.getTotalRecords().toString() == ""){
			            			failureResult.add("MISSING TOTAL RECORDS;");
			            	 }
	        				 else if(recommendedProduct.getTotalRecords() != 2 || recommendedProduct.getTotalRecords() < 2 ){
	        					 failureResult.add("RECORD COUNT NOT CORRECT");
	        				 }
	        				 for (Record record:recordList){
	        					 System.out.println(record.getIsbn13());
				 	             if((record.getIsbn13()== null || record.getIsbn13() == "")) {
				 	            	 failureResult.add("MISSING ISBN");
			        			 } else if(record.getIsbn13().toString().contains(expectedRelatedProductISBNValue)) {
			        				  relatedFlag=true;
			        			 }
				 	             if((record.getIsbn13()== null || record.getIsbn13() == "")) {
				 	            	 failureResult.add("MISSING ISBN");
			        			 } else if(record.getIsbn13().toString().contains(expectedParentISBNValue)) {
			        				 parentFlag=true;
			        			 }
				 	         }

		 	                 if(!parentFlag && !relatedFlag){
		 	                	failureResult.add("INCORRECT PARENT ISBN; INCORRECT RELATED PRODUCT ISBN;");
		 	                 }else if(!parentFlag && relatedFlag){
			 	                failureResult.add("MISSING PARENT ISBN;");
			 	             }else if(parentFlag && !relatedFlag){
				 	             failureResult.add("MISSING RELATED PRODUCT ISBN;");
			 	             }
			 	            
			 	         }
	        			 else if(relatedProductInclusion.equalsIgnoreCase("no")){
	        				 System.out.println(recommendedProduct.getTotalRecords());
	        				 if(recommendedProduct.getTotalRecords() !=0){
	 	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	 		    	         }
	 		    	        
	        				 for (Record record:recordList){
	        					 System.out.println(record.getIsbn13());
		        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)) {
				            		 failureResult.add("INCORRECT RELATED PRODUCT ISBN;");
				            	 }
			            	 }
	        				
	        			 }else if(relatedProductInclusion.equalsIgnoreCase("")){
	        				 if(recommendedProduct.getTotalRecords() < 1){
	 	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	 		    	        }
	        				 for (Record record:recordList){
				 	             if((record.getIsbn13()== null || record.getIsbn13() == "")) {
				 	            	 failureResult.add("MISSING ISBN;");
			        			 } else if(record.getIsbn13().toString().contains(expectedRelatedProductISBNValue)) {
			        				  relatedFlag=true;
			        			 }
				 	             if((record.getIsbn13()== null || record.getIsbn13() == "")) {
				 	            	 failureResult.add("MISSING ISBN;");
			        			 } else if(record.getIsbn13().toString().contains(expectedParentISBNValue)) {
			        				 parentFlag=true;
			        			 }
				 	        }
	        				 
		 	                 if(!parentFlag && relatedFlag){
		 	                	 failureResult.add("INCORRECT PARENT ISBN;");
		 	                 }else if(!parentFlag && !relatedFlag){
		 	                	 failureResult.add("INCORRECT PARENT ISBN;INCORRECT RELATED PRODUCT ISBN;");
		 	                 }
	        				
	        			 }
	        		
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  
}
