package com.anjz.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义拦截器
 * @author shuai.ding
 *
 * @date 2017年5月17日下午4:05:04
 */
public class TestFilter implements Filter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestFilter.class);
	
	private String[] include_url;
	
	private String[] exclude_url;
	
	public TestFilter() {
		LOGGER.info("TestFilter new");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("filter 初始化");
		
//		Enumeration<String> enumeration = filterConfig.getInitParameterNames();
//		while (enumeration.hasMoreElements()) {
//			String name = enumeration.nextElement();
//			String value = filterConfig.getInitParameter(name);
//			LOGGER.info("TestFilter key:{},value:{}",name,value);
//		}	
		
		
		String includeUrl = filterConfig.getInitParameter("include_url");
		String excludeUrl = filterConfig.getInitParameter("exclude_url");
		
		if(includeUrl!=null && !"".equals(includeUrl)){
			include_url = includeUrl.split(",");
		}
		
		if(excludeUrl!=null && !"".equals(excludeUrl)){
			exclude_url = excludeUrl.split(",");
		}
		
		LOGGER.info(include_url.toString());
		LOGGER.info(exclude_url.toString());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("filter doFilter");
	}

	@Override
	public void destroy() {
		LOGGER.info("filter 销毁");
	}

}
