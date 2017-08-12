package com.anjz.fastjon;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.anjz.model.User;

/**
 * fastJson
 * @author shuai.ding
 *
 * @date 2017年8月9日上午11:33:59
 */
public class FastJsonTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonTest.class);
	
	private String content;
	
	@Before
	public void getJsonString(){
		try{
			String pathStr = this.getClass().getClassLoader().getResource("test1.json").getFile();
			this.content=FileUtils.readFileToString(new File(pathStr), "utf-8");
			
			String pattern="(\\s*)";			
			content = Pattern.compile(pattern).matcher(content).replaceAll("");
			LOGGER.info(content);
			
		}catch(Exception e){
			LOGGER.error("exception:",e);
		}	
	}
	
	@Test
	public void parseJsonStr(){
		User user =JSON.parseObject(content, User.class);
		LOGGER.info(user.toString());
	}
	
	@Test
	public void toJSONString() throws ParseException{
		User user = new User();
		user.setUserName("test");
		user.setPassword("123456");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthday(simpleDateFormat.parse("1991-10-11"));
		
//		String json = JSON.toJSONString(user);
		
		//处理带有日期的对象
		String json = JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd");
		LOGGER.info(json);	
	}
}
