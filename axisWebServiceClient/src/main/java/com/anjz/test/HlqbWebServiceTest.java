package com.anjz.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjz.model.hlqb.HlqbPageContentBaseVo4014;
import com.anjz.model.hlqb.HlqbRequestContent4002;
import com.anjz.model.hlqb.HlqbRequestContent4014;
import com.anjz.model.hyd.PageContentsBaseVO1001;
import com.anjz.model.soap.common.PageInfo;
import com.anjz.util.Axis2Util;

/**
 * 慧联钱包接口测试
 * @author shuai.ding
 *
 * @date 2016年12月9日下午5:01:37
 */
public class HlqbWebServiceTest {
	// 日志
	private static Logger logger = LoggerFactory.getLogger(HlqbWebServiceTest.class);
	
	private String url = "http://192.2.2.13:60003/hlqbWebservice/services/MainServer?wsdl";
	private String namespace = "http://server.web.hlqb.usi.com";
	private String methodName = "RequestByClient";
	
	//令牌
	private String token="";
	
	
	
	/**
	 * token 接口
	 */
	@Before
	public void hlqb_4014_test(){
		try {
			HlqbRequestContent4014 requestContent4014 = new HlqbRequestContent4014();

			String bizType = "4014";
			PageInfo pageInfo = Axis2Util.getPageInfo(url, namespace, methodName, requestContent4014, bizType,
					new HlqbPageContentBaseVo4014(), null);
			HlqbPageContentBaseVo4014 pageContentBaseVo4014 = (HlqbPageContentBaseVo4014)pageInfo.getPageContents().get(0);
			token=pageContentBaseVo4014.getToken();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 慧易贷业务申请列表查询接口
	 */
	@Test
	public void hlqb_4002_test() {
		try {
			HlqbRequestContent4002 requestContent4002 = new HlqbRequestContent4002();
			requestContent4002.setToken(token);

			String bizType = "4002";
			PageInfo pageInfo = Axis2Util.getPageInfo(url, namespace, methodName, requestContent4002, bizType,
					new PageContentsBaseVO1001(), null);
			logger.debug(pageInfo + "");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}
}
