package com.anjz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.anjz.util.CookieUtils;

/**
 * 
 * @author ding.shuai
 * @date 2017年7月24日下午10:20:10
 */
public class TestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		
		for(Cookie cookie:cookies){
			System.out.println("----------------------------------");
			System.out.println("name="+cookie.getName());
			System.out.println("value="+cookie.getValue());
			System.out.println("domain="+cookie.getDomain());
			System.out.println("secure="+cookie.getSecure());
			System.out.println("----------------------------------");
		}
		
		HttpSession session = req.getSession();
		String sessionId = session.getId();
		
		//设置cookie
		CookieUtils.addCookie("test", "123", resp);
		CookieUtils.addCookie("test2", "1234", -1, "/", "www.baidu.com", false, resp);
		
		resp.getWriter().write("sessionId="+sessionId);
		resp.getWriter().flush();
	}

}
