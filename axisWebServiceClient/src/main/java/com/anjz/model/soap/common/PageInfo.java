package com.anjz.model.soap.common;

import java.util.List;

public class PageInfo {
	
	private String messageId;
	private int resultCode;
	private List pageContents;
	private AccessoryContentsBaseVO accessoryContents;
	private int currentPage;
	private int rowCount;
	private int pageSize;
	private String errors;
	
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	
	public List getPageContents() {
		return pageContents;
	}
	public void setPageContents(List pageContents) {
		this.pageContents = pageContents;
	}
	public AccessoryContentsBaseVO getAccessoryContents() {
		return accessoryContents;
	}
	public void setAccessoryContents(AccessoryContentsBaseVO accessoryContents) {
		this.accessoryContents = accessoryContents;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
