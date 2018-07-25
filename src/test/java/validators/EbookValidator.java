package validators;

import com.google.gson.Gson;

import pojo.CoursewareEbook.CoursewareEbook;
import pojo.CoursewareEbook.Record;
import utils.LoggerImplemetation;

import org.apache.commons.logging.Log;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.simple.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;


public class EbookValidator {

	 CoursewareEbook ebook;
	 
	 HashSet<String> failureResult;
		
		public  HashSet<String> failureResult() {
			return failureResult;
		}
			
	   @SuppressWarnings("unused")
		public EbookValidator(JSONObject jsonObject) {
	        String jsonString = jsonObject.toJSONString();
	        ResponseHandler<String> handler = new BasicResponseHandler();
	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
	        ebook = gson.fromJson(jsonString, CoursewareEbook.class);
	        failureResult= new HashSet<String>();
	        
	    }
	  
	  public void verifyRecordForQueriedParentISBN(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
	        try{
	        	List<Record> recordList = ebook.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("yes")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                System.out.println(ebook.getTotalRecords());
	        		if(ebook.getTotalRecords() != 0){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		 String cuEbookISBN = null ;
	        		 for (Record record:recordList){
       
	        			 if(relatedProductInclusion.equalsIgnoreCase("yes")){
		        			 cuEbookISBN = record.getCuEBookIsbns().toString().substring(1,record.getCuEBookIsbns().toString().length()-1 );	  
		        			 System.out.println("getCuEBookIsbns is====>"+ cuEbookISBN);
	        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }
	        				 if(record.getCuEBookIsbns() == null || record.getCuEBookIsbns().isEmpty()){
	 	            			failureResult.add("MISSING CUEBOOKS;");
	 	            		}else if(!cuEbookISBN.equalsIgnoreCase(expectedRelatedProductISBNValue)){
	 	 	            		failureResult.add("INCORRECT CUEBOOKS;");
	 	 	            	}
	        			 }if(relatedProductInclusion.equalsIgnoreCase("no")){
	        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
			            			failureResult.add("MISSING ISBN;");
			            	 }else if(!record.getIsbn13().equalsIgnoreCase(expectedParentISBNValue)){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }
	        				 if(record.getCuEBookIsbns() != null){
	 	 	            		failureResult.add("RELATED PRODUCT ISBN AVAILABLE;");
	 	 	            	 }
	        			 }
	        		}
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	
	  public void verifyRelatedProductIsbn13InRecords(String expectedParentISBNValue,String expectedRelatedProductISBNValue, String parentInclusion, String relatedProductInclusion) {
	        try{
	        	List<Record> recordList = ebook.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("yes") && relatedProductInclusion.equalsIgnoreCase("no")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                if(!ebook.getTotalRecords().toString().equals("0")){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	       
	    	     }
	        	
	        	if(relatedProductInclusion.equalsIgnoreCase("yes")){
	        		boolean parentFlag=false,relatedFlag=false;
	        			 if(parentInclusion.equalsIgnoreCase("yes")){
	        				 for (Record record:recordList){
		        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().contains(expectedParentISBNValue)){
		        					  parentFlag =true;
				 	             } 
		        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().contains(expectedRelatedProductISBNValue)){
				 	            	relatedFlag =true;
				 	             }
		        				 
	        				 }
	        				 if(!parentFlag && relatedFlag){
				 	            failureResult.add("INCORRECT PARENT ISBN;");
				 	         }else  if(parentFlag && !relatedFlag){
					 	        failureResult.add("INCORRECT RELATED PRODUCT ISBN;");
				 	         }else if(!parentFlag && !relatedFlag){
			 	            	failureResult.add("INCORRECT ISBN;");
			 	             }
	        			 }if(parentInclusion.equalsIgnoreCase("no")){
	        				 for (Record record:recordList){
	    	        			 System.out.println("2 getIsbn13 is=======>" + record.getIsbn13());
		        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(record.getIsbn13().contains(expectedParentISBNValue)){
				            		 parentFlag =true;
				 	             }
		        				 if(record.getIsbn13()== null || record.getIsbn13() == ""){
				            			failureResult.add("MISSING ISBN;");
				            	 }else if(!record.getIsbn13().contains(expectedRelatedProductISBNValue)){
				            		 relatedFlag =true;
				 	             }
		        			 } 

	        				 if(parentFlag && !relatedFlag){
						 	        failureResult.add("INCORRECT ISBN;");
					 	     }else if(!parentFlag && !relatedFlag){
				 	            	failureResult.add("INCORRECT RELATED ISBN;");
				 	         }
	        			 }
	        		
	        	}
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
}
