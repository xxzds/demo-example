package com.anjz.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anjz.service.intf.TestService;

public class Consumer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {  
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
                new String[] { "spring-dubbo.xml" });  
        context.start();  
  
        TestService testService = (TestService) context.getBean("testService");  
        String content = testService.test();
        System.out.println(content);
        System.in.read();
    }  
}
