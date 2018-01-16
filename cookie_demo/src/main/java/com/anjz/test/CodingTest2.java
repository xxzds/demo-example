package com.anjz.test;

import java.io.UnsupportedEncodingException;

public class CodingTest2 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str="a";
		
		byte[] utfBytes =  str.getBytes("utf-8");
		for(byte utfByte:utfBytes){
			System.out.print(Integer.toHexString((utfByte & 0xFF)) +",");
		}
		
		System.out.println();
		byte[] gbkBytes = str.getBytes("gbk");
		for(byte gbkByte:gbkBytes){
			System.out.println(Integer.toHexString((gbkByte & 0xFF)) +",");
		}
	}

}
