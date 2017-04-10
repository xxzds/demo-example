package com.anjz.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author ding.shuai
 * @date 2017年4月9日下午5:39:29
 */
public class HttpClientUtil {

	public static String doPost(String url, Map<String, String> params) {
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
		//模拟浏览器头部
//		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//		httpPost.setHeader("Accept-Encoding", "gzip, deflate, sdch");
//		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
//		httpPost.setHeader("Connection", "keep-alive");
//		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/53");
		
		//请求头部
		Header[] requestHeaders = httpPost.getAllHeaders();
		for(Header h:requestHeaders){
			System.out.println(h.getName() + ":" + h.getValue());
		}
		System.out.println("--------------------------------------------------");
		
		CloseableHttpResponse response = null;
		try {
			//设置编码格式，防止乱码
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(paramsList, "UTF-8");
			httpPost.setEntity(uefEntity);
			response = httpclient.execute(httpPost);	
			
			//响应头部
			Header[] responseHeaders = response.getAllHeaders();
			for (Header h : responseHeaders) {
				System.out.println(h.getName() + ":" + h.getValue());
			}
			
			System.out.println("--------------------------------------------------");
			//响应，返回的状态码
			
			StatusLine statusLine =response.getStatusLine();
			System.out.println(statusLine);
			
			//如果返回的状态码是302，从http头部获取客户端跳转的地址
			if(statusLine.getStatusCode()==HttpStatus.SC_MOVED_TEMPORARILY){
				String location = response.getFirstHeader("location").getValue();
				System.out.println("客户端跳转地址:"+location);
			}
			
			
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return  EntityUtils.toString(entity, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally {			
			try {
				if(response!=null){
					response.close();
				}
				//关闭连接,释放资源
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String doGet(String url){
		 CloseableHttpClient httpclient = HttpClients.createDefault();		 
		 // 创建httpget.    
         HttpGet httpGet = new HttpGet(url); 
             
         try {
        	// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpGet);
			
			//响应头部
			Header[] responseHeaders = response.getAllHeaders();
			for (Header h : responseHeaders) {
				System.out.println(h.getName() + ":" + h.getValue());
			}
			
			System.out.println("--------------------------------------------------");
			//响应，返回的状态码
			
			StatusLine statusLine =response.getStatusLine();
			System.out.println(statusLine);
			
			//如果返回的状态码是302，从http头部获取客户端跳转的地址
			if(statusLine.getStatusCode()==HttpStatus.SC_MOVED_TEMPORARILY){
				String location = response.getFirstHeader("location").getValue();
				System.out.println("客户端跳转地址:"+location);
			}
			
			
			// 获取响应实体    
            HttpEntity entity = response.getEntity();  
            if (entity != null) {
				return  EntityUtils.toString(entity, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
         return null;
	}
}
