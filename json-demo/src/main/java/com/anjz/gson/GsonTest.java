package com.anjz.gson;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjz.model.User;
import com.google.gson.Gson;

/**
 * GSON test
 * 
 * @author shuai.ding
 *
 * @date 2017年8月9日下午2:51:24
 */
public class GsonTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(GsonTest.class);

	private String content;

	@Before
	public void getJsonString() {
		try {
			String pathStr = this.getClass().getClassLoader().getResource("test1.json").getFile();
			this.content = FileUtils.readFileToString(new File(pathStr), "utf-8");

		} catch (Exception e) {
			LOGGER.error("exception:", e);
		}
	}
	
	@Test
	public void parseJsonStr(){
		User user = new Gson().fromJson(content, User.class);
		LOGGER.info(user.toString());
	}
}
