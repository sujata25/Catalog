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
		
		HashSet<String> set=new HashSet<String>();
	
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
	                if(!alternateProduct.getTotalRecords().toString().equals("0")){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		 for (Record record:recordList){
	        			 String cuEbookISBN = record.getCuEBookIsbns().toString().substring(1,record.getCuEBookIsbns().toString().length()-1 );
	        			 if(relatedProductInclusion.equalsIgnoreCase("yes")){
	        				 System.out.println("getCuEBookIsbns is====>"+ cuEbookISBN);
	        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }
	        				
	 	            		else if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
	 	 	            		failureResult.add("INCORRECT CUEBOOKS;");
	 	 	            	}
	        			 }if(relatedProductInclusion.equalsIgnoreCase("no")){
	        				 System.out.println("getCuEBookIsbns is====>"+ cuEbookISBN);
	        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }
	        				
	 	            		 if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
	 	 	            		failureResult.add("INCORRECT ISBN;");
	 	 	            	}
	        			 }
	        			 if(relatedProductInclusion.equalsIgnoreCase(" ")){
	        				 System.out.println("getCuEBookIsbns is====>"+ cuEbookISBN);
	        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }
	        				
	 	            		else if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
	 	 	            		failureResult.add("INCORRECT ISBN;");
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
	    	        (parentInclusion.equalsIgnoreCase(" ") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                if(!alternateProduct.getTotalRecords().toString().equals("0")){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	       
	    	     }
	        	
	        	if(relatedProductInclusion.equalsIgnoreCase("yes")){
	        		boolean parentFlag=false,relatedFlag=false, totalRecord = false;
	        		
	        		 for (Record record:recordList){
	        			
	        			 System.out.println("getIsbn13 is=======>" + record.getIsbn13());
	        			 if(parentInclusion.equalsIgnoreCase("yes")){
	        				 System.out.println("expec rec 1 "+ (!record.getIsbn13().contains(expectedParentISBNValue)));
	        				 System.out.println("rec 2 "+ (!record.getIsbn13().contains(expectedRelatedProductISBNValue)));
	        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }/*else if((!record.getIsbn13().contains(expectedParentISBNValue)) && (!record.getIsbn13().contains(expectedRelatedProductISBNValue))){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }*/
	        				  if(record.getIsbn13().contains(expectedParentISBNValue)){
	        					  parentFlag =true;
			 	             } if(record.getIsbn13().contains(expectedRelatedProductISBNValue)){
			 	            	relatedFlag =true;
			 	             }
			 	             
			 	             if(alternateProduct.getTotalRecords() == 2) {
			 	            	 totalRecord = true;
			 	             }
			 	            
			 	             
	        				 
			 	             if(!(parentFlag || relatedFlag && totalRecord)){
			 	            	failureResult.add("MISSING ISBN and total records is missing");
			 	             }
	        				 
	        			 }if(parentInclusion.equalsIgnoreCase("no")){
	        				
	        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }
	        				
	 	            		 if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
	 	 	            		failureResult.add("INCORRECT ISBN;");
	 	 	            	}
	        			 }
	        			 
	        			 if(parentInclusion.equalsIgnoreCase(" ")){
	        				 
	        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }
	        				
	 	            		 if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
	 	 	            		failureResult.add("INCORRECT ISBN;");
	 	 	            	}
	        			 }
	        			 
	        		}
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  
}
