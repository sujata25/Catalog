package tests;

import java.util.HashSet;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.APIExecutor;
import utils.CURelationshipIndex;
import utils.ExcelUtils;
import utils.UpdateExcelSheet;
import validators.EbookValidator;
import validators.RentalValidator;

public class CoursewareRental_RelatedProductISBN {
	static int testCount = 0;
	String inputFilePath;
	String sheetName;
	String endPoint;
	int startRow, startCol, totalCols, maxRows;
	String reportFilePath;
	String reportSheetName;
	//Logger logger = LoggerImplemetation.logConfig("CoursewareEBK_RelatedProductISBN.class");
	@BeforeClass
	public void init_vars(){
		sheetName = "CoursewareToRental";
		startCol = 0;
		totalCols = 8;
		endPoint = "Related Product";
		startRow=1;
		maxRows=3;
		inputFilePath="D:\\Project\\CU Catalog\\Files\\19_july_2018\\cucatalog.xlsx";
		reportFilePath =  "D:\\test.xlsx";
		reportSheetName = "Rental_RelatedProduct";
		UpdateExcelSheet.createFile(reportFilePath, reportSheetName);
	}
	
	@DataProvider
	public Object[][] Authentication() throws Exception{
		ExcelUtils excelUtils = new ExcelUtils(inputFilePath, sheetName);
		List<List<String>> testObjArray = excelUtils.getTableArray(startRow, maxRows);
		Object [][] o=new Object[testObjArray.size()][];
		for(int itr=0;itr<testObjArray.size();itr++){
			List<String> list=testObjArray.get(itr);
			o[itr]=new Object[1];
			o[itr][0]=list.toArray();
			
		}
		return (o);
	}
	
	 @Test(dataProvider="Authentication")
	  public void Test01EBKRelatedProductISNB(Object[] varArg)throws  Exception{
		 String [] list=new String [varArg.length];
		 for(int i=0; i<varArg.length;i++){
			 list[i]=varArg[i].toString();
		 }
		  testCount++;
		  boolean noresponseflag=false,listValueNotEmpty=false;
	      HashSet<String> failureResponse = null;
	      System.out.println("RELATED_PRODUCT_ISBN Stating Test Number : " + testCount);
	      try {
	    	  if(!list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()].isEmpty()){
				  listValueNotEmpty=true;
		    	  JSONObject jsonObject = APIExecutor.executeProductAPI(list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()]);
		    	   RentalValidator Validator = new RentalValidator(jsonObject);
		    	   Validator.verifyRecordForQueriedRelatedProductISBN(list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()],list[CURelationshipIndex.PARENT_ISBN.getIndex()],list[CURelationshipIndex.PARENT_CU_INCLUSION.getIndex()],list[CURelationshipIndex. RELATED_PRODUCT_CU_INCLUSION.getIndex()]);
		    	   failureResponse = Validator.failureResult();
		           System.out.println("failure response is ====>" + failureResponse);
		           Assert.assertTrue(failureResponse.isEmpty(),"For " + list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()] + " failure response is " + failureResponse);
		      }
	      }catch(Exception e){
	    	 noresponseflag=true;
	      }finally{
	    	 /*if(failureResponse.toString().equalsIgnoreCase("SKIPPED")){
	    		  noresponseflag=true;
	    	 }*/
	    	  if(noresponseflag && listValueNotEmpty){
		    	  System.out.println("if value of noresponse...2....");
	    		UpdateExcelSheet.updateNoResponseInSheet(sheetName, endPoint, list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()],reportSheetName);
	    	  }else if(!noresponseflag && listValueNotEmpty) {
		    	  System.out.println("else value of noresponse...2....");
	    		 if(failureResponse.isEmpty()){
	    			UpdateExcelSheet.updatePassInSheet(sheetName, endPoint, list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()],reportSheetName);
	    		}else{
	    			UpdateExcelSheet.updateFailInSheet(sheetName, endPoint, list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()], failureResponse,reportSheetName);
	    		}
	        }
	      }
	        
	  }
 
}
