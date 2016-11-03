package com.anjz.util;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.log4j.Logger;

import com.anjz.model.soap.common.AccessoryContentsBaseVO;
import com.anjz.model.soap.common.PageContentsBaseVO;
import com.anjz.model.soap.common.PageInfo;

/**
 *
 * @see		
 * @author  xuxile
 * @date	2016年1月19日 下午3:42:46
 * @version	 
 * @desc    调用外部Axis2接口工具类
 */
public class Axis2Util {
	private static Logger logger = Logger.getLogger(Axis2Util.class);
	
	/**
	 * 公用调用接口方法
	 * @param address 服务地址 例：http://127.0.0.1:8080/hydWebservice/services/StudentServer?wsdl
	 * @param namespaceURI  命名空间URL 例：http://demo.service.hyd.usi.com
	 * @param methodName  方法名 例：RequestByClient
	 * @param requestContentVo  请求参数封装的对象 例：studentInfo
	 * @param bizType  业务编号 例：1012
	 * @param pageContents  列表查询VO(可NULL) 例：new StudentInfo()
	 * @param accessoryContents  汇总信息VO(可NULL) 例：new StudentSum()
	 * @return pageInfo 返回PageInfo对象
	 * */
	public static PageInfo getPageInfo(String address,String namespaceURI,String methodName,Object requestContentVo,String bizType,PageContentsBaseVO pageContents,AccessoryContentsBaseVO accessoryContents) throws Exception{
		   // 使用RPC方式调用WebService
			RPCServiceClient serviceClient = new RPCServiceClient();
			// 指定调用WebService的URL
			EndpointReference targetEPR = new EndpointReference(address);
			Options options = serviceClient.getOptions();
			// 确定目标服务地址
			options.setTo(targetEPR);
			// 确定调用方法
			options.setAction("urn:"+methodName);
			options.setTimeOutInMilliSeconds(180000);
			QName qname = new QName(namespaceURI,methodName);
			//设置请求唯一标识
			String messageId =UUIDGenerator.getUUID();
			// 指定RequestByClient方法的参数值
			String requestContent =XMLDomUtil.getStringByObject(requestContentVo,messageId);
			logger.info("请求的xml：\n"+requestContent);
			
			Object[] parameters = new Object[] {bizType,requestContent};
			// 调用方法一 传递参数，调用服务，获取服务返回结果集
			OMElement element = serviceClient.invokeBlocking(qname, parameters);
			// 值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
			String resultXml = element.getFirstElement().getText();
			logger.info("响应的xml：\n"+resultXml);
			
			PageInfo pageInfo=XMLDomUtil.getResultFromXml(resultXml,pageContents,accessoryContents);
			serviceClient.cleanupTransport();
			return pageInfo;
	}
}