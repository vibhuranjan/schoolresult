package com.vibhu.design;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.vibhu.bean.StudentBean;
import com.vibhu.constant.GlobalConstants;
import com.vibhu.schoolresult.ExcelReaderWriter;
import com.vibhu.schoolresult.ResultProcess;
import com.vibhu.utility.CommonUtility;

public class ResultDesign {

	private JFrame firstFrame;
	private JFrame newResultFrame;
	private JFrame savedResultFrame;
	
	private JTextField outputFileField;
	private JTextField inputFileField;
	private JComboBox<Object> comboBox;
	private JTextField showTotalInputNumber;
	private JTextField schoolCodeField;
	private JComboBox<Object> resultInFileCheckBox ;
	private JCheckBox saveResultChkBox;
	
	boolean flag = false;
	List<String> rollNoList = new ArrayList<String>();
	
	static final Logger logger = Logger.getLogger(ResultDesign.class);
	private JTextField processedResultField;
	private JTextField timeTakenField;
	private JTextField startValue;
	private JTextField endValue;
	private JCheckBox chckbxNewCheckBox;
	
	private static ResultDesign window;
	private JTextField preiousResultFileField;
	private JLabel doneField;
	private JComboBox<Object> savedFilesCombo ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ResultDesign();
					window.firstFrame.setVisible(true);
					window.newResultFrame.setVisible(false);
					window.savedResultFrame.setVisible(false);
				} catch (Exception e) {
					logger.debug("Exception e :: "+e+" with message ::"+e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ResultDesign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setupFirstFrame();
		setupNewResultFrame();
		setupSavedResultFrame();
	}
	
	/**
	 * 
	 */
	private void setupFirstFrame(){
		firstFrame = new JFrame();
		firstFrame.setBounds(450, 200, 450, 300);
		firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstFrame.getContentPane().setLayout(null);
		
		final JRadioButton newResultButton = new JRadioButton("New result");
		newResultButton.setSelected(true);
		newResultButton.setBounds(38, 34, 109, 23);
		firstFrame.getContentPane().add(newResultButton);
		
		final JRadioButton savedResultButton = new JRadioButton("Get old saved result");
		savedResultButton.setBounds(38, 69, 149, 23);
		firstFrame.getContentPane().add(savedResultButton);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(newResultButton);
		buttonGroup.add(savedResultButton);
		
		JButton submitButton = new JButton(GlobalConstants.SUBMIT);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(newResultButton.isSelected()){
					window.firstFrame.setVisible(false);
					window.newResultFrame.setVisible(true);
					window.savedResultFrame.setVisible(false);
				} else if(savedResultButton.isSelected()){
					window.firstFrame.setVisible(false);
					window.newResultFrame.setVisible(false);
					window.savedResultFrame.setVisible(true);
				}
				
			}
		});
		submitButton.setBounds(38, 123, 89, 23);
		firstFrame.getContentPane().add(submitButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(149, 123, 89, 23);
		firstFrame.getContentPane().add(resetButton);
		logger.debug("First frame launched.");
	}
	
	/**
	 * 
	 */
	private void setupNewResultFrame() {
		newResultFrame = new JFrame();
		newResultFrame.setBounds(450, 200, 550, 600);
		newResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newResultFrame.getContentPane().setLayout(null);

		JLabel lblStart = new JLabel("Roll number range starts");
		lblStart.setBounds(10, 11, 46, 20);
		newResultFrame.getContentPane().add(lblStart);

		startValue = new JTextField();
		startValue.setBounds(114, 8, 86, 20);
		startValue.setText("513616");
		newResultFrame.getContentPane().add(startValue);
		startValue.setColumns(10);

		JLabel lblEnd = new JLabel("Roll number range ends");
		lblEnd.setBounds(10, 42, 46, 14);
		newResultFrame.getContentPane().add(lblEnd);

		endValue = new JTextField();
		endValue.setBounds(114, 39, 86, 20);
		endValue.setText("513618");
		newResultFrame.getContentPane().add(endValue);
		endValue.setColumns(10);

		JLabel lblInputFile = new JLabel("Input File");
		lblInputFile.setBounds(10, 79, 80, 14);
		newResultFrame.getContentPane().add(lblInputFile);

		inputFileField = new JTextField();
		inputFileField.setBounds(114, 76, 242, 20);
		newResultFrame.getContentPane().add(inputFileField);
		inputFileField.setColumns(10);
		inputFileField.setText(
				CommonUtility.getCurrentDirectory() + GlobalConstants.BLACK_SLASH + GlobalConstants.INPUT_FILE_NAME);

		JButton browseButton = new JButton(GlobalConstants.BROWSE_TEXT);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonUtility.browseFiles(inputFileField);
			}
		});
		browseButton.setBounds(366, 75, 92, 23);
		newResultFrame.getContentPane().add(browseButton);

		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(10, 135, 46, 14);
		newResultFrame.getContentPane().add(lblClass);

		comboBox = new JComboBox<Object>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "High School", "Inter" }));
		comboBox.setBounds(114, 132, 86, 20);
		newResultFrame.getContentPane().add(comboBox);

		JLabel lblSchoolCode = new JLabel("School Code");
		lblSchoolCode.setBounds(10, 169, 80, 14);
		newResultFrame.getContentPane().add(lblSchoolCode);

		schoolCodeField = new JTextField();
		schoolCodeField.setText(GlobalConstants.SCHOOL_CODE);
		schoolCodeField.setBounds(114, 166, 86, 20);
		newResultFrame.getContentPane().add(schoolCodeField);

		JLabel lblOutputFile = new JLabel("Output File");
		lblOutputFile.setBounds(10, 207, 67, 14);
		newResultFrame.getContentPane().add(lblOutputFile);

		outputFileField = new JTextField();
		outputFileField.setForeground(new Color(0, 0, 51));
		outputFileField.setBounds(114, 204, 242, 20);
		outputFileField.setText(CommonUtility.getCurrentDirectory() + GlobalConstants.BLACK_SLASH + GlobalConstants.OUTPUT_FILE_NAME);
		newResultFrame.getContentPane().add(outputFileField);

		JButton browseButton2 = new JButton(GlobalConstants.BROWSE_TEXT);
		browseButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonUtility.browseFiles(outputFileField);
			}
		});
		browseButton2.setBounds(366, 203, 92, 23);
		newResultFrame.getContentPane().add(browseButton2);

		JButton btnSubmit = new JButton(GlobalConstants.SUBMIT);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearDisplayedValue();
				try {
					logger.debug("Submit to fetch results called!!");
					submitButtonFeatureOnResultFetching();
				} catch (IOException ioe) {
					logger.error("IOException :: " + ioe + " with message ::" + ioe.getMessage());
				} catch (Exception ex) {
					logger.error("Exception :: " + ex + " with message ::" + ex.getMessage());
				}

			}
		});
		btnSubmit.setBounds(114, 288, 89, 23);
		newResultFrame.getContentPane().add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.debug("Clear button called!!");
				startValue.setText("");
				endValue.setText("");
				inputFileField.setText("");
				outputFileField.setText("");
				showTotalInputNumber.setText("");
				processedResultField.setText("");
				timeTakenField.setText("");
				chckbxNewCheckBox.setSelected(false);
				saveResultChkBox.setSelected(false);
			}
		});
		btnClear.setBounds(247, 288, 89, 23);
		newResultFrame.getContentPane().add(btnClear);

		JLabel lblTotalStudents = new JLabel("Total Students");
		lblTotalStudents.setBounds(10, 342, 94, 14);
		newResultFrame.getContentPane().add(lblTotalStudents);

		showTotalInputNumber = new JTextField();
		showTotalInputNumber.setDisabledTextColor(new Color(0, 0, 0));
		showTotalInputNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		showTotalInputNumber.setBackground(new Color(255, 255, 255));
		showTotalInputNumber.setForeground(new Color(0, 0, 255));
		showTotalInputNumber.setSelectionColor(new Color(255, 0, 0));
		showTotalInputNumber.setEnabled(false);
		showTotalInputNumber.setBounds(114, 339, 86, 20);
		newResultFrame.getContentPane().add(showTotalInputNumber);

		JLabel lblResultMode = new JLabel("Result mode");
		lblResultMode.setBounds(10, 248, 80, 14);
		newResultFrame.getContentPane().add(lblResultMode);

		JCheckBox chckbxSummary = new JCheckBox("Summary");
		chckbxSummary.setBounds(114, 244, 97, 23);
		newResultFrame.getContentPane().add(chckbxSummary);

		JCheckBox chckbxFullResult = new JCheckBox("Full result");
		chckbxFullResult.setBounds(213, 244, 97, 23);
		newResultFrame.getContentPane().add(chckbxFullResult);

		JLabel lblResultProcessed = new JLabel("Result Processed");
		lblResultProcessed.setBounds(10, 375, 105, 14);
		newResultFrame.getContentPane().add(lblResultProcessed);

		processedResultField = new JTextField();
		processedResultField.setForeground(new Color(255, 255, 255));
		processedResultField.setBackground(new Color(255, 255, 255));
		processedResultField.setDisabledTextColor(new Color(0, 0, 0));
		processedResultField.setEnabled(false);
		processedResultField.setBounds(114, 370, 86, 20);
		newResultFrame.getContentPane().add(processedResultField);
		processedResultField.setColumns(10);

		JLabel lblTimeTaken = new JLabel("Time Taken");
		lblTimeTaken.setBounds(247, 342, 80, 14);
		newResultFrame.getContentPane().add(lblTimeTaken);

		timeTakenField = new JTextField();
		timeTakenField.setForeground(new Color(51, 51, 51));
		timeTakenField.setDisabledTextColor(new Color(0, 0, 0));
		timeTakenField.setEditable(false);
		processedResultField.setForeground(new Color(255, 255, 255));
		processedResultField.setBackground(new Color(255, 255, 255));
		processedResultField.setDisabledTextColor(new Color(0, 0, 0));
		processedResultField.setEnabled(false);
		timeTakenField.setBounds(329, 339, 86, 20);
		newResultFrame.getContentPane().add(timeTakenField);
		timeTakenField.setColumns(10);

		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(114, 102, 97, 23);
		newResultFrame.getContentPane().add(chckbxNewCheckBox);

		JLabel lblReadFile = new JLabel("Read file");
		lblReadFile.setBounds(10, 110, 80, 14);
		newResultFrame.getContentPane().add(lblReadFile);

		JLabel lblResultInA = new JLabel("Result in a file");
		lblResultInA.setBounds(247, 135, 109, 14);
		newResultFrame.getContentPane().add(lblResultInA);

		resultInFileCheckBox = new JComboBox<Object>();
		resultInFileCheckBox.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "10", "50", "100", "200", "300", "400", "500" }));
		resultInFileCheckBox.setBounds(366, 132, 46, 20);
		newResultFrame.getContentPane().add(resultInFileCheckBox);

		JLabel lblSaveResultFor = new JLabel("Save result for future use");
		lblSaveResultFor.setBounds(10, 409, 156, 14);
		newResultFrame.getContentPane().add(lblSaveResultFor);

		saveResultChkBox = new JCheckBox("");
		saveResultChkBox.setSelected(true);
		saveResultChkBox.setBounds(172, 409, 97, 23);
		newResultFrame.getContentPane().add(saveResultChkBox);

		JButton btnPreviousScreen = new JButton("Previous screen");
		btnPreviousScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.firstFrame.setVisible(true);
				window.newResultFrame.setVisible(false);
				window.savedResultFrame.setVisible(false);
			}
		});
		btnPreviousScreen.setBounds(111, 455, 133, 23);
		newResultFrame.getContentPane().add(btnPreviousScreen);

	}
	
	/**
	 * @throws IOException
	 */
	private void submitButtonFeatureOnResultFetching() throws IOException{
		if (!showAlertForInputFields()){
			if(!showAlertForInputOutputPath()) {
			long startTime = System.currentTimeMillis();
			/**
			 * Condition to check and read either from range or file
			 */
			if(chckbxNewCheckBox.isSelected()){
				logger.debug("Reading roll numbers from input file!!");
				rollNoList = ExcelReaderWriter.readNumbersFromFile(inputFileField.getText());
			}else{
				logger.debug("Reading roll numbers from input and output fields!!");
				rollNoList = CommonUtility.generateNumbersFromRange(startValue.getText(), endValue.getText());
			}
			logger.info("rollNumber list ::" + rollNoList);
			showTotalInputNumber.setText(Integer.toString(rollNoList.size()));
			
			/**
			 * Using automation fetch the results
			 */
			List<StudentBean> studentBeanList = ResultProcess.processResult(rollNoList, comboBox.getModel().getSelectedItem().toString(), schoolCodeField.getText());
			ExcelReaderWriter.writeToFile(studentBeanList, outputFileField.getText(), resultInFileCheckBox.getModel().getSelectedItem().toString());
			processedResultField.setText(Integer.toString(studentBeanList.size()));
			
			/** 
			 * Save read data for future use
			 * **/
			if(saveResultChkBox.isSelected()){
				CommonUtility.saveResult(studentBeanList, outputFileField.getText(), startValue.getText(), endValue.getText());
			}
			
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			Double truncatedDouble = BigDecimal.valueOf(elapsedTime/(1000.0 * 60))
				    .setScale(2, RoundingMode.HALF_UP)
				    .doubleValue();
			timeTakenField.setText(Double.toString(truncatedDouble));
			}
			else {
				JOptionPane.showMessageDialog(null, "Input or output path or files are not valid!!");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Input or output field is empty!!");
		}
	}
	
	/**
	 * 
	 */
	private void setupSavedResultFrame(){
		
		savedResultFrame = new JFrame();
		savedResultFrame.setBounds(450, 200, 600, 300);
		savedResultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		savedResultFrame.getContentPane().setLayout(null);
		
		JLabel lblSelectPreviousResult = new JLabel("Select previous result data");
		lblSelectPreviousResult.setBounds(10, 23, 161, 14);
		savedResultFrame.getContentPane().add(lblSelectPreviousResult);
		
		preiousResultFileField = new JTextField();
		preiousResultFileField.setBounds(181, 20, 275, 20);
		savedResultFrame.getContentPane().add(preiousResultFileField);
		preiousResultFileField.setColumns(10);
		
		JButton previousResultButton = new JButton(GlobalConstants.BROWSE_TEXT);
		previousResultButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonUtility.browseFiles(preiousResultFileField);
			}
		});
		previousResultButton.setBounds(466, 19, 89, 23);
		savedResultFrame.getContentPane().add(previousResultButton);
		
		JButton btnGetFiles = new JButton("Get files");
		btnGetFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] fileNames = CommonUtility.getSavedFileList(preiousResultFileField.getText());
				savedFilesCombo = new JComboBox<Object>();
				savedFilesCombo.setModel(new DefaultComboBoxModel<Object>(fileNames));
				savedFilesCombo.setBounds(181, 65, 275, 20);
				savedResultFrame.getContentPane().add(savedFilesCombo);
				
			}
		});
		btnGetFiles.setBounds(466, 64, 89, 23);
		savedResultFrame.getContentPane().add(btnGetFiles);
		
		JButton btnProcessResult = new JButton("Process result");
		btnProcessResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<StudentBean> studentBeanList = CommonUtility.fetchSavedResult(savedFilesCombo.getModel().getSelectedItem().toString());
					ExcelReaderWriter.writeToFile(studentBeanList, outputFileField.getText(), resultInFileCheckBox.getModel().getSelectedItem().toString());
					doneField.setText("Done!!");
				} catch (ClassNotFoundException e1) {
					logger.error(e1);
				} catch (IOException e1) {
					logger.error(e1);
				}
			}
		});
		btnProcessResult.setBounds(181, 122, 136, 23);
		savedResultFrame.getContentPane().add(btnProcessResult);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preiousResultFileField.setText("");
				doneField.setText("");
				savedFilesCombo.setModel(new DefaultComboBoxModel<Object>(new String[] {}));
			}
		});
		btnReset.setBounds(327, 122, 89, 23);
		savedResultFrame.getContentPane().add(btnReset);
		
		JButton previousScreenOnSS = new JButton("Previous screen");
		previousScreenOnSS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.firstFrame.setVisible(true);
				window.newResultFrame.setVisible(false);
				window.savedResultFrame.setVisible(false);
			}
		});
		previousScreenOnSS.setBounds(181, 211, 136, 23);
		savedResultFrame.getContentPane().add(previousScreenOnSS);
		
		doneField = new JLabel("");
		doneField.setBounds(181, 171, 46, 14);
		savedResultFrame.getContentPane().add(doneField);		
	}
	
	/** This method will show alert on the basis of conditions
	 * @return
	 */
	private boolean showAlertForInputFields() {
		boolean alertFlag = false;
		if ((!chckbxNewCheckBox.isSelected() && (startValue.getText().isEmpty() || endValue.getText().isEmpty()))) {
			alertFlag = true;
			logger.debug("One of input fields are empty!!");
		}
		return alertFlag;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean showAlertForInputOutputPath() {
		boolean alertFlag = false;
		if ((chckbxNewCheckBox.isSelected()
				&& ( (inputFileField.getText().isEmpty() || !new File(inputFileField.getText()).exists()) 
						|| (outputFileField.getText().isEmpty() || !new File(outputFileField.getText()).exists())))) {
			alertFlag = true;
			logger.debug("One of input/output files or paths are not valid!!");
		}
		return alertFlag;
	}
	
	private void clearDisplayedValue(){
		showTotalInputNumber.setText("");
		processedResultField.setText("");
		timeTakenField.setText("");
	}
}
