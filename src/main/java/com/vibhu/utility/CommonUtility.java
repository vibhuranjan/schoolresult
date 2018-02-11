package com.vibhu.utility;

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
}
