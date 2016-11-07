package com.xxl.job.admin.core.model;

import java.util.Date;

/**
 * xxl-job log, used to track trigger process
 * @author xuxueli  2015-12-19 23:19:09
 */
public class XxlJobLog {
	
	private int id;
	
	// job info
	private int jobGroup;
	private String jobName;
	
	private String executorAddress;	// 执行器地址，有多个则逗号分隔
	private String executorHandler;	// 执行器Handler
	private String executorParam;	// 执行器，任务参数
	
	// trigger info
	private Date triggerTime;
	private String triggerStatus;
	private String triggerMsg;
	
	// handle info
	private Date handleTime;
	private String handleStatus;
	private String handleMsg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(int jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getExecutorAddress() {
		return executorAddress;
	}
	public void setExecutorAddress(String executorAddress) {
		this.executorAddress = executorAddress;
	}
	public String getExecutorHandler() {
		return executorHandler;
	}
	public void setExecutorHandler(String executorHandler) {
		this.executorHandler = executorHandler;
	}
	public String getExecutorParam() {
		return executorParam;
	}
	public void setExecutorParam(String executorParam) {
		this.executorParam = executorParam;
	}
	public Date getTriggerTime() {
		return triggerTime;
	}
	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}
	public String getTriggerStatus() {
		return triggerStatus;
	}
	public void setTriggerStatus(String triggerStatus) {
		this.triggerStatus = triggerStatus;
	}
	public String getTriggerMsg() {
		return triggerMsg;
	}
	public void setTriggerMsg(String triggerMsg) {
		this.triggerMsg = triggerMsg;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	public String getHandleStatus() {
		return handleStatus;
	}
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
	public String getHandleMsg() {
		return handleMsg;
	}
	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}
	
}
