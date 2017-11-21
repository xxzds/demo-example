package com.anjz.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  4.5.2
 * @date 2017年11月21日上午9:24:07
 */
public class HttpClientUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
	
	public static String doPost(String url, Map<String, String> params){
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);

		// 创建参数
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		if (params != null) {
			for (Entry<String, String> param : params.entrySet()) {
				paramsList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
		}
		
		CloseableHttpResponse response = null;
		try {
			//设置编码格式，防止乱码
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(paramsList, "UTF-8");
			httpPost.setEntity(uefEntity);
			response = httpclient.execute(httpPost);	
			
			//获取返回实体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return  EntityUtils.toString(entity, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("UnsupportedEncodingException",e);
		} catch (IOException e) {		
			LOGGER.error("IOException",e);
		}catch (Exception e) {
			LOGGER.error("Exception",e);
		}finally {			
			try {
				if(response!=null){
					response.close();
				}
				//关闭连接,释放资源
				httpclient.close();
			} catch (IOException e) {
				LOGGER.error("IOException",e);
			}
		}
		return null;
	}
	
	public static String doGet(String url){
		 CloseableHttpClient httpclient = HttpClients.createDefault();		 
		 // 创建httpget.    
        HttpGet httpGet = new HttpGet(url); 
         
        CloseableHttpResponse response = null;
        try {
        	// 执行get请求.
			response = httpclient.execute(httpGet);
			
			// 获取响应实体    
           HttpEntity entity = response.getEntity();  
           if (entity != null) {
				return  EntityUtils.toString(entity, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			LOGGER.error("ClientProtocolException",e);
		} catch (IOException e) {
			LOGGER.error("IOException",e);
		}catch (Exception e) {
			LOGGER.error("Exception",e);
		}finally {
			try {
				if(response!=null){
					response.close();
				}
				//关闭连接,释放资源
				httpclient.close();
			} catch (IOException e) {
				LOGGER.error("IOException",e);
			}
		}
        return null;
	}
	
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<>();
		params.put("volumnCode", "OUP001");
		String content =  HttpClientUtils.doPost("http://api.koukao.cn/baseResource/textbook/getLessonList", params);
		LOGGER.info(content);
	}

}
