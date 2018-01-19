package com.anjz.test;
import java.io.UnsupportedEncodingException;

public class CodingTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "你好";
		
		byte[] gbkBytes = str.getBytes("gbk");
		for(byte gbkByte:gbkBytes){
			//字节对应的十进制是负数，因java中的二进制使用补码表示的，此处使用0xff 还原成int表示的数据，再转化成16进制
			System.out.print(Integer.toHexString((gbkByte & 0xFF)) +",");
		}
		System.out.println();
		String gbk2utfStr = new String(str.getBytes("gbk"),"utf-8");
		System.out.println("gbk转化成utf-8:"+gbk2utfStr);
		
		byte[] utfBytes = gbk2utfStr.getBytes("utf-8");
		for(byte utfByte:utfBytes){
			System.out.print(Integer.toHexString((utfByte & 0xFF))+",");
		}
		
		System.out.println();
		String utf2gbkStr = new String(gbk2utfStr.getBytes("utf-8"),"gbk");
		System.out.println("utf-8转化成gbk:"+utf2gbkStr);
	}
}
