package com.anjz.model.hlqb;

/**
 * 
 * @author shuai.ding
 *
 * @date 2016年12月9日下午5:07:48
 */
public class HlqbRequestContent4002 extends HlqbRequestContentBase {
	private String token;
	private String merchantId="0c23d559fbb2425ab89aa7018d35ee0d";
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
}
