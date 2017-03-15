package com.tech.util;


import java.util.UUID;

/**
 * 
 * @author shuai.ding
 * @date 2017年2月3日下午3:42:38
 */
public class UUIDUtil {
	/**
	 * 36位
	 * @return
	 */
    public static String generateUuid36() {
    	//随机产生一个[0,10)的数字
    	int random=(int)(Math.random()*10);
        return UUID.randomUUID().toString().replaceAll("-", String.valueOf(random));
    }
    
    /**
     * 32位
     * @return
     */
    public static String  generateUuid32(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public static void main(String[] args) {
		System.out.println(UUIDUtil.generateUuid32());
	}
}
