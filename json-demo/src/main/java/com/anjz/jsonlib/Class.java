package com.anjz.jsonlib;

import java.util.Date;
import java.util.List;

public class Class {
	private String name;
	private Date date;
	private List<Student> students;

	public Class() {
	}

	public Class(String name, Date date, List<Student> students) {
		this.name = name;
		this.date = date;
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
