package com.vibhu.result;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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

public class SchoolResult {

	private JFrame frame;
	private JTextField outputFileField;
	private JTextField inputFileField;
	private JComboBox<Object> comboBox;
	private JProgressBar progressBar;
	private JTextField showTotalInputNumber;
	private JTextField schoolCodeField;
	
	boolean flag = false;
	List<String> rollNoList = new ArrayList<String>();
	
	static final Logger logger = Logger.getLogger(SchoolResult.class);
	private JTextField processedResultField;
	
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
		frame.setBounds(450, 200, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInputFile = new JLabel("Input File");
		lblInputFile.setBounds(10, 11, 80, 14);
		frame.getContentPane().add(lblInputFile);
		
		inputFileField = new JTextField();
		inputFileField.setBounds(114, 8, 242, 20);
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
		browseButton.setBounds(366, 7, 92, 23);
		frame.getContentPane().add(browseButton);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(10, 39, 46, 14);
		frame.getContentPane().add(lblClass);
		
		comboBox = new JComboBox<Object>();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"High School", "Inter"}));
		comboBox.setBounds(114, 36, 80, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblSchoolCode = new JLabel("School Code");
		lblSchoolCode.setBounds(10, 73, 80, 14);
		frame.getContentPane().add(lblSchoolCode);
		
		schoolCodeField = new JTextField();
		schoolCodeField.setEditable(false);
		schoolCodeField.setText(GlobalConstants.SCHOOL_CODE);
		schoolCodeField.setBounds(114, 70, 86, 20);
		frame.getContentPane().add(schoolCodeField);
		
		JLabel lblOutputFile = new JLabel("Output File");
		lblOutputFile.setBounds(10, 112, 67, 14);
		frame.getContentPane().add(lblOutputFile);
		
		outputFileField = new JTextField();
		outputFileField.setForeground(new Color(0, 0, 51));
		outputFileField.setBounds(114, 109, 242, 20);
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
		browseButton2.setBounds(366, 108, 92, 23);
		frame.getContentPane().add(browseButton2);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!inputFileField.getText().isEmpty() && !outputFileField.getText().isEmpty()){
						long startTime = System.currentTimeMillis();
						rollNoList = ExcelReaderWriter.readNumbersFromFile(inputFileField.getText());
						logger.debug("rollNumber list ::"+rollNoList);
						showTotalInputNumber.setText(Integer.toString(rollNoList.size()));
						List<StudentBean> studentBeanList = ResultProcess.processResult(rollNoList, comboBox.getModel().getSelectedItem().toString(), schoolCodeField.getText());
						ExcelReaderWriter.writeToFile(studentBeanList, outputFileField.getText());
						processedResultField.setText(Integer.toString(studentBeanList.size()));
						long stopTime = System.currentTimeMillis();
						long elapsedTime = stopTime - startTime;
					    System.out.println(elapsedTime/1000.0);
					}
					else{
						JOptionPane.showMessageDialog(null, "Input or output file field is empty!!");
					}
					
				} catch (IOException ioe) {
					logger.error("IOException :: "+ioe+" with message ::"+ioe.getMessage());
				}
				
			}
		});
		btnSubmit.setBounds(114, 222, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clear clicked...");
			}
		});
		btnClear.setBounds(253, 222, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JLabel lblTotalStudents = new JLabel("Total Students");
		lblTotalStudents.setBounds(10, 281, 94, 14);
		frame.getContentPane().add(lblTotalStudents);
		
		showTotalInputNumber = new JTextField();
		showTotalInputNumber.setDisabledTextColor(new Color(0, 0, 0));
		showTotalInputNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		showTotalInputNumber.setBackground(new Color(255, 255, 255));
		showTotalInputNumber.setForeground(new Color(0, 0, 255));
		showTotalInputNumber.setSelectionColor(new Color(255, 0, 0));
		showTotalInputNumber.setEnabled(false);
		showTotalInputNumber.setBounds(114, 278, 86, 20);
		frame.getContentPane().add(showTotalInputNumber);
		
		JLabel lblResultMode = new JLabel("Result mode");
		lblResultMode.setBounds(10, 161, 80, 14);
		frame.getContentPane().add(lblResultMode);
		
		JCheckBox chckbxSummary = new JCheckBox("Summary");
		chckbxSummary.setBounds(114, 157, 97, 23);
		frame.getContentPane().add(chckbxSummary);
		
		JCheckBox chckbxFullResult = new JCheckBox("Full result");
		chckbxFullResult.setBounds(213, 157, 97, 23);
		frame.getContentPane().add(chckbxFullResult);
		
		JLabel lblResultProcessed = new JLabel("Result Processed");
		lblResultProcessed.setBounds(10, 314, 94, 14);
		frame.getContentPane().add(lblResultProcessed);
		
		processedResultField = new JTextField();
		processedResultField.setForeground(new Color(255, 255, 255));
		processedResultField.setBackground(new Color(255, 255, 255));
		processedResultField.setDisabledTextColor(new Color(0, 0, 0));
		processedResultField.setEnabled(false);
		processedResultField.setBounds(114, 309, 86, 20);
		frame.getContentPane().add(processedResultField);
		processedResultField.setColumns(10);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(114, 256, 146, 14);
		//frame.getContentPane().add(progressBar);
		
	}
}
