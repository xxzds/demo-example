/**
 * @单位名称：科大国创—安徽慧通互联科技有限公司
 * @Copyright (c) 2016 All Rights Reserved.
 * @系统名称：KDGC-HTHL
 * @工程名称：hydService
 * @文件名称: RequestContent1006.java
 * @类路径: com.usi.hyd.model.soap.account
 */

package com.anjz.model;

/**
 *
 * @see
 * @author qianjunchao
 * @date 2016年9月27日 下午3:47:16
 * @version
 * @desc TODO
 */
public class RequestContent1006 {
	private String userId;// 用户id
	private String mobileNo;// 手机号
	private String userName;// 用户名(账号)
	private String totalCredit;// 新的授信额度

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}
}
