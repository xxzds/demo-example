package com.anjz.test.simpleDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试
 * @author ding.shuai
 * @date 2017年6月10日下午1:48:17
 */
public class SimpleDateFormateTest extends Thread{
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String name;
	private String dateStr;
	
	public SimpleDateFormateTest(String name,String dateStr) {
		this.name = name;
		this.dateStr = dateStr;
	}

	@Override
	public void run() {
		
		try {
			Date date = sdf.parse(dateStr);
			System.out.println(name+": date:"+date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		executorService.execute(new SimpleDateFormateTest("A", "2017-06-10"));
		executorService.execute(new SimpleDateFormateTest("B", "2016-06-06"));
		executorService.shutdown();
	}
}
