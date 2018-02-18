package com.vibhu.result;

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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.vibhu.bean.StudentBean;
import com.vibhu.constant.GlobalConstants;
import com.vibhu.schoolresult.ExcelReaderWriter;
import com.vibhu.schoolresult.ResultProcess;
import com.vibhu.utility.CommonUtility;

public class SchoolResult {

	private JFrame frame;
	private JTextField outputFileField;
	private JTextField inputFileField;
	private JComboBox<Object> comboBox;
	private JProgressBar progressBar;
	private JTextField showTotalInputNumber;
	private JTextField schoolCodeField;
	private JComboBox resultInFileCheckBox ;
	
	boolean flag = false;
	List<String> rollNoList = new ArrayList<String>();
	
	static final Logger logger = Logger.getLogger(SchoolResult.class);
	private JTextField processedResultField;
	private JTextField timeTakenField;
	private JTextField startValue;
	private JTextField endValue;
	private JCheckBox chckbxNewCheckBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchoolResult window = new SchoolResult();
					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.debug("Exception e :: "+e+" with message ::"+e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SchoolResult() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 200, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(10, 11, 46, 20);
		frame.getContentPane().add(lblStart);
		
		startValue = new JTextField();
		startValue.setBounds(114, 8, 86, 20);
		startValue.setText("513616");
		frame.getContentPane().add(startValue);
		startValue.setColumns(10);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setBounds(10, 42, 46, 14);
		frame.getContentPane().add(lblEnd);
		
		endValue = new JTextField();
		endValue.setBounds(114, 39, 86, 20);
		endValue.setText("513618");
		frame.getContentPane().add(endValue);
		endValue.setColumns(10);
		
		JLabel lblInputFile = new JLabel("Input File");
		lblInputFile.setBounds(10, 79, 80, 14);
		frame.getContentPane().add(lblInputFile);
		
		inputFileField = new JTextField();
		inputFileField.setBounds(114, 76, 242, 20);
		frame.getContentPane().add(inputFileField);
		inputFileField.setColumns(10);
		inputFileField.setText(GlobalConstants.INPUT_FILE_NAME);
		
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        File browserDir = new File(GlobalConstants.DIRECTORY);
		        if(browserDir.isDirectory() || browserDir.isFile()){
		        	fileChooser.setCurrentDirectory(browserDir);
		        }
		        fileChooser.setAcceptAllFileFilterUsed(false);
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		        	inputFileField.setText(fileChooser.getSelectedFile().toString());
		        }
			}
		});
		browseButton.setBounds(366, 75, 92, 23);
		frame.getContentPane().add(browseButton);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(10, 135, 46, 14);
		frame.getContentPane().add(lblClass);
		
		comboBox = new JComboBox<Object>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"High School", "Inter"}));
		comboBox.setBounds(114, 132, 86, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblSchoolCode = new JLabel("School Code");
		lblSchoolCode.setBounds(10, 169, 80, 14);
		frame.getContentPane().add(lblSchoolCode);
		
		schoolCodeField = new JTextField();
		schoolCodeField.setText(GlobalConstants.SCHOOL_CODE);
		schoolCodeField.setBounds(114, 166, 86, 20);
		frame.getContentPane().add(schoolCodeField);
		
		JLabel lblOutputFile = new JLabel("Output File");
		lblOutputFile.setBounds(10, 207, 67, 14);
		frame.getContentPane().add(lblOutputFile);
		
		outputFileField = new JTextField();
		outputFileField.setForeground(new Color(0, 0, 51));
		outputFileField.setBounds(114, 204, 242, 20);
		outputFileField.setText(GlobalConstants.OUTPUT_FILE_NAME);
		frame.getContentPane().add(outputFileField);
		
		JButton browseButton2 = new JButton("Browse");
		browseButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        File browserDir = new File(GlobalConstants.DIRECTORY);
		        if(browserDir.isDirectory() || browserDir.isFile()){
		        	fileChooser.setCurrentDirectory(browserDir);
		        }
		        fileChooser.setAcceptAllFileFilterUsed(false);
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		        	outputFileField.setText(fileChooser.getSelectedFile().toString());
		        }
			}
		});
		browseButton2.setBounds(366, 203, 92, 23);
		frame.getContentPane().add(browseButton2);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearDisplayedValue();
				try {
					if (!showAlert()){
						long startTime = System.currentTimeMillis();
						if(chckbxNewCheckBox.isSelected()){
							rollNoList = ExcelReaderWriter.readNumbersFromFile(inputFileField.getText());
						}else{
							rollNoList = CommonUtility.generateNumbersFromRange(startValue.getText(), endValue.getText());
						}
						logger.debug("rollNumber list ::"+rollNoList);
						showTotalInputNumber.setText(Integer.toString(rollNoList.size()));
						List<StudentBean> studentBeanList = ResultProcess.processResult(rollNoList, comboBox.getModel().getSelectedItem().toString(), schoolCodeField.getText());
						ExcelReaderWriter.writeToFile(studentBeanList, outputFileField.getText(), resultInFileCheckBox.getModel().getSelectedItem().toString());
						processedResultField.setText(Integer.toString(studentBeanList.size()));
						long stopTime = System.currentTimeMillis();
						long elapsedTime = stopTime - startTime;
						Double truncatedDouble = BigDecimal.valueOf(elapsedTime/(1000.0 * 60))
							    .setScale(2, RoundingMode.HALF_UP)
							    .doubleValue();
						timeTakenField.setText(Double.toString(truncatedDouble));
					}
					else{
						JOptionPane.showMessageDialog(null, "Input or output field is empty!!");
					}
					
				} catch (IOException ioe) {
					logger.error("IOException :: "+ioe+" with message ::"+ioe.getMessage());
				} catch (Exception ex) {
					logger.error("Exception :: "+ex+" with message ::"+ex.getMessage());
				}
				
			}
		});
		btnSubmit.setBounds(114, 288, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startValue.setText("");
				endValue.setText("");
				inputFileField.setText("");
				outputFileField.setText("");
				showTotalInputNumber.setText("");
				processedResultField.setText("");
				timeTakenField.setText("");
				chckbxNewCheckBox.setSelected(false);
			}
		});
		btnClear.setBounds(247, 288, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JLabel lblTotalStudents = new JLabel("Total Students");
		lblTotalStudents.setBounds(10, 342, 94, 14);
		frame.getContentPane().add(lblTotalStudents);
		
		showTotalInputNumber = new JTextField();
		showTotalInputNumber.setDisabledTextColor(new Color(0, 0, 0));
		showTotalInputNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		showTotalInputNumber.setBackground(new Color(255, 255, 255));
		showTotalInputNumber.setForeground(new Color(0, 0, 255));
		showTotalInputNumber.setSelectionColor(new Color(255, 0, 0));
		showTotalInputNumber.setEnabled(false);
		showTotalInputNumber.setBounds(114, 339, 86, 20);
		frame.getContentPane().add(showTotalInputNumber);
		
		JLabel lblResultMode = new JLabel("Result mode");
		lblResultMode.setBounds(10, 248, 80, 14);
		frame.getContentPane().add(lblResultMode);
		
		JCheckBox chckbxSummary = new JCheckBox("Summary");
		chckbxSummary.setBounds(114, 244, 97, 23);
		frame.getContentPane().add(chckbxSummary);
		
		JCheckBox chckbxFullResult = new JCheckBox("Full result");
		chckbxFullResult.setBounds(213, 244, 97, 23);
		frame.getContentPane().add(chckbxFullResult);
		
		JLabel lblResultProcessed = new JLabel("Result Processed");
		lblResultProcessed.setBounds(10, 375, 105, 14);
		frame.getContentPane().add(lblResultProcessed);
		
		processedResultField = new JTextField();
		processedResultField.setForeground(new Color(255, 255, 255));
		processedResultField.setBackground(new Color(255, 255, 255));
		processedResultField.setDisabledTextColor(new Color(0, 0, 0));
		processedResultField.setEnabled(false);
		processedResultField.setBounds(114, 370, 86, 20);
		frame.getContentPane().add(processedResultField);
		processedResultField.setColumns(10);
		
		JLabel lblTimeTaken = new JLabel("Time Taken");
		lblTimeTaken.setBounds(247, 342, 80, 14);
		frame.getContentPane().add(lblTimeTaken);
		
		timeTakenField = new JTextField();
		timeTakenField.setForeground(new Color(51, 51, 51));
		timeTakenField.setDisabledTextColor(new Color(0, 0, 0));
		timeTakenField.setEditable(false);
		processedResultField.setForeground(new Color(255, 255, 255));
		processedResultField.setBackground(new Color(255, 255, 255));
		processedResultField.setDisabledTextColor(new Color(0, 0, 0));
		processedResultField.setEnabled(false);
		timeTakenField.setBounds(329, 339, 86, 20);
		frame.getContentPane().add(timeTakenField);
		timeTakenField.setColumns(10);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(114, 102, 97, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JLabel lblReadFile = new JLabel("Read file");
		lblReadFile.setBounds(10, 110, 80, 14);
		frame.getContentPane().add(lblReadFile);
		
		JLabel lblResultInA = new JLabel("Result in a file");
		lblResultInA.setBounds(247, 135, 109, 14);
		frame.getContentPane().add(lblResultInA);
		
		resultInFileCheckBox = new JComboBox();
		resultInFileCheckBox.setModel(new DefaultComboBoxModel(new String[] {"10", "50", "100", "200", "300", "400", "500"}));
		resultInFileCheckBox.setBounds(366, 132, 46, 20);
		frame.getContentPane().add(resultInFileCheckBox);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(114, 256, 146, 14);
		//frame.getContentPane().add(progressBar);
		
	}
	
	private boolean showAlert(){
		boolean alertFlag = false;
		if((!chckbxNewCheckBox.isSelected() && (startValue.getText().isEmpty()|| endValue.getText().isEmpty()))
				|| (chckbxNewCheckBox.isSelected() && inputFileField.getText().isEmpty())
				|| (outputFileField.getText().isEmpty())){
			alertFlag = true;
		}
		return alertFlag;
	}
	
	private void clearDisplayedValue(){
		showTotalInputNumber.setText("");
		processedResultField.setText("");
		timeTakenField.setText("");
	}
}
