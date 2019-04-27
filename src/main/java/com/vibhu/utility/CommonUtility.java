package com.vibhu.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.apache.commons.exec.util.StringUtils;
import org.apache.log4j.Logger;

import com.vibhu.bean.StudentBean;
import com.vibhu.constant.GlobalConstants;

public class CommonUtility {
	
	static final Logger logger = Logger.getLogger(CommonUtility.class);
	
	private CommonUtility(){
		
	}
	
	/**
	 * @param rollNumber
	 * @return
	 */
	public static String checkAndChange(String rollNumber){
		String modifiedRollNo = "";
		if(rollNumber.contains(".")){
			rollNumber = StringUtils.split(rollNumber, ".")[0];
		}
		modifiedRollNo = adjustLenght(rollNumber);
		return modifiedRollNo;
	}
	
	/**
	 * @param rollNumber
	 * @return
	 */
	private static String adjustLenght(String rollNumber){
		String modifiedRollNo = "";
		if(rollNumber.length() == 7){
			modifiedRollNo = rollNumber;
		}else{
			int length = 7 - rollNumber.length();
				if(length == 1){
					modifiedRollNo = "0" + rollNumber;
				}else if(length == 2){
					modifiedRollNo = "00" + rollNumber;
				}	
		}
		return modifiedRollNo;
	}
	
	/**
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> generateNumbersFromRange(String start, String end){
		List<String> listOfRollNumbers = new ArrayList<String>();
		long startRollNo = Long.parseLong(start);
		long endRollNo = Long.parseLong(end);
		while(startRollNo <= endRollNo){
			listOfRollNumbers.add(adjustLenght(String.valueOf(startRollNo)));
			startRollNo = startRollNo + 1;
		}
		return listOfRollNumbers;
		
	}
	
	public static void saveResult(List<StudentBean> studentBeanList, String filePath, String startRollNo, String endRollNo, String nameOfSelectedClass) throws IOException{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos = new FileOutputStream(StringUtils.split(filePath, ".")[0] + GlobalConstants.UNDERSCORE
					+ nameOfSelectedClass + GlobalConstants.UNDERSCORE + startRollNo + GlobalConstants.UNDERSCORE
					+ endRollNo + ".ser");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(studentBeanList);
		}catch(IOException ioe){
			logger.error(ioe);
		}finally{
			if(oos != null){
				oos.close();
			}
			if(fos != null){
				fos.close();
			}
		}
	}
	
	public static List<StudentBean> fetchSavedResult(String filePath) throws IOException, ClassNotFoundException{
		List<StudentBean> studentBeanList = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
		fis = new FileInputStream(filePath);
        ois = new ObjectInputStream(fis);
        studentBeanList = (List<StudentBean>) ois.readObject();
		}catch(IOException ioe){
			logger.error("Exception :: " + ioe + " message ::" + ioe.getMessage());
		}finally{
			if(ois != null){
				ois.close();
			}
			if(fis != null){
				fis.close();
			}
		}
        return studentBeanList;
	}
	
	/**
	 * @param fileField
	 */
	public static void browseFiles(JTextField fileField){
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        File browserDir = new File(CommonUtility.getCurrentDirectory());
        if(browserDir.isDirectory() || browserDir.isFile()){
        	fileChooser.setCurrentDirectory(browserDir);
        }
        fileChooser.setAcceptAllFileFilterUsed(false);
        int rVal = fileChooser.showOpenDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {
        	fileField.setText(fileChooser.getSelectedFile().toString());
        }
	}
	
	public static String[] getSavedFileList(String folderPath){
		List<String> listOfFiles = new ArrayList<String>();
		File folder = new File(folderPath);
		File[] filesUnderFolder = folder.listFiles();
		for (int i = 0; i < filesUnderFolder.length; i++) {
		      if (filesUnderFolder[i].isFile() && filesUnderFolder[i].getName().contains("ser")) {
		    	  listOfFiles.add(filesUnderFolder[i].getName());
		      }
		}
		return listOfFiles.stream().toArray(String[]::new);
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCurrentDirectory() {
		return System.getProperty("user.dir");
	}
}
