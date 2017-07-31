package com.anjz.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjz.util.HttpsUtils;

/**
 * 
 * @author shuai.ding
 * @date 2017年7月31日下午12:47:27
 */
public class HttpsUtilsTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpsUtils.class);
	
	@Test
	public void httpsPost(){
		try{
			String content = HttpsUtils.post("https://www.taobao.com/", null, null, null);
			LOGGER.info(content);
		}catch(Exception e){
			LOGGER.error("exception",e);
		}
		
	}
	
}
