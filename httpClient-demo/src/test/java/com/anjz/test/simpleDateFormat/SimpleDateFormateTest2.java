package com.anjz.test.simpleDateFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试
 * @author ding.shuai
 * @date 2017年6月10日下午12:14:19
 */
public class SimpleDateFormateTest2 {

	public static void main(String[] args) {
		final DateFormat df = new SimpleDateFormat("yyyyMMdd,HHmmss");
		ExecutorService ts = Executors.newFixedThreadPool(100);
		for (;;) {
		    ts.execute(new Runnable() {		 
		        @Override
		        public void run() {
		            try {
		              //生成随机数，格式化日期
		              String format =  df.format(new Date(Math.abs(new Random().nextLong())));
		              System.out.println(format);
		            } catch (Exception e) {
		                e.printStackTrace();
		                System.exit(1);
		            }
		        }
		    });
		}
		
//		final ThreadLocal<DateFormat> tl = new ThreadLocal<DateFormat>(){
//	        @Override
//	        protected DateFormat initialValue() {
//	            return new SimpleDateFormat("yyyyMMdd,HHmmss");
//	        }
//	         
//	    };
//	    ExecutorService ts = Executors.newFixedThreadPool(100);
//	    for (;;) {
//	        ts.execute(new Runnable() {
//	 
//	            @Override
//	            public void run() {
//	                try {
//	                	String format =  tl.get().format(new Date(Math.abs(new Random().nextLong())));
//	                	System.out.println(format);
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                    System.exit(1);
//	                }
//	            }
//	        });
//	    }
		
		
	}
	
	
	
}
