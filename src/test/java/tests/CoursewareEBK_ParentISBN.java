package tests;

import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Logger;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.APIExecutor;
import utils.CURelationshipIndex;
import utils.ExcelUtils;
import utils.LoggerImplemetation;
import utils.UpdateExcelSheet;
import validators.EbookValidator;

public class CoursewareEBK_ParentISBN {
	Logger logger = LoggerImplemetation.logConfig("CoursewareEBK_ParentISBN.class");
	static int testCount = 0;
	String inputFilePath;
	String sheetName;
	String endPoint;
	int startRow, startCol, totalCols, maxRows;
	String reportFilePath;
	String reportSheetName;
	
	@BeforeClass
	public void init_vars(){
		sheetName = "CoursewaretoEBK";
		startCol = 0;
		totalCols = 16;
		//endPoint = "Product";
		//inputFilePath = System.getProperty("inputFilePath");
		//startRow = Integer.parseInt(System.getProperty("startRow"));
		//maxRows = Integer.parseInt(System.getProperty("maxRows"));
		//reportFilePath = System.getProperty("reportFilePath");
		//inputFilePath="D:\\Project\\CU Catalog\\Files\\cu relationships extract with type fields and bundles_070318.xlsx";
		startRow=2;
		maxRows=1;
		inputFilePath="D:\\Project\\CU Catalog\\Files\\cu relationships.xlsx";
		reportFilePath =  "D:\\test.xlsx";
		reportSheetName = "CoursewareEBK_ParentISBN";
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
	  public void Test01EBKParentISNB(Object[] varArg)throws  Exception{
		 String [] list=new String [varArg.length];
		 for(int i=0; i<varArg.length;i++){
			 list[i]=varArg[i].toString();
		 }
		  testCount++;
		  boolean noresponseflag=false;
	      HashSet<String> failureResponse = null;
	      System.out.println("product Stating Test Number : " + testCount);
	      logger.info("Stating Test Number log : ");
	      try {
	    	   JSONObject jsonObject = APIExecutor.executeProductAPI(list[CURelationshipIndex.PARENT_ISBN.getIndex()]);
	    	   EbookValidator Validator = new EbookValidator(jsonObject);
	    	   Validator.verifyIsbn13InRecords(list[CURelationshipIndex.PARENT_ISBN.getIndex()],list[CURelationshipIndex.RELATED_PRODUCT_ISBN.getIndex()],list[CURelationshipIndex.PARENT_CU_INCLUSION.getIndex()],list[CURelationshipIndex. RELATED_PRODUCT_CU_INCLUSION.getIndex()]);
	    	   failureResponse = Validator.failureResult();
	           System.out.println("failure response is ====>" + failureResponse);
	           Assert.assertTrue(failureResponse.isEmpty(),"For " + list[CURelationshipIndex.PARENT_ISBN.getIndex()] + " failure response is " + failureResponse);
	      }catch(Exception e){
	    	 noresponseflag=true;
	         Assert.assertTrue(failureResponse.isEmpty(),"For " + list[CURelationshipIndex.PARENT_ISBN.getIndex()] + " failure response is " + failureResponse);
	      }finally{
	    	if(noresponseflag){
				UpdateExcelSheet.updateNoResponseInSheet(sheetName, endPoint, list[CURelationshipIndex.PARENT_ISBN.getIndex()],reportSheetName);
	        }else{
	        	if(failureResponse.isEmpty()){
	    			UpdateExcelSheet.updatePassInSheet(sheetName, endPoint, list[CURelationshipIndex.PARENT_ISBN.getIndex()],reportSheetName);
	    		}else{
	    			UpdateExcelSheet.updateFailInSheet(sheetName, endPoint, list[CURelationshipIndex.PARENT_ISBN.getIndex()], failureResponse,reportSheetName);
	    		}
	        }
	      }
	        
	  }

	 
}
