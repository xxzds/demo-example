package com.anjz.jackson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjz.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jackson test
 * 需要三个包
 * jackson-core、jackson-databind、jackson-annotations
 * @author shuai.ding
 *
 * @date 2017年8月9日下午3:11:19
 */
public class JacksonTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(JacksonTest.class);
	
	public static void main(String[] args) throws ParseException, IOException {
		User user = new User();
		user.setUserName("test");
		user.setPassword("123456");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthday(simpleDateFormat.parse("1991-10-11"));
				
		/**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。 
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字 符串保存File、OutputStream等不同的介质中。 
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。 
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。 
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。 
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。 
         */  
		ObjectMapper mapper = new ObjectMapper(); 
		
		//User类转JSON字符串
		String content = mapper.writeValueAsString(user);
		//日期格式化
		mapper.setDateFormat(simpleDateFormat);
		
		LOGGER.info(content);
		
		//java集合转JSON字符串
		List<User> users = new ArrayList<User>();
		users.add(user);
		String jsonList = mapper.writeValueAsString(users);
		LOGGER.info(jsonList);
		
		/** 
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。 
         */  
		
		//JSON字符串转成java对象
		String json = "{\"userName\":\"test\",\"password\":\"123456\",\"birthday\":\"1991-10-23\"}";
		User user2 = mapper.readValue(json, User.class);
		LOGGER.info(user2.toString());
		
		//注解的用法
		User2 user21 = new User2();
		user21.setName("小民");   
		user21.setEmail("xiaomin@sina.com");  
		user21.setAge(20);  
		user21.setBirthday(simpleDateFormat.parse("1996-10-01"));   
		String json2 = mapper.writeValueAsString(user21);  
		LOGGER.info(json2);
		
		
		String json3="{\"name\":\"小民\",\"birthday\":\"1996年09月30日\",\"mail\":\"xiaomin@sina.com\",\"age\":20}";
		LOGGER.info(mapper.readValue(json3,User2.class).toString());
	}

}
