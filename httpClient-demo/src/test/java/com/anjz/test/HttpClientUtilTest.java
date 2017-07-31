package com.anjz.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjz.util.HttpClientUtil;

public class HttpClientUtilTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtilTest.class);

	/**
	 * http请求，get和post 参数都会进行url编码
	 * name=%E4%BD%A0%E5%A5%BD&password=h%E5%AE%89%E5%BE%BD
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void postTest() throws UnsupportedEncodingException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", URLEncoder.encode("你好", "utf-8"));
		params.put("password", "h安徽");
//		String content = HttpClientUtil.doPost("http://localhost:9999/kj/test/ajax3", params);
		String content = HttpClientUtil.doPost("https://www.xh99d.com", null);
		LOGGER.info(content);
	}
	
	@Test
	public void getTest(){
		String content = HttpClientUtil.doGet("https://www.tooklili.com:8447/test");;
		System.out.println(content);
	}
	
	
}
