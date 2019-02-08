package com.vibhu.schoolresult;

import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vibhu.bean.StudentBean;
import com.vibhu.bean.SubjectBean;

/**
 * @author vibhu.ranjan
 *
 */
public class BeanSetter {
	
	private static final String TABLE_TWO = "//table[2]//tbody//tr";
	private static final String TABLE_THREE = "//table[3]//tbody//tr";
	
	private BeanSetter(){
		
	}
	/**
	 * @param driver
	 * @param studentBean
	 * @return
	 */
	public static StudentBean fetchPersonalData(WebDriver driver,StudentBean studentBean){
		
		WebElement rollNoElement = driver.findElement(By.xpath(TABLE_TWO+"[1]//td[2]"));
		studentBean.setRollNo(rollNoElement.getText());
		WebElement fatherNameElement = driver.findElement(By.xpath(TABLE_TWO+"[1]//td[4]"));
		studentBean.setFatherName(fatherNameElement.getText());
		
		WebElement nameElement = driver.findElement(By.xpath(TABLE_TWO+"[2]//td[2]"));
		studentBean.setName(nameElement.getText());
		WebElement motherNameElement = driver.findElement(By.xpath(TABLE_TWO+"[2]//td[4]"));
		studentBean.setMotherName(motherNameElement.getText());
		return studentBean;
	}
	
	/**
	 * @param driver
	 * @param subjectBeanList
	 * @param row
	 * @param total
	 */
	public static int fetchSubjectData(WebDriver driver,List<SubjectBean> subjectBeanList, int row,int total){
		SubjectBean subjectBean = new SubjectBean();
		int returnTotal = 0;
		WebElement subject = driver.findElement(By.xpath(TABLE_THREE+"["+row+"]//td[1]"));
		WebElement subjectTheory = driver.findElement(By.xpath(TABLE_THREE+"["+row+"]//td[2]"));
		WebElement subjectPractical = driver.findElement(By.xpath(TABLE_THREE+"["+row+"]//td[3]"));
		WebElement subjectTotal = driver.findElement(By.xpath(TABLE_THREE+"["+row+"]//td[4]"));
		WebElement subjectGrade = driver.findElement(By.xpath(TABLE_THREE+"["+row+"]//td[5]"));
		
		subjectBean.setSubject(subject.getText());
		subjectBean.setSubjectTheory(subjectTheory.getText());
		subjectBean.setSubjectPractical(subjectPractical.getText());
		subjectBean.setSubjectTotal(subjectTotal.getText());
		subjectBean.setSubjectGrade(subjectGrade.getText());
		subjectBeanList.add(subjectBean);
		if(subjectTotal.getText().contains("F")){
			if(!subjectTotal.getText().contains("AA")){
				String actualRollNo = StringUtils.split(subjectTotal.getText(), "F")[0];
				returnTotal = Integer.parseInt(actualRollNo) + total;
			}
		}else{
			returnTotal = Integer.parseInt(subjectTotal.getText()) + total;
		}
		return returnTotal;
	}
	
	/**
	 * @param driver
	 * @param subjectBeanList
	 * @param row
	 * @param total
	 */
	public static void fetchSubjectDataForInter(WebDriver driver,List<SubjectBean> subjectBeanList, int row, StudentBean studentBean){
		SubjectBean subjectBean = new SubjectBean();
		
		WebElement subject = driver.findElement(By.xpath(TABLE_THREE+"["+row+"]//td[1]"));
		WebElement subjectTotal = driver.findElement(By.xpath(TABLE_THREE+"["+row+"]//td[8]"));
		WebElement totalOrResultStatus = driver.findElement(By.xpath(TABLE_THREE+"["+row+"]//td[9]"));
		
		subjectBean.setSubject(subject.getText());
		subjectBean.setSubjectTotal(subjectTotal.getText());
		
		subjectBeanList.add(subjectBean);
		if(row == 3 && totalOrResultStatus.getText().contains("500")){
			if(totalOrResultStatus.getText().contains("/")) {
				studentBean.setTotal(Integer.parseInt(totalOrResultStatus.getText().split("/")[0]));
			} else {
				studentBean.setTotal(0);
			}
		}
		if(row == 6){
			studentBean.setStatus(totalOrResultStatus.getText());;
		}
	}
	
	/**
	 * @param driver
	 * @param studentBean
	 */
	public static void fetchStudentStatus(WebDriver driver,StudentBean studentBean){
		WebElement status = driver.findElement(By.xpath(TABLE_THREE+"[5]//td[6]"));
		studentBean.setStatus(status.getText());
	}
}
