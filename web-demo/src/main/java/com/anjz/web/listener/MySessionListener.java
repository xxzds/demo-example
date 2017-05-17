package com.anjz.web.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义session 监听器
 * @author shuai.ding
 *
 * @date 2017年5月17日下午4:36:37
 */
public class MySessionListener implements HttpSessionListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MySessionListener.class);
	
	public MySessionListener() {
		LOGGER.info("MySessionListener new");
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		
		//创建时间
		Date date = new Date(session.getCreationTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTimeStr = simpleDateFormat.format(date);
		
		
		//最后访问的时间
		String lastAccessedTimes = simpleDateFormat.format(new Date(session.getLastAccessedTime()));
				
		LOGGER.info("MySessionListener sessionCreated,createTimeStr:{},lastAccessedTimes:{},sessionId:{}",createTimeStr,lastAccessedTimes,session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		LOGGER.info("MySessionListener sessionDestroyed,sessionId:{}",session.getId());
	}

}
