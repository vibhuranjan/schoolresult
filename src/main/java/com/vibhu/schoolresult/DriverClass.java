package com.vibhu.schoolresult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	public static void main(String args[]) throws IOException{
		
		// fetch all roll numbers from list
		List<String> rollNoList = ExcelWriter.readNumberFromFile();
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
		ExcelWriter excelWriter = new ExcelWriter();
		excelWriter.writeToFile(studentBeanList);
		driver.close();
	}
}
