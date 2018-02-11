package com.vibhu.schoolresult;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vibhu.bean.StudentBean;
import com.vibhu.bean.SubjectBean;

/**
 * @author vibhu.ranjan
 *
 */
public class ResultProcess {

	public StudentBean processSingleResult(WebDriver driver, String rollNo){
		
		StudentBean studentBean = new StudentBean();
		List<SubjectBean> subjectBeanList = new ArrayList<SubjectBean>();
		int total = 0;
		
		WebElement regNo = driver.findElement(By.name("regno"));
		regNo.sendKeys(rollNo);
		WebElement submitButton = driver.findElement(By.name("B1"));
		submitButton.click();
		
		
		if(!driver.findElements(By.xpath("//table[2]//tbody//tr[1]//td[2]")).isEmpty()){
			BeanSetter.fetchPersonalData(driver, studentBean);
			for(int row=4;row<=9;row++){
				total = BeanSetter.fetchSubjectData(driver, subjectBeanList, row,total);
			}
			
			studentBean.setSubjectBeanList(subjectBeanList);
			BeanSetter.fetchStudentStatus(driver, studentBean);
			studentBean.setTotal(total);
		}
		WebElement backLink = driver.findElement(By.partialLinkText("BACK TO PREVIOUS PAGE"));
		backLink.click();
		
		return studentBean;
	}
}
