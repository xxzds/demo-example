package com.anjz.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayTest {
	public static void main(String[] args) throws IOException {
		String str = "你";
		ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes("utf-8"));
		
		byte[] bytes = new byte[str.getBytes("utf-8").length];
		bis.read(bytes);
		for(byte b :bytes){
			System.out.print((b &0xff)+",");
		}
	}
}
