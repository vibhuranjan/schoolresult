package com.vibhu.utility;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;

public class CommonUtility {
	
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
}
