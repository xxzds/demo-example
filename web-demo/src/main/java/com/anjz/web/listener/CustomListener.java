package com.anjz.web.listener;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义监听器
 * @author shuai.ding
 *
 * @date 2017年5月16日下午4:03:34
 */
public class CustomListener implements ServletContextListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		LOGGER.info("执行contextInitialized");
		
		ServletContext servletContext = sce.getServletContext();
		
		Enumeration<String> enumeration = servletContext.getInitParameterNames();
		while(enumeration.hasMoreElements()){
			String key = enumeration.nextElement();
			String value = servletContext.getInitParameter(key);
			LOGGER.info("键：{},值：{}",key,value);
		}
						
		String contextPath = servletContext.getContextPath();
		String rootPath = servletContext.getRealPath("/");
		
		LOGGER.info("contextPath:{},rootPath:{}",contextPath,rootPath);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("执行contextDestroyed");
		
	}

}
