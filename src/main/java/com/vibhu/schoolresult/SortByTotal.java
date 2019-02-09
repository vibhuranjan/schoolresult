package com.vibhu.schoolresult;

import java.util.Comparator;

import com.vibhu.bean.StudentBean;

public class SortByTotal implements Comparator<StudentBean> 
{ 
	@Override
	public int compare(StudentBean o1, StudentBean o2) {
		// TODO Auto-generated method stub
		return o2.getTotal() - o1.getTotal();
	} 
} 
