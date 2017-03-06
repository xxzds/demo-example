/**
 * @单位名称：科大国创—安徽慧通互联科技有限公司
 * @Copyright (c) 2016 All Rights Reserved.
 * @系统名称：KDGC-HTHL
 * @工程名称：hydService
 * @文件名称: PageContentsBaseVO1004.java
 * @类路径: com.usi.hyd.model.soap.account
 */

package com.anjz.model.hyd;

import com.anjz.model.soap.common.PageContentsBaseVO;

/**
 *
 * @see
 * @author qianjunchao
 * @date 2016年8月12日 上午11:23:25
 * @version
 * @desc TODO
 */
public class PageContentsBaseVO1004 extends PageContentsBaseVO {
	private String acctId;//账户Id
	private String totalCredit;// 授信额度
	private String availableBalance;// 可用余额
	private String unpaidAmount;// 待还金额
	private String loanAmt;// 借贷金额
	private String loanInterest;// 借贷利息
	private String loanTurnIds;//应还id字符串以|分割
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
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
	public String getUnpaidAmount() {
		return unpaidAmount;
	}
	public void setUnpaidAmount(String unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanInterest() {
		return loanInterest;
	}
	public void setLoanInterest(String loanInterest) {
		this.loanInterest = loanInterest;
	}
	public String getLoanTurnIds() {
		return loanTurnIds;
	}
	public void setLoanTurnIds(String loanTurnIds) {
		this.loanTurnIds = loanTurnIds;
	}
	@Override
	public String toString() {
		return "PageContentsBaseVO1004 [acctId=" + acctId + ", totalCredit=" + totalCredit + ", availableBalance="
				+ availableBalance + ", unpaidAmount=" + unpaidAmount + ", loanAmt=" + loanAmt + ", loanInterest="
				+ loanInterest + ", loanTurnIds=" + loanTurnIds + "]";
	}
}