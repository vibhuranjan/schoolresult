package com.vibhu.schoolresult;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vibhu.bean.StudentBean;

/**
 * @author vibhu.ranjan
 *
 */
public class BeanSetter {
	
	/**
	 * @param driver
	 * @param studentBean
	 * @return
	 */
	public static StudentBean fetchPersonalData(WebDriver driver,StudentBean studentBean){
		WebElement rollNoElement = driver.findElement(By.xpath("//table[2]//tbody//tr[1]//td[2]"));
		studentBean.setRollNo(rollNoElement.getText());
		WebElement fatherNameElement = driver.findElement(By.xpath("//table[2]//tbody//tr[1]//td[4]"));
		studentBean.setFatherName(fatherNameElement.getText());
		
		WebElement nameElement = driver.findElement(By.xpath("//table[2]//tbody//tr[2]//td[2]"));
		studentBean.setName(nameElement.getText());
		WebElement motherNameElement = driver.findElement(By.xpath("//table[2]//tbody//tr[2]//td[4]"));
		studentBean.setMotherName(motherNameElement.getText());
		return studentBean;
	}
	
	public static StudentBean fetchFirstSubjectData(WebDriver driver,StudentBean studentBean){
		WebElement firstSubject = driver.findElement(By.xpath("//table[3]//tbody//tr[4]//td[1]"));
		WebElement firstSubjectTheory = driver.findElement(By.xpath("//table[3]//tbody//tr[4]//td[2]"));
		WebElement firstSubjectPractical = driver.findElement(By.xpath("//table[3]//tbody//tr[4]//td[3]"));
		WebElement firstSubjectTotal = driver.findElement(By.xpath("//table[3]//tbody//tr[4]//td[4]"));
		WebElement firstSubjectGrade = driver.findElement(By.xpath("//table[3]//tbody//tr[4]//td[5]"));
		studentBean.setFirstSubject(firstSubject.getText());
		studentBean.setFirstSubjectTheory(firstSubjectTheory.getText());
		studentBean.setFirstSubjectPractical(firstSubjectPractical.getText());
		studentBean.setFirstSubjectTotal(firstSubjectTotal.getText());
		studentBean.setFirstSubjectGrade(firstSubjectGrade.getText());
		
		return studentBean;
	}
	
	public static StudentBean fetchSecondSubjectData(WebDriver driver,StudentBean studentBean){
		WebElement secondSubject = driver.findElement(By.xpath("//table[3]//tbody//tr[5]//td[1]"));
		WebElement secondSubjectTheory = driver.findElement(By.xpath("//table[3]//tbody//tr[5]//td[2]"));
		WebElement secondSubjectPractical = driver.findElement(By.xpath("//table[3]//tbody//tr[5]//td[3]"));
		WebElement secondSubjectTotal = driver.findElement(By.xpath("//table[3]//tbody//tr[5]//td[4]"));
		WebElement secondSubjectGrade = driver.findElement(By.xpath("//table[3]//tbody//tr[5]//td[5]"));
		studentBean.setSecondSubject(secondSubject.getText());
		studentBean.setSecondSubjectTheory(secondSubjectTheory.getText());
		studentBean.setSecondSubjectPractical(secondSubjectPractical.getText());
		studentBean.setSecondSubjectTotal(secondSubjectTotal.getText());
		studentBean.setSecondSubjectGrade(secondSubjectGrade.getText());
		
		return studentBean;
	}
	
	public static StudentBean fetchThirdSubjectData(WebDriver driver,StudentBean studentBean){
		WebElement thirdSubject = driver.findElement(By.xpath("//table[3]//tbody//tr[6]//td[1]"));
		WebElement thirdSubjectTheory = driver.findElement(By.xpath("//table[3]//tbody//tr[6]//td[2]"));
		WebElement thirdSubjectPractical = driver.findElement(By.xpath("//table[3]//tbody//tr[6]//td[3]"));
		WebElement thirdSubjectTotal = driver.findElement(By.xpath("//table[3]//tbody//tr[6]//td[4]"));
		WebElement thirdSubjectGrade = driver.findElement(By.xpath("//table[3]//tbody//tr[6]//td[5]"));
		studentBean.setThirdSubject(thirdSubject.getText());
		studentBean.setThirdSubjectTheory(thirdSubjectTheory.getText());
		studentBean.setThirdSubjectPractical(thirdSubjectPractical.getText());
		studentBean.setThirdSubjectTotal(thirdSubjectTotal.getText());
		studentBean.setThirdSubjectGrade(thirdSubjectGrade.getText());
		
		return studentBean;
	}

}
