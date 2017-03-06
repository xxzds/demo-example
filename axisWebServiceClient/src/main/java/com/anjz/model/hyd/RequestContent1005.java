/**
 * @单位名称：科大国创—安徽慧通互联科技有限公司
 * @Copyright (c) 2016 All Rights Reserved.
 * @系统名称：KDGC-HTHL
 * @工程名称：hydService
 * @文件名称: RequestContent1005.java
 * @类路径: com.usi.hyd.model.soap.loandetil
 */

package com.anjz.model.hyd;

/**
 *
 * @see
 * @author qianjunchao
 * @date 2016年8月17日 下午3:18:21
 * @version
 * @desc TODO
 */
public class RequestContent1005 {
	private String userId;// 用户id
	private String flag;// 全部传null 00已还款 01未还款
	private String currentPage;// 当前页
	private String pageSize;// 每页显示条数
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String acctName;// 账户名
	private String status;// null全部0正常1逾期
	private String loanTurnId;// 应还ID

	public String getLoanTurnId() {
		return loanTurnId;
	}

	public void setLoanTurnId(String loanTurnId) {
		this.loanTurnId = loanTurnId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
