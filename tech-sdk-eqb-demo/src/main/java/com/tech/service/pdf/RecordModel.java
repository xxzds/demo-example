package com.tech.service.pdf;

public class RecordModel {
	private String[] strs;
	
	private Boolean isBold;

	public String[] getStrs() {
		return strs;
	}

	public void setStrs(String[] strs) {
		this.strs = strs;
	}

	public Boolean getIsBold() {
		return isBold;
	}

	public void setIsBold(Boolean isBold) {
		this.isBold = isBold;
	}
	
	public RecordModel(String[] strs,Boolean isBold) {
		this.strs=strs;
		this.isBold=isBold;
	}
	
	public RecordModel(String[] strs) {
		this.strs=strs;
		this.isBold=false;
	}
}
