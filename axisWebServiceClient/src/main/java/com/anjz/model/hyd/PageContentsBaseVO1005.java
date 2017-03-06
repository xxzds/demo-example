/**
 * @单位名称：科大国创—安徽慧通互联科技有限公司
 * @Copyright (c) 2016 All Rights Reserved.
 * @系统名称：KDGC-HTHL
 * @工程名称：hydService
 * @文件名称: PageContentsBaseVO1005.java
 * @类路径: com.usi.hyd.model.soap.loandetil
 */

package com.anjz.model.hyd;

import com.anjz.model.soap.common.PageContentsBaseVO;

/**
 *
 * @see
 * @author qianjunchao
 * @date 2016年8月17日 下午3:18:46
 * @version
 * @desc TODO
 */
public class PageContentsBaseVO1005 extends PageContentsBaseVO {
	private String createTime;// 创建时间
	private String name;// 交易名称
	private String flag;// 类型0待还款1已还款
	private String loanAmt;// 借款金额
	private String interest;// 利息
	private String status;// 状态0正常1逾期
	private String loanDay;// 逾期天数
	private String overdueAmt;// 逾期本金
	private String loanDays;// 借贷天数
	private String totalCredit;// 授信额度
	private String availableBalance;// 可用余额
	private String acctName;// 账户名
	private String channel;// 还款渠道wt网厅yy运营wp 微信,app
	private String overdueBalance;// 逾期利息
	private String loanTurnId;// 应还ID
	private String updateTime;// 还款时间

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoanDay() {
		return loanDay;
	}

	public void setLoanDay(String loanDay) {
		this.loanDay = loanDay;
	}

	public String getOverdueAmt() {
		return overdueAmt;
	}

	public void setOverdueAmt(String overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

	public String getLoanDays() {
		return loanDays;
	}

	public void setLoanDays(String loanDays) {
		this.loanDays = loanDays;
	}

	public String getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}

	public String getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOverdueBalance() {
		return overdueBalance;
	}

	public void setOverdueBalance(String overdueBalance) {
		this.overdueBalance = overdueBalance;
	}

	public String getLoanTurnId() {
		return loanTurnId;
	}

	public void setLoanTurnId(String loanTurnId) {
		this.loanTurnId = loanTurnId;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	@Override
	public String toString() {
		return "PageContentsBaseVO1005 [createTime=" + createTime + ", name=" + name + ", flag=" + flag + ", loanAmt="
				+ loanAmt + ", interest=" + interest + ", status=" + status + ", loanDay=" + loanDay + ", overdueAmt="
				+ overdueAmt + ", loanDays=" + loanDays + ", totalCredit=" + totalCredit + ", availableBalance="
				+ availableBalance + ", acctName=" + acctName + ", channel=" + channel + ", overdueBalance="
				+ overdueBalance + ", loanTurnId=" + loanTurnId + ", updateTime=" + updateTime + "]";
	}
	
}
