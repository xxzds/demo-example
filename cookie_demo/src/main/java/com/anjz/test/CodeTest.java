package com.anjz.test;

import java.io.UnsupportedEncodingException;

public class CodeTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "你好，世界！hello,world!";
//		String str = "abc";
		System.out.println("内容："+str);
		
		byte[] utf8Bytes = str.getBytes("utf-8");
		
		System.out.println("utf8编码，长度"+utf8Bytes.length);  // (3*6= 18byte)  utf-8 中文，一个字符用三个字节存储
		
		byte[] gbkBytes = str.getBytes("gbk");
		System.out.println("gbk编码，长度"+gbkBytes.length);  //(2*6 = 12byte)  gbk中文，一个字符用两个字节存储
		
		//字符
//		char[] chars = str.toCharArray();
//		for(char temp:chars){
//			System.out.print(temp);
//		}
		
		
//		String utf2gbkStr = new String(str.getBytes("utf-8"),"gbk");
//		System.out.println(utf2gbkStr);
//		System.out.println(utf2gbkStr.length());
//		
//		String gbk2utfStr =  new String(utf2gbkStr.getBytes("gbk"),"utf-8");
//		System.out.println(gbk2utfStr);
		
		String gbk2utfStr = new String(str.getBytes("gbk"),"utf-8");
		System.out.println(gbk2utfStr);
		System.out.println(gbk2utfStr.length());
		
		String utf2gbkStr = new String(gbk2utfStr.getBytes("utf-8"), "gbk");
		System.out.println(utf2gbkStr);
		
	}

}
