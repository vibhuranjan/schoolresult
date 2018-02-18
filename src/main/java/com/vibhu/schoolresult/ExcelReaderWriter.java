package com.vibhu.schoolresult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vibhu.bean.StudentBean;
import com.vibhu.constant.GlobalConstants;
import com.vibhu.utility.CommonUtility;

/**
 * @author vibhu.ranjan
 *
 */
public class ExcelReaderWriter {
	
	static final Logger logger = Logger.getLogger(ExcelReaderWriter.class);
	
	public static void writeToFile(List<StudentBean> studentBeanList, String outputFileName, String resultsInFile){
		
		XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Result");
	    logger.info("Creating excel sheet...");
	    createHeader(workbook, sheet);
	     
	    for(int i=0;i<studentBeanList.size();i++){
	    	
	    	Row row = sheet.createRow(i+2);
	    	writeSingleResultData(studentBeanList.get(i), row);
	    	for(int count = 0; count < 6; count++) {
	    		sheet.autoSizeColumn(count);
	    	}
	    }
		    try {
		    	FileOutputStream outputStream = new FileOutputStream(outputFileName);
		        workbook.write(outputStream);
		        workbook.close();
		     } catch (FileNotFoundException e) {
		    	 logger.error("FileNotFoundException "+e+" with message "+e.getMessage());
		     } catch (IOException e) {
		    	 logger.error("IOException "+e+" with message "+e.getMessage());
		     }
	     
	     System.out.println("Done");
	}
	
	/**
	 * @param outputFileName
	 * @param index
	 * @return
	 */
	private static String createNewFileName(String outputFileName, int index){
		String splitChar = "";
		if(outputFileName.contains("\\\\")){
			splitChar = "\\\\";
		} else if(outputFileName.contains("\\")){
			splitChar = "\\";
		} 
		String withOutExtension = StringUtils.split(outputFileName, ".")[0];
		List<String> listOfSplitData = new ArrayList<String>(Arrays.asList(withOutExtension.split(splitChar)));
		String fileName = listOfSplitData.get(listOfSplitData.size() - 1);
		String modifiedFileName = fileName + GlobalConstants.UNDERSCORE +  Integer.toString(index);
		return outputFileName.replace(fileName, modifiedFileName);
		
	}
	
	/**
	 * @param workbook
	 * @param sheet
	 */
	private static void createHeader(XSSFWorkbook workbook, XSSFSheet sheet){
		// Create a Font for styling header cells
	     Font headerFont = workbook.createFont();
	     headerFont.setBold(true);
	     headerFont.setFontHeight((short) 16);
	     headerFont.setColor(IndexedColors.BLUE.getIndex());
	     
	     // Create a CellStyle with the font
	     CellStyle headerCellStyle = workbook.createCellStyle();
	     headerCellStyle.setFont(headerFont);
	     
	     String[] headers = new String[] { "Name", "RollNo", "Father's Name", "Mother's Name", "Status", "Total" };
	     Row headerRow = sheet.createRow(0);
	     
	     for (int cn=0; cn<headers.length; cn++) {
	    	 headerRow.createCell(cn).setCellStyle(headerCellStyle);
	    	 headerRow.createCell(cn).setCellValue(headers[cn]);
	     }
	}
	
	/**
	 * @param studentBean
	 * @param row
	 */
	private static void writeSingleResultData(StudentBean studentBean,Row row){
	    row.createCell(0).setCellValue(studentBean.getName());
	    row.createCell(1).setCellValue(studentBean.getRollNo());
	    row.createCell(2).setCellValue(studentBean.getFatherName());
	    row.createCell(3).setCellValue(studentBean.getMotherName());
	    row.createCell(4).setCellValue(studentBean.getStatus());
	    row.createCell(5).setCellValue(studentBean.getTotal());
	}
	

	/**
	 * @return
	 * @throws IOException
	 */
	public static List<String> readNumbersFromFile(String inputFilePath) throws IOException{
		List<String> rollNoList = new ArrayList<String>();
		FileInputStream excelFile = null;
		Workbook workbook = null;
		
		try{
			File inputFile = new File(inputFilePath);
			if(inputFile.isFile() && inputFile.canRead()){
				excelFile = new FileInputStream(inputFile);
			    workbook = new XSSFWorkbook(excelFile);
			    Sheet datatypeSheet = workbook.getSheetAt(0);
			    Iterator<Row> iterator = datatypeSheet.iterator();	
			        while (iterator.hasNext()) {
			            Row currentRow = iterator.next();
			            Iterator<Cell> cellIterator = currentRow.iterator();
			            while (cellIterator.hasNext()) {
			                Cell currentCell = cellIterator.next();
			                if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
			                    rollNoList.add(CommonUtility.checkAndChange(String.valueOf(currentCell.getNumericCellValue())));
			                }
			            }
			        }
			        logger.info("Total roll numbers are :: "+rollNoList.size());
				}
			else{
				logger.info("Application can't read the file...");
			}
		}
		catch(Exception ex){
			logger.error("Exception "+ex+" with message "+ex.getMessage());
		}
		finally{
			if(excelFile != null){ 
				excelFile.close();
			}
			if(workbook != null){ 
				workbook.close();
			}
		}
		return rollNoList;
	}
}