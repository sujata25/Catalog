package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    HashMap<String, List<String>> dataList1;

    public ExcelUtils(String path, String sheetName) {
        this.path = path;
        this.sheetName = sheetName;
    }
    
    public List<List<String>> getTableArray(int startRow, int endRow) throws Exception {
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
  	  	           if(startRow<=r.getRowNum() && endRow>=r.getRowNum())
  	  	           { 
	  		   		//if(index>=1 && maxRows>0){
	  		       		List<String> data=new ArrayList<String>();
  		       			int lastCell=r.getLastCellNum();
	  		       		int startCell=r.getFirstCellNum();
	  		       		int length=lastCell-startCell;
	  		       		for(int cellItr=0;cellItr<length;cellItr++) {
	                      Cell c=r.getCell(cellItr, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
		  		       			if(c!=null){
		  		       			 data.add(c.getStringCellValue());	
		  		       			}else{
		  		       				data.add(" ");
		  		       			}
		  		       		 }
		  		       		dataList.add(data);
		  		       	//endRow--;
		  		   	} else if(endRow<r.getRowNum()){
						 break; 
					}		   		
	       	  // index++;
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
 
    
		 public HashMap<String,List<String>> getCUMap(String relatedValue) throws Exception {
		         List<String>  datafetch = null;
		         HashMap<String,List<String>> dataList2 = new HashMap<String,List<String>>(); 
				 try {
		            FileInputStream ExcelFile = new FileInputStream(path);
		            Workbook  workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(ExcelFile); 
		            ExcelWSheet = workbook.getSheet(sheetName);
		            int index=0,rowItr=0;
		            datafetch=new ArrayList<String>();
		            for(Row r:ExcelWSheet){ 
		  		       			Cell c=r.getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
		  		       			if(c!=null){
		  		       				   if(r.getCell(5).getStringCellValue().equalsIgnoreCase(relatedValue)){
		  		       					  	Cell c1=r.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			  		       					if(c1!=null){
			  		       					datafetch.add(r.getCell(0).getStringCellValue());	
			  		       					}
				  		       			}
		  		       			}
		  		       			      
							} 
		  		   dataList2.put(relatedValue,datafetch);
		  		   System.out.println("dataList2 ==========>" + dataList2);
				}
		        catch (FileNotFoundException e){
		            System.out.println("Could not read the Excel sheet");
		            e.printStackTrace();
		        }
		        catch (IOException e){
		            System.out.println("Could not read the Excel sheet");
		            e.printStackTrace();
		        }
				
				return dataList2;
		   }
    
    
    

    
}