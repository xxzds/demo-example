package com.anjz.model;

//慧易贷支付接口请求参数1003
public class RequestContent1003 {
	private String userId;//用户id
	private String payUniqueId; //支付唯一标识
	private String payTime;   //支付时间	
	private String payMerchantId;//付款方商户id
	private String payAccount;//付款账户
	private String payAccountName;//付款账户名
	private String receiveMerchantId;//收款商户id
	private String receiveAccount;//收款账户
	private String receiveAccountName;//收款账户名
	private String type;//支付类型：0慧易贷支付1签约支付
	private String amt;//充值金额
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
	public String getPayUniqueId() {
		return payUniqueId;
	}
	public void setPayUniqueId(String payUniqueId) {
		this.payUniqueId = payUniqueId;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}	
}
