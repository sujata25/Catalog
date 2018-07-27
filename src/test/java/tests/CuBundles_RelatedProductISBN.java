package tests;

import java.util.HashMap;
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
import validators.CuBundlesValidator;
import validators.RentalValidator;

public class CuBundles_RelatedProductISBN {
	static int testCount = 0;
	String inputFilePath;
	String sheetName;
	String endPoint;
	int startRow, startCol, totalCols, maxRows;
	String reportFilePath;
	String reportSheetName;
	ExcelUtils excelUtils;
	//Logger logger = LoggerImplemetation.logConfig("CoursewareEBK_RelatedProductISBN.class");
	@BeforeClass
	public void init_vars(){
		sheetName = "CUBundles";
		startCol = 0;
		totalCols = 8;
		endPoint = "Related Product";
		startRow=1700;
		maxRows=2000;
		inputFilePath="D:\\Project\\CU Catalog\\Files\\19_july_2018\\cucatalog.xlsx";
		reportFilePath =  "D:\\test.xlsx";
		reportSheetName = "Bundles_RelatedProduct";
		UpdateExcelSheet.createFile(reportFilePath, reportSheetName);
	}
	
	@DataProvider
	public Object[][] Authentication() throws Exception{
		excelUtils = new ExcelUtils(inputFilePath, sheetName);
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
	      HashMap<String,List<String>> isbnvalues;
	      System.out.println("\n");
	      System.out.println("product Stating Test Number : " + testCount);
	      try {
	    	  if(!list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()].isEmpty()){
			   listValueNotEmpty=true; 
	    	   JSONObject jsonObject = APIExecutor.executeProductAPI(list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()]);
	    	   isbnvalues = excelUtils.getCUMap(list[5]);
	    	   CuBundlesValidator Validator = new CuBundlesValidator(jsonObject);
	    	   Validator.verifyRecordForQueriedRelatedProductISBN(list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()],list[CURelationshipIndex.PARENT_ISBN.getIndex()],list[CURelationshipIndex.PARENT_CU_INCLUSION.getIndex()],list[CURelationshipIndex. RELATED_PRODUCT_CU_INCLUSION.getIndex()], isbnvalues);
	    	   failureResponse = Validator.failureResult();
	           System.out.println("failue response is ====>" + failureResponse);
	           Assert.assertTrue(failureResponse.isEmpty(),"For " + list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()] + " failure response is " + failureResponse);
	    	  }
	      }catch(Exception e){
	    	 noresponseflag=true;
	        // Assert.assertTrue(failureResponse.isEmpty(),"For " + list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()] + " failure response is " + failureResponse);
	      }finally{
	    	 if(noresponseflag && listValueNotEmpty){
				UpdateExcelSheet.updateNoResponseInSheet(sheetName, endPoint, list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()],reportSheetName);
	    	 }else if(!noresponseflag && listValueNotEmpty) {
	        	if(failureResponse.isEmpty()){
	    			UpdateExcelSheet.updatePassInSheet(sheetName, endPoint, list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()],reportSheetName);
	    		}else{
	    			UpdateExcelSheet.updateFailInSheet(sheetName, endPoint, list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()], failureResponse,reportSheetName);
	    		}
	        }
	      }
	        
	  }
 
}
