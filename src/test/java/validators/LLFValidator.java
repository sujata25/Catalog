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
		
		HashSet<String> set=new HashSet<String>();
	
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
	        	
	        	if((parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("")) ||
	    	        (parentInclusion.equalsIgnoreCase("no") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                if(!llf.getTotalRecords().toString().equals("0")){
	    	        		failureResult.add("TOTAL RECORDS NOT 0;");
	    	        }if (!recordList.isEmpty()) {
	    	        		failureResult.add("RECORDLIST NOT EMPTY;");
					}
	    	     }
	        	
	        	if(parentInclusion.equalsIgnoreCase("yes")){
	        		 for (Record record:recordList){
	        			 String cuEbookISBN = record.getCuEBookIsbns().toString().substring(1,record.getCuEBookIsbns().toString().length()-1 );
	        			 if(relatedProductInclusion.equalsIgnoreCase("")){
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
	 	            		else if(record.getIsbn13().equalsIgnoreCase(expectedRelatedProductISBNValue)){
	 	 	            		failureResult.add("INCORRECT CUEBOOKS;");
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
	        	List<Record> recordList = llf.getRecords();
	        	System.out.println("expectedParentISBNValue is==========="+ expectedParentISBNValue);
	        	System.out.println("expectedRelatedProductISBNValue is=========="+ expectedRelatedProductISBNValue);
	        	System.out.println("parentInclusion is=========="+ parentInclusion);
	        	System.out.println("relatedProductInclusion is==========="+ relatedProductInclusion);
	        	
	        	if((parentInclusion.equalsIgnoreCase("") && relatedProductInclusion.equalsIgnoreCase("yes")) ||
	    	        (parentInclusion.equalsIgnoreCase("") && relatedProductInclusion.equalsIgnoreCase("no"))){
	                if(!llf.getTotalRecords().toString().equals("0")){
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
	        				 System.out.println("expec rec 1 "+ (!record.getIsbn13().contains(expectedParentISBNValue)));
	        				 System.out.println("rec 2 "+ (!record.getIsbn13().contains(expectedRelatedProductISBNValue)));
	        				 if(record.getIsbn13()== null || record.getIsbn13().equals("")){
			            			failureResult.add("MISSING ISBN;");
			            	 }/*else if((!record.getIsbn13().contains(expectedParentISBNValue)) && (!record.getIsbn13().contains(expectedRelatedProductISBNValue))){
			 	            		failureResult.add("INCORRECT ISBN;");
			 	             }*/
	        				 else if(record.getIsbn13().equals(expectedRelatedProductISBNValue)) {
	        					 failureResult.add("PRODUCT ISBN EXIST IN THE RECORD");
	        				 }
	        				 
	        				 if(llf.getTotalRecords()==null || llf.getTotalRecords().equals("")) {
	        					 failureResult.add(" Total Record does not exist");
	        				 }
	        				 
	        				 else if(!llf.getTotalRecords().equals(1)) {
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
	        				 
	        				 if(llf.getTotalRecords()==null || llf.getTotalRecords().equals("")) {
	        					 failureResult.add(" Total Record does not exist");
	        				 }
	        				 
	        				 else if(!llf.getTotalRecords().equals("1")) {
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
