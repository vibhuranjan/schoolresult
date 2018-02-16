package com.vibhu.schoolresult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vibhu.bean.StudentBean;

/**
 * @author vibhu.ranjan
 *
 */
public class DriverClass {

	static final Logger logger = Logger.getLogger(DriverClass.class);
	
	public static void main(String args[]) throws IOException{
		
		// fetch all roll numbers from input file
		logger.info("Method main :: ");
		List<String> rollNoList = ExcelReaderWriter.readNumbersFromFile();
		// Pass roll number list to process results
		processResult(rollNoList);
		
	}
	
	/**
	 * @param rollNoList
	 */
	public static void processResult(List<String> rollNoList){
		
		List<StudentBean> studentBeanList = new ArrayList<StudentBean>();
		String linkPartialName = "High School";
		WebDriver driver = new FirefoxDriver();
		driver.get("http://upresults.nic.in/");
		WebElement linkName = driver.findElement(By.partialLinkText(linkPartialName));
		linkName.click();
		
		for(String rollNo : rollNoList){
			ResultProcess resultProcess = new ResultProcess();
			StudentBean studentBean = resultProcess.processSingleResult(driver, rollNo);
			studentBeanList.add(studentBean);
		}
		
		ExcelReaderWriter excelReaderWriter = new ExcelReaderWriter();
		excelReaderWriter.writeToFile(studentBeanList);
		driver.close();
	}
}
