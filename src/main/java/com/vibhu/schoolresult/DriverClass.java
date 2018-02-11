/**
 * 
 */
package com.vibhu.schoolresult;

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

	
	public static void main(String args[]){
		
		String[] rollNumbers = new String[] { "0513554", "0513555", "0513556" };
		List<StudentBean> studentBeanList = new ArrayList<StudentBean>();
		String linkPartialName = "High School";
		WebDriver driver = new FirefoxDriver();
		driver.get("http://upresults.nic.in/");
		WebElement linkName = driver.findElement(By.partialLinkText(linkPartialName));
		linkName.click();
	
		for(String rollNo : rollNumbers){
			ResultProcess resultProcess = new ResultProcess();
			StudentBean studentBean = resultProcess.processSingleResult(driver, rollNo);
			System.out.println(studentBean.toString());
			studentBeanList.add(studentBean);
		}
		
		ExcelWriter excelWriter = new ExcelWriter();
		excelWriter.writeToFile(studentBeanList);
		
		driver.close();
		
	}
}
