package com.anjz.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义servlet
 * @author shuai.ding
 *
 * @date 2017年5月16日下午5:29:18
 */
public class MyServlet extends HttpServlet{
	private static final Logger LOGGER = LoggerFactory.getLogger(MyServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		//获取servlet中初始化的参数
		String value1 = this.getInitParameter("param1");
		LOGGER.info("key:{},value:{}","param1",value1);
		
		//获取ServletContext中初始化的参数
		String value2 = getServletContext().getInitParameter("test1");
		LOGGER.info("key:{},value:{}","test1",value2);
	}
}
