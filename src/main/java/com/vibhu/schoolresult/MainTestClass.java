package com.vibhu.schoolresult;

import java.io.File;

public class MainTestClass {

	public static void main(String[] args) {
		//List<String> listOfNumbers = CommonUtility.generateNumbersFromRange("0513622", "0513624");
		//System.out.println("list ::"+listOfNumbers.toString());
		
		//String str = ExcelReaderWriter.createNewFileName("C:\\fuNke\\code\\schoolresult\\ResultData.xlsx", 2);
			//	System.out.println("str ::"+str);
				//List<String> list = new ArrayList<String>(Arrays.asList("C:\\fuNke\\code\\schoolresult\\ResultData.xlsx".split("\\\\")));
				//System.out.println("list :: "+list.toString());
		
		File folder = new File("C:\\fuNke\\code\\schoolresult");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
	}

	
}
