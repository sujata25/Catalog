package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.monitorjbl.xlsx.StreamingReader;

public class ExcelUtils {

    private static  Sheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static org.apache.poi.ss.usermodel.Cell Cell;
    private String path;
    private String sheetName;
    List<String> header;
    List<List<String>>dataList;

    public ExcelUtils(String path, String sheetName) {
        this.path = path;
        this.sheetName = sheetName;
    }
    
    public List<List<String>> getTableArray(int startRow, int maxRows) throws Exception {
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            Workbook  workbook = StreamingReader.builder()
                    .rowCacheSize(100)    
                    .bufferSize(4096)     
                    .open(ExcelFile); 
            ExcelWSheet = workbook.getSheet(sheetName);
            int index=0,rowItr=0;
            dataList=new ArrayList<>(); 
  		   	 for(Row r:ExcelWSheet){ 
  		   		// if(index>=1 && startRow>=r.getRowNum() && maxRows>0){  
  		   			 
	  		   		if(index>=1 && maxRows>0){
	  		       		List<String> data=new ArrayList<String>();
  		       			//System.out.println("data is:" + index);
  		       			int lastCell=r.getLastCellNum();
	  		       		int startCell=r.getFirstCellNum();
	  		       		int length=lastCell-startCell;
	  		       		//System.out.println("length:"+length);
	  		       		for(int cellItr=0;cellItr<length;cellItr++) {
	                      Cell c=r.getCell(cellItr, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
		  		       			//System.out.println("index:"+ c.getColumnIndex()+"cell value is===  " + c.getStringCellValue().trim());
		  		       			if(c!=null){
		  		       			 data.add(c.getStringCellValue());	
		  		       			}else{
		  		       				data.add(" ");
		  		       			}
		  		       		 }
		  		       		dataList.add(data);
		  		       	maxRows--;
		  		   	}  		   		
	       	   index++;
	       	}       
        } 
        catch (FileNotFoundException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
		return dataList;
        
    }
 
    
    
   /* public List<List<String>> getTableArray(int startRow, int maxRows) throws Exception {
        Map<Object,Object> mapObject =null;
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            Workbook  workbook = StreamingReader.builder()
                    .rowCacheSize(100)    
                    .bufferSize(4096)     
                    .open(ExcelFile); 
            ExcelWSheet = workbook.getSheet(sheetName);
            //String CellData = "";
            int index=0,rowItr=0;
            dataList=new ArrayList<>(); 
  		   	 for(Row r:ExcelWSheet){ 
	  		   		if(index>=1 && maxRows>0){
	  		       		List<String> data1=new ArrayList<String>();
  		       			System.out.println("data 1" + index);
  		       			try{
  	  		       			System.out.println("r is==" + r.getCell(2).getStringCellValue());
  		       			}catch(Exception e){
  		       				e.printStackTrace();
  		       				System.out.println("exception is ========");
  		       			}
	  		       		for(Cell c:r){
	  		       			System.out.println("cell value is===  " + c.getStringCellValue().trim());
	  		       			if(c!=null){
	  		       			 data1.add(c.getStringCellValue());	
	  		       			}else{
	  		       				data1.add(" ");
	  		       			}
	  		       			
	  		       			if(c.getStringCellValue().trim() == null || c.getStringCellValue().trim() ==""){
	  		       				data1.add("null");
	  		       			}else{
	  		       				data1.add(c.getStringCellValue());

	  		       			}
	  		       		 }
	  		       		dataList.add(data1);
	  		       	maxRows--;
	  		   		}  		   		
	       	   index++;
	       	}       
            
	    } 
        catch (FileNotFoundException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
		return dataList;
        
    }*/
 
}