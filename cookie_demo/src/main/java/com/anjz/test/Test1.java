package com.anjz.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

public class Test1 {
	public static void main(String[] args) throws IOException {
		
		/***********************************字节流*******************************************/
//		String str = "你好";
//		ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes("utf-8"));
//		
//		int temp = 0;
//		while((temp = bis.read())!=-1){
//			System.out.print(temp+",");
//		}
		
		//java展示的字节使用补码的形式表示的。在原码的基础上进行了补码操作
//		System.out.println();
//		byte[] bytes = new byte[str.getBytes("utf-8").length];
//		bis.read(bytes);
//		for(byte b :bytes){
//			System.out.print(b+",");
//		}
		
		
		/***********************************文件流*******************************************/
//		File f = new File("C:\\Users\\Administrator\\Desktop\\a");
//	    FileOutputStream fos = new FileOutputStream(f);
//	    
//	    //此处的int类型的取值范围[0,255]，取值范围是8位字节的范围，如果大于这个范围，将进行截断
//	    fos.write(0);
//	    
//	    FileInputStream fis = new FileInputStream(f);
//	    int a = fis.read();
//	    System.out.println(a);
	    
//	    byte[] b = new byte[1];
//	    fis.read(b, 0, 1);
//	    System.out.println(b[0]);
		
		
		
		
		
		 //获取系统默认编码    
//        System.out.println(System.getProperty("file.encoding"));
        
        
//        StringReader sr = new StringReader("128");
//        int temp1 = 0;
//		while((temp1 = sr.read())!=-1){
//			System.out.println(temp1);
//		}
	    
	    
	    byte bx = -128;
	    System.out.println(bx);
	}

}