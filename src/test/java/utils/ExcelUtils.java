package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
        Map<Object,Object> mapObject =null;
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            Workbook  workbook = StreamingReader.builder()
                    .rowCacheSize(100)    
                    .bufferSize(4096)     
                    .open(ExcelFile); 
            ExcelWSheet = workbook.getSheet(sheetName);
            String CellData = "";
            int index=0,rowItr=0;
            dataList=new ArrayList<>(); 
  		   	 for(Row r:ExcelWSheet){ 
	  		   		if(index>=1 && maxRows>0){
	  		       		List<String> data1=new ArrayList<String>();
	  		       		for(Cell c:r){
	  		       				data1.add(c.getStringCellValue());
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
        
    }
 
}