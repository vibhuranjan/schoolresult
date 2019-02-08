package com.vibhu.schoolresult;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vibhu.bean.StudentBean;
import com.vibhu.bean.SubjectBean;
import com.vibhu.constant.GlobalConstants;
import com.vibhu.utility.CommonUtility;

/**
 * @author vibhu.ranjan
 *
 */
public class ResultProcess {

	static final Logger logger = Logger.getLogger(ResultProcess.class);
	
	/**
	 * @param rollNoList
	 * @return 
	 */
	public static List<StudentBean> processResult(List<String> rollNoList, String inputClass, String schoolCode) {
		logger.debug("Method :: processResult [Entry] ");
		List<StudentBean> studentBeanList = new ArrayList<StudentBean>();
		List<StudentBean> finalStudentBeanList = new ArrayList<StudentBean>();
		System.setProperty("webdriver.chrome.driver", CommonUtility.getCurrentDirectory() + GlobalConstants.BLACK_SLASH
				+ GlobalConstants.TOOLS_FOLDER + GlobalConstants.BLACK_SLASH + GlobalConstants.CHROME_DRIVER_EXE);
		WebDriver driver = new ChromeDriver();
		driver.get(GlobalConstants.WEBSITE_URL);
		WebElement linkName = driver.findElement(By.partialLinkText(inputClass));
		linkName.click();

		try {
			for (String rollNo : rollNoList) {
				ResultProcess resultProcess = new ResultProcess();
				StudentBean studentBean = resultProcess.processSingleResult(driver, rollNo, schoolCode, inputClass);
				studentBeanList.add(studentBean);
			}
		} catch (Exception ex) {
			logger.error("Exception :: " + ex + " message ::" + ex.getMessage());
		} finally {
			driver.close();
			finalStudentBeanList = studentBeanList;
		}
		logger.debug("Method :: processResult [Exit] ");
		return finalStudentBeanList;
	}
	
	public StudentBean processSingleResult(WebDriver driver, String rollNo, String schoolCode, String inputClass){
		
		StudentBean studentBean = new StudentBean();
		List<SubjectBean> subjectBeanList = new ArrayList<SubjectBean>();
		int total = 0;
		
		WebElement regNo = driver.findElement(By.name("regno"));
		regNo.sendKeys(rollNo);
		if(inputClass.contains("Inter") || inputClass.contains("inter")){
			WebElement schCode = driver.findElement(By.name("schcode"));
			schCode.sendKeys(schoolCode);
		}
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
