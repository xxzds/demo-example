package com.anjz.test;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjz.model.PageContentsBaseVO1001;
import com.anjz.model.RequestContent1001;
import com.anjz.model.RequestContent1002;
import com.anjz.model.soap.common.PageInfo;
import com.anjz.util.Axis2Util;
import com.anjz.util.UUIDGenerator;
import com.anjz.util.XMLDomUtil;

public class HydWebServiceTest {
	
	// 日志
	private static Logger logger = LoggerFactory.getLogger(HydWebServiceTest.class);
	
	private String url = "http://localhost:8080/hydWebservice/services/MainServer?wsdl";
	
	/**
	 * 账户申请业务
	 */
	@Test
	public void axis2_1001_test(){		
		try {
			// 指定方法的参数值
			RequestContent1001 requestContent1001 = new RequestContent1001();
			requestContent1001.setUserId("用户ID");
			requestContent1001.setCreateTime("2016-11-03 16:30:15");
			requestContent1001.setAcctName("XXZ");
			requestContent1001.setMobileNo("18256915945");
			requestContent1001.setTotalCredit("10000");
			String bizType = "1001";
			PageInfo pageInfo = Axis2Util.getPageInfo(url, "http://server.web.hyd.usi.com", "RequestByClient",
					requestContent1001, bizType, new PageContentsBaseVO1001(), null);
			// 客户端VO名字不必和服务端一致，只要VO属性名字一致就OK！
			System.out.println(pageInfo.getPageContents());
			logger.debug(pageInfo+"");
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	
	@Test
	public void axis2_1001_test2(){		
		try {
			// 指定方法的参数值
			RequestContent1001 requestContent1001 = new RequestContent1001();
			requestContent1001.setUserId("用户ID");
			requestContent1001.setCreateTime("2016-11-03 16:30:15");
			requestContent1001.setAcctName("XXZ");
			requestContent1001.setMobileNo("18256915945");
			requestContent1001.setTotalCredit("10000");
			String bizType = "1001";
			
		    RPCServiceClient serviceClient = new RPCServiceClient();  
            Options options = serviceClient.getOptions();  
            EndpointReference targetEPR = new EndpointReference(url);  
            options.setTo(targetEPR);  
            QName opAddEntry = new QName("http://server.web.hyd.usi.com","RequestByClient");   
              
            //设置请求唯一标识
			String messageId =UUIDGenerator.getUUID();
            String requestContent =XMLDomUtil.getStringByObject(requestContent1001,messageId);
            logger.info("请求的xml：\n"+requestContent);
            Object[] parameters = new Object[] {bizType,requestContent};
            
            
            OMElement element = serviceClient.invokeBlocking(opAddEntry, parameters);
            logger.info("响应的xml：\n"+element.getFirstElement().getText());
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	/**
	 * 充值接口
	 */
	@Test
	public void axis2_1002_test(){
		try {
			// 指定参数值
			RequestContent1002 rc = new RequestContent1002();
			String bizType = "1002";
			rc.setUserId("用户ID");
			rc.setPayMerchantId("0c23d559fbb2425ab89aa7018d35ee0d");
			rc.setPayAccount("付款方");
			rc.setPayAccountName("付款名");
			rc.setReceiveMerchantId("收款商户id");
			rc.setReceiveAccount("收款方");
			rc.setReceiveAccountName("收款名");
			rc.setAmt("5000");
			rc.setType("0");
			rc.setFlag("1");
//			rc.setLoanTurnIds("42c7416930a14c9d82bbc4df44c0d2f2|a043b283a541451aabcf39a95a84a98f");
//			rc.setLoanTurnId("42c7416930a14c9d82bbc4df44c0d2f2");
			rc.setChannel("00");
			rc.setTranDes("123号码");
			rc.setTranNo("123");
			rc.setLoanTurnId("c37dc3e1d90b4642afe982d9c22451c7");
			PageInfo pageInfo = Axis2Util.getPageInfo(url, "http://server.web.hyd.usi.com", "RequestByClient", rc,
					bizType, null, null);
			logger.debug(pageInfo+"");
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
