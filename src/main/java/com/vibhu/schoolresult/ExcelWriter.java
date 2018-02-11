package com.vibhu.schoolresult;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vibhu.bean.StudentBean;

/**
 * @author vibhu.ranjan
 *
 */
public class ExcelWriter {

	private static final String FILE_NAME = "C:\\fuNke\\code\\schoolresult\\ResultData.xlsx";
	
	public void writeToFile(List<StudentBean> studentBeanList){
		
		 XSSFWorkbook workbook = new XSSFWorkbook();
	     XSSFSheet sheet = workbook.createSheet("Result");
	     System.out.println("Creating excel");
	     
	     createHeader(workbook, sheet);
	     
	     for(int i=0;i<studentBeanList.size();i++){
	    	 Row row = sheet.createRow(i+2);
	    	 writeSingleResultData(studentBeanList.get(i), row);
	     }
	     for(int i = 0; i < 6; i++) {
	            sheet.autoSizeColumn(i);
	     }
	     
	     try {
	    	 FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
	         workbook.write(outputStream);
	         workbook.close();
	     } catch (FileNotFoundException e) {
	    	 e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     
	     System.out.println("Done");
	}
	
	/**
	 * @param workbook
	 * @param sheet
	 */
	private void createHeader(XSSFWorkbook workbook, XSSFSheet sheet){
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
	private void writeSingleResultData(StudentBean studentBean,Row row){
	    row.createCell(0).setCellValue(studentBean.getName());
	    row.createCell(1).setCellValue(studentBean.getRollNo());
	    row.createCell(2).setCellValue(studentBean.getFatherName());
	    row.createCell(3).setCellValue(studentBean.getMotherName());
	    row.createCell(4).setCellValue(studentBean.getStatus());
	    row.createCell(5).setCellValue(studentBean.getTotal());
	}
}
