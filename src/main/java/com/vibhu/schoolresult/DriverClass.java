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
import com.vibhu.bean.SubjectBean;

/**
 * @author vibhu.ranjan
 *
 */
public class DriverClass {

	
	public static void main(String args[]){
		StudentBean studentBean = new StudentBean();
		List<SubjectBean> subjectBeanList = new ArrayList<SubjectBean>();
		int total = 0;
		
		String linkPartialName = "High School";
		WebDriver driver = new FirefoxDriver();
		driver.get("http://upresults.nic.in/");
		WebElement linkName = driver.findElement(By.partialLinkText(linkPartialName));
		linkName.click();
		WebElement regNo = driver.findElement(By.name("regno"));
		regNo.sendKeys("0513554");
		WebElement submitButton = driver.findElement(By.name("B1"));
		submitButton.click();
		
		BeanSetter.fetchPersonalData(driver, studentBean);
		for(int row=4;row<=9;row++){
			BeanSetter.fetchSubjectData(driver, subjectBeanList, row,total);
		}
		
		studentBean.setSubjectBeanList(subjectBeanList);
		BeanSetter.fetchStudentStatus(driver, studentBean);
		
		System.out.println(studentBean.toString());
		driver.close();	
	}
}
