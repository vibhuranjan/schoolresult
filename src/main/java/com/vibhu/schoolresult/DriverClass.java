package com.vibhu.schoolresult;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.vibhu.constant.GlobalConstants;

/**
 * @author vibhu.ranjan
 *
 */
public class DriverClass {

	static final Logger logger = Logger.getLogger(DriverClass.class);
	
	public static void main(String args[]) throws IOException{
		
		// fetch all roll numbers from input files
		logger.info("Method main :: ");
		List<String> rollNoList = ExcelReaderWriter.readNumbersFromFile(GlobalConstants.INPUT_FILE_NAME);
		// Pass roll number list to process results
		ResultProcess.processResult(rollNoList, GlobalConstants.LINK_PARTIAL_TEXT);
		
	}
}
