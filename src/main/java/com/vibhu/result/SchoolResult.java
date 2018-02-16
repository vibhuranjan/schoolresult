package com.vibhu.result;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JCheckBox;

public class SchoolResult {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;

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
					e.printStackTrace();
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
		frame.setBounds(200, 200, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInputFile = new JLabel("Input File");
		lblInputFile.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblInputFile);
		
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(10, 39, 46, 14);
		frame.getContentPane().add(lblClass);
		
		JLabel lblSchoolCode = new JLabel("School Code");
		lblSchoolCode.setBounds(10, 73, 67, 14);
		frame.getContentPane().add(lblSchoolCode);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("1048");
		textField_1.setBounds(102, 70, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblOutputFile = new JLabel("Output File");
		lblOutputFile.setBounds(10, 112, 67, 14);
		frame.getContentPane().add(lblOutputFile);
		
		textField_2 = new JTextField();
		textField_2.setBounds(102, 109, 228, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(102, 8, 228, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        fileChooser.setAcceptAllFileFilterUsed(false);
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		        	textField.setText(fileChooser.getSelectedFile().toString());
		        }
			}
		});
		btnBrowse.setBounds(352, 7, 92, 23);
		frame.getContentPane().add(btnBrowse);
		
		JButton button = new JButton("Browse");
		button.setBounds(352, 108, 92, 23);
		frame.getContentPane().add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"High School", "Inter"}));
		comboBox.setBounds(102, 36, 80, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(102, 222, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(220, 222, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JLabel lblResultMode = new JLabel("Result mode");
		lblResultMode.setBounds(10, 161, 67, 14);
		frame.getContentPane().add(lblResultMode);
		
		JCheckBox chckbxSummary = new JCheckBox("Summary");
		chckbxSummary.setBounds(102, 157, 97, 23);
		frame.getContentPane().add(chckbxSummary);
		
		JCheckBox chckbxFullResult = new JCheckBox("Full result");
		chckbxFullResult.setBounds(102, 183, 97, 23);
		frame.getContentPane().add(chckbxFullResult);
	}
}
