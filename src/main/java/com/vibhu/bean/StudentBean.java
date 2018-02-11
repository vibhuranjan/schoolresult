package com.vibhu.bean;

import java.util.List;

public class StudentBean {
	
	private String rollNo;
	private String name;
	private String fatherName;
	private String motherName;
	
	private List<SubjectBean> subjectBeanList;
	
	private int total;
	private String status;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public List<SubjectBean> getSubjectBeanList() {
		return subjectBeanList;
	}

	public void setSubjectBeanList(List<SubjectBean> subjectBeanList) {
		this.subjectBeanList = subjectBeanList;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StudentBean [rollNo=" + rollNo + ", name=" + name
				+ ", fatherName=" + fatherName + ", motherName=" + motherName
				+ ", subjectBeanList=" + subjectBeanList + ", total=" + total
				+ ", status=" + status + "]";
	}
	
	
}
