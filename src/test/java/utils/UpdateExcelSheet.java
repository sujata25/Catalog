package utils;

import java.io.*;
import org.apache.poi.EncryptedDocumentException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UpdateExcelSheet {

    public static String filePath = "";
    public static void createFile(String path, String sheetName){
        filePath = path;
        File f = new File(filePath);
        try {
            Workbook workbook;
            FileInputStream fileinp = new FileInputStream(path);
            workbook = new XSSFWorkbook(fileinp);
            try{
            	workbook.createSheet(sheetName);
            }catch(Exception e){
            	System.out.println("Exception while creating workbook");
            }
            
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("File is written successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updatePassInSheet(String sourceSheet, String endpoint, String ISBN, String reportSheet) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            int rowCount = 0;
            Sheet sheet = workbook.getSheet(reportSheet);

            Object[][] bookData = {
                    {sourceSheet, endpoint, ISBN, "Pass" },
            };

            rowCount = sheet.getLastRowNum();
            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);
                int columnCount = 0;
                Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);
                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException
                | InvalidFormatException ex) {
            ex.printStackTrace();
        }
    }
    public static void updateFailInSheet(String sourceSheet, String endpoint, String ISBN, HashSet<String> errors, String reportSheet) {
    	int rowCount = 0;
        String errorString = "";
        for(String error:errors){
            errorString += error + "; ";
        }
        errorString = errorString.substring(0,errorString.length()-2);

        try {
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(reportSheet);
            Object[][] bookData = {
                    {sourceSheet, endpoint, ISBN, "Fail", errorString },
            };
            rowCount = sheet.getLastRowNum();
            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);
                int columnCount = 0;
                Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);
                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException
                | InvalidFormatException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateNoResponseInSheet(String sourceSheet, String endpoint, String ISBN, String reportSheet) {

        try {
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet(reportSheet);

            Object[][] bookData = {
                    {sourceSheet, endpoint, ISBN, "Skipped" },
            };

            int rowCount = sheet.getLastRowNum();

            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);
                int columnCount = 0;
                Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);
                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException
                | InvalidFormatException ex) {
            ex.printStackTrace();
        }
    
    }
    
}