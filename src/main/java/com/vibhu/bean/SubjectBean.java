package com.vibhu.bean;

import java.io.Serializable;

public class SubjectBean implements Serializable{

	private static final long serialVersionUID = -1184438990915540671L;
	
	private String subject;
	private String theory1;
	private String theory2;
	private String subjectTheory;
	private String subjectPractical;
	private String subjectTotal;
	private String total;
	private String subjectGrade;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTheory1() {
		return theory1;
	}
	public void setTheory1(String theory1) {
		this.theory1 = theory1;
	}
	public String getTheory2() {
		return theory2;
	}
	public void setTheory2(String theory2) {
		this.theory2 = theory2;
	}
	public String getSubjectTheory() {
		return subjectTheory;
	}
	public void setSubjectTheory(String subjectTheory) {
		this.subjectTheory = subjectTheory;
	}
	public String getSubjectPractical() {
		return subjectPractical;
	}
	public void setSubjectPractical(String subjectPractical) {
		this.subjectPractical = subjectPractical;
	}
	public String getSubjectTotal() {
		return subjectTotal;
	}
	public void setSubjectTotal(String subjectTotal) {
		this.subjectTotal = subjectTotal;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSubjectGrade() {
		return subjectGrade;
	}
	public void setSubjectGrade(String subjectGrade) {
		this.subjectGrade = subjectGrade;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SubjectBean [subject=" + subject + ", theory1=" + theory1
				+ ", theory2=" + theory2 + ", subjectTheory=" + subjectTheory
				+ ", subjectPractical=" + subjectPractical + ", subjectTotal="
				+ subjectTotal + ", total=" + total + ", subjectGrade="
				+ subjectGrade + "]";
	}
	
}
