package com.anjz.test;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjz.model.hyd.PageContentsBaseVO1001;
import com.anjz.model.hyd.PageContentsBaseVO1003;
import com.anjz.model.hyd.PageContentsBaseVO1004;
import com.anjz.model.hyd.PageContentsBaseVO1005;
import com.anjz.model.hyd.RequestContent1001;
import com.anjz.model.hyd.RequestContent1002;
import com.anjz.model.hyd.RequestContent1003;
import com.anjz.model.hyd.RequestContent1004;
import com.anjz.model.hyd.RequestContent1005;
import com.anjz.model.hyd.RequestContent1006;
import com.anjz.model.soap.common.PageInfo;
import com.anjz.util.Axis2Util;
import com.anjz.util.UUIDGenerator;
import com.anjz.util.XMLDomUtil;

/**
 * 慧易贷接口测试
 * @author shuai.ding
 *
 * @date 2016年12月9日下午5:01:14
 */
public class HydWebServiceTest {
	
	// 日志
	private static Logger logger = LoggerFactory.getLogger(HydWebServiceTest.class);
	
	private String url = "http://localhost:8080/hydWebservice/services/MainServer?wsdl";
	private String namespace = "http://server.web.hyd.usi.com";
	
	/**
	 * 账户申请业务
	 */
	@Test
	public void axis2_1001_test(){		
		try {
			// 指定方法的参数值
			RequestContent1001 requestContent1001 = new RequestContent1001();
			requestContent1001.setUserId("用户ID");
			requestContent1001.setCreateTime("2016-11-017 16:30:15");
			requestContent1001.setAcctName("XXZ");
			requestContent1001.setMobileNo("18256915945");
			requestContent1001.setTotalCredit("10000");
			String bizType = "1001";
			PageInfo pageInfo = Axis2Util.getPageInfo(url, namespace, "RequestByClient",
					requestContent1001, bizType, new PageContentsBaseVO1001(), null);
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
	 * 充值接口（还款）
	 * @throws Exception 
	 */
	@Test
	public void axis2_1002_test() throws Exception{
		RequestContent1002 rc = new RequestContent1002();		
		rc.setUserId("用户ID");
		rc.setPayMerchantId("0c23d559fbb2425ab89aa7018d35ee0d");
		rc.setPayAccount("付款方");
		rc.setPayAccountName("付款名");
		rc.setReceiveMerchantId("收款商户id");
		rc.setReceiveAccount("收款方");
		rc.setReceiveAccountName("收款名");
		rc.setAmt("101");
		rc.setType("0");
		
		//多笔
//		rc.setFlag("1");
//		rc.setLoanTurnIds("11d55becaabc4aa3aa49f33fb7066148");
//		rc.setLoanTurnIds("42c7416930a14c9d82bbc4df44c0d2f2|a043b283a541451aabcf39a95a84a98f");
		
		
		//单笔
		rc.setFlag("2");
		rc.setLoanTurnId("11d55becaabc4aa3aa49f33fb7066148");
		rc.setChannel("00");		
		rc.setTranNo("交易号");
		rc.setTranDes("交易描述");
		
		String bizType = "1002";
		PageInfo pageInfo = Axis2Util.getPageInfo(url, namespace, "RequestByClient", rc,
				bizType, null, null);
		logger.info(pageInfo.toString());
	}
	
	/**
	 * 支付接口(借款)
	 * @throws Exception 
	 */
	@Test
	public void axis2_1003_test() throws Exception{
		RequestContent1003 rc1003=new RequestContent1003();
		rc1003.setUserId("用户ID");
		rc1003.setPayUniqueId("支付唯一标识");
		rc1003.setPayTime("2016-11-17 12:00:01");
		rc1003.setPayAccount("付款方");
		rc1003.setPayAccountName("付款名");
		rc1003.setPayMerchantId("付款商户id");
		rc1003.setReceiveAccount("收款方");
		rc1003.setReceiveAccountName("收款名");
		rc1003.setReceiveMerchantId("收款商户id");
		rc1003.setType("0");
		rc1003.setAmt("100");
		
		String bizType = "1003";
		
		PageInfo pageInfo = Axis2Util.getPageInfo(url, namespace, "RequestByClient",
				rc1003, bizType, new PageContentsBaseVO1003(), null);
		
		logger.info(pageInfo.toString());
	}
	
	/**
	 * 慧易贷账户信息查询
	 * @throws Exception 
	 */
	@Test
	public void axis2_1004_test() throws Exception{
		RequestContent1004 rc1004=new RequestContent1004();
		rc1004.setUserId("用户ID");
		String bizType = "1004";
		
		PageInfo pageInfo = Axis2Util.getPageInfo(url, namespace, "RequestByClient",
				rc1004, bizType, new PageContentsBaseVO1004(), null);
		logger.info(pageInfo.toString());
	}
	
	/**
	 * 慧易贷还款/待还款查询
	 * @throws Exception 
	 * 
	 */
	@Test
	public void axis2_1005_test() throws Exception{
		RequestContent1005 rc1005=new RequestContent1005();
		rc1005.setUserId("用户ID");
		rc1005.setFlag(null);
		rc1005.setCurrentPage("1");
		rc1005.setPageSize("5");
		
		String bizType = "1005";
		PageInfo pageInfo = Axis2Util.getPageInfo(url, namespace, "RequestByClient",
				rc1005, bizType, new PageContentsBaseVO1005(), null);
		logger.info(pageInfo.toString());
	}
	
	
	/**
	 * 慧易贷账户信息修改接口
	 * @throws Exception
	 */
	@Test
	public void axis2_1006_test() throws Exception{
		RequestContent1006 rc1006 = new RequestContent1006();
		rc1006.setUserId("用户ID");
		rc1006.setUserName("XXZ");
		rc1006.setMobileNo("18234567898");
		//授信额度
		rc1006.setTotalCredit("99999");
		
		String bizType = "1006";
		PageInfo pageInfo = Axis2Util.getPageInfo(url, namespace, "RequestByClient",
				rc1006, bizType, null, null);
		logger.info(pageInfo.toString());
	}

}
