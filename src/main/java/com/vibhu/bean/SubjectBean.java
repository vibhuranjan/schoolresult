package com.vibhu.bean;

import java.io.Serializable;

public class SubjectBean implements Serializable{

	private static final long serialVersionUID = -1184438990915540671L;
	
	private String subject;
	private String subjectTheory;
	private String subjectPractical;
	private String subjectTotal;
	private String subjectGrade;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String getSubjectGrade() {
		return subjectGrade;
	}
	public void setSubjectGrade(String subjectGrade) {
		this.subjectGrade = subjectGrade;
	}
	@Override
	public String toString() {
		return "SubjectBean [subject=" + subject + ", subjectTheory="
				+ subjectTheory + ", subjectPractical=" + subjectPractical
				+ ", subjectTotal=" + subjectTotal + ", subjectGrade="
				+ subjectGrade + "]";
	}
	
	
}
