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
		
		//默认从客户端传入的cookie，只传了name，value
		for(Cookie cookie:cookies){
			System.out.println("----------------------------------");
			System.out.println("name="+cookie.getName());
			System.out.println("value="+cookie.getValue());
			System.out.println("domain="+cookie.getDomain());
			System.out.println("maxAge="+cookie.getMaxAge());
			System.out.println("path="+cookie.getPath());
			System.out.println("secure="+cookie.getSecure());
			System.out.println("----------------------------------");
		}
		
		HttpSession session = req.getSession();
		String sessionId = session.getId();
		
		//设置cookie
		
		//domain="localhost";path="/",可以回传到服务端
		CookieUtils.addCookie("test", "123", 12,"/","localhost",resp);   //设置的cookie 12s后过期
		//domain="localhost";path="/cookie_demo/a",因路径不能匹配， 不可回传到服务端
		CookieUtils.addCookie("test", "a", -1,"/cookie_demo/a","localhost",resp); 
		//domain="localhost";path="/cookie_demo/test",可以回传到服务端
		CookieUtils.addCookie("test", "test", -1,"/cookie_demo/test","localhost",resp); 
		//其他域，cookie可以传给浏览器，但浏览器会直接忽略掉
		CookieUtils.addCookie("test2", "1234", 12, "/", "www.baidu.com", false, resp);
		
		//domain="127.0.0.1";path="/",因域不同，浏览器直接忽略掉
	    CookieUtils.addCookie("test", "127.0.0.1", -1,"/","127.0.0.1",resp);
		
		resp.getWriter().write("sessionId="+sessionId);
		resp.getWriter().flush();
	}

}
