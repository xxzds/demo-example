package com.anjz.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamTest {
	public static void main(String[] args) throws IOException {
		fileStream(1000);
	}
	
	public static void fileStream(int content) throws IOException{
		File f = new File("C:\\Users\\Administrator\\Desktop\\a");
	    FileOutputStream fos = new FileOutputStream(f);
	    
	    fos.write(content);
	    
	    FileInputStream fis = new FileInputStream(f);
	    byte[] b = new byte[1];
	    fis.read(b, 0, 1);
	    System.out.println(b[0]);
	}
}
