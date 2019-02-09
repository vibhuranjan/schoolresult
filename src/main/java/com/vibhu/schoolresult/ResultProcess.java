package com.vibhu.schoolresult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vibhu.bean.StudentBean;
import com.vibhu.bean.SubjectBean;
import com.vibhu.constant.GlobalConstants;

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
		logger.info("Method :: processResult [Entry] ");
		List<StudentBean> studentBeanList = new ArrayList<StudentBean>();
		List<StudentBean> finalStudentBeanList = new ArrayList<StudentBean>();
		WebDriver driver = null;
		try {
			File file = new File("tools/"+GlobalConstants.CHROME_DRIVER_EXE);
			String absolutePath = file.getAbsolutePath();
			System.setProperty("webdriver.chrome.driver", absolutePath);
			logger.debug("absolutePath of chrome driver ::  "+ absolutePath);
			driver = new ChromeDriver();
			driver.get(GlobalConstants.WEBSITE_URL);
			if(inputClass.equalsIgnoreCase(GlobalConstants.HIGH_SCHOOL)) {
				logger.info("Input class : 10 ");
				WebElement linkName = driver.findElement(By.partialLinkText(GlobalConstants.HIGH_SCHOOL_LINK_PARTIAL_TEXT));
				linkName.click();
			} else if(inputClass.equalsIgnoreCase(GlobalConstants.INTER)) {
				logger.info("Input class : 12 ");
				WebElement linkName = driver.findElement(By.partialLinkText(GlobalConstants.INTER_LINK_PARTIAL_TEXT));
				linkName.click();
			}			
			for (String rollNo : rollNoList) {
				ResultProcess resultProcess = new ResultProcess();
				StudentBean studentBean = resultProcess.processSingleResult(driver, rollNo, schoolCode, inputClass);
				studentBeanList.add(studentBean);
			}
		} catch (Exception ex) {
			//JOptionPane.showMessageDialog(null, "Failed in processing result." + ex.getMessage());
			logger.error("Exception :: " + ex + " message ::" + ex.getMessage());
		} finally {
			if(driver != null) {
				driver.close();
			}
			finalStudentBeanList = studentBeanList;
		}
		logger.debug("Method :: processResult [Exit] ");
		return finalStudentBeanList;
	}
	
	public StudentBean processSingleResult(WebDriver driver, String rollNo, String schoolCode, String inputClass){
		logger.info("Method :: processSingleResult [Entry] ");
		StudentBean studentBean = new StudentBean();
		List<SubjectBean> subjectBeanList = new ArrayList<SubjectBean>();
		int total = 0;
		
		WebElement regNo = driver.findElement(By.name("regno"));
		regNo.sendKeys(rollNo);
		if(inputClass.contains("Inter") || inputClass.contains("inter")){
			logger.debug("This is result of class : 12 ");
			WebElement schCode = driver.findElement(By.name("schcode"));
			schCode.sendKeys(schoolCode);
		} else {
			logger.debug("This is result of class : 10 ");
		}
		WebElement submitButton = driver.findElement(By.name("B1"));
		submitButton.click();
		
		if(!driver.findElements(By.xpath("//table[2]//tbody//tr[1]//td[2]")).isEmpty()){
			BeanSetter.fetchPersonalData(driver, studentBean);
			if(inputClass.contains(GlobalConstants.INTER) || inputClass.contains("inter")){
				for(int row=3;row<=8;row++){
					BeanSetter.fetchSubjectDataForInter(driver, subjectBeanList, row, studentBean);
				}
			} else if(inputClass.contains(GlobalConstants.HIGH_SCHOOL) || inputClass.contains("high school")){
				for(int row=4;row<=9;row++){
					total = BeanSetter.fetchSubjectData(driver, subjectBeanList, row,total);
				}
				BeanSetter.fetchStudentStatus(driver, studentBean);
				studentBean.setTotal(total);
			}
			studentBean.setSubjectBeanList(subjectBeanList);
		}
		WebElement backLink = driver.findElement(By.partialLinkText("BACK TO PREVIOUS PAGE"));
		backLink.click();
		
		return studentBean;
	}
}
