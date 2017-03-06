/**
 * @单位名称：科大国创—安徽慧通互联科技有限公司
 * @Copyright (c) 2016 All Rights Reserved.
 * @系统名称：KDGC-HTHL
 * @工程名称：hydService
 * @文件名称: RequestContent1002.java
 * @类路径: com.usi.hyd.model.soap.recharge
 */

package com.anjz.model.hyd;

/**
 *
 * @see
 * @author qianjunchao
 * @date 2016年8月17日 上午11:16:29
 * @version
 * @desc TODO
 */
public class RequestContent1002 {
	private String userId;// 用户id
	private String payMerchantId;// 付款方商户ID
	private String payAccount;// 付款账户
	private String payAccountName;// 付款账户名
	private String receiveMerchantId;// 收款方商户ID
	private String receiveAccount;// 收款账户
	private String receiveAccountName;// 收款账户名
	private String type;// 充值类型0钱包1慧易贷
	private String amt;// 充值金额
	private String flag;// 1全部2单个
	private String loanTurnId;// 应还id(单个时传)
	private String channel;// 预存方式00银联转账01pos机
	private String tranNo;// 交易号
	private String tranDes;// 交易描述
	private String loanTurnIds;// 应还id字符串（全部时传）应还id字符串以|分割

	public String getLoanTurnIds() {
		return loanTurnIds;
	}

	public void setLoanTurnIds(String loanTurnIds) {
		this.loanTurnIds = loanTurnIds;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getTranDes() {
		return tranDes;
	}

	public void setTranDes(String tranDes) {
		this.tranDes = tranDes;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPayMerchantId() {
		return payMerchantId;
	}

	public void setPayMerchantId(String payMerchantId) {
		this.payMerchantId = payMerchantId;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPayAccountName() {
		return payAccountName;
	}

	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}

	public String getReceiveMerchantId() {
		return receiveMerchantId;
	}

	public void setReceiveMerchantId(String receiveMerchantId) {
		this.receiveMerchantId = receiveMerchantId;
	}

	public String getReceiveAccount() {
		return receiveAccount;
	}

	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}

	public String getReceiveAccountName() {
		return receiveAccountName;
	}

	public void setReceiveAccountName(String receiveAccountName) {
		this.receiveAccountName = receiveAccountName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLoanTurnId() {
		return loanTurnId;
	}

	public void setLoanTurnId(String loanTurnId) {
		this.loanTurnId = loanTurnId;
	}
}
