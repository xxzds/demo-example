package com.anjz.test;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjz.util.HttpsUtils2;

/**
 * @author shuai.ding
 * @date 2017年7月31日下午4:03:58
 */
public class HttpsUtils2Test {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpsUtils2Test.class);
	
	@Test
	public void postTest(){
		try {
			LOGGER.info(HttpsUtils2.post("https://www.tooklili.com:8447/test/index.html", null));
//			LOGGER.info(HttpsUtils2.post("https://www.xh99d.com", null));
		} catch (IOException e) {
			LOGGER.error("exception:",e);
		}
	}

}
