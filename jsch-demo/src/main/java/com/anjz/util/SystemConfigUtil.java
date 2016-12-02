package com.anjz.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author shuai.ding
 *
 * @date 2016年12月2日上午11:36:16
 */
public class SystemConfigUtil {
	public static final Properties properties=new Properties();
	
	/**
	 * 文件服务器参数配置
	 */
	public static final String SFTP_host;
	public static final String SFTP_port;
	public static final String SFTP_username;
	public static final String SFTP_password;
	public static final String SFTP_directory;
	public static final String SFTP_httpBaseUrl;
	
	static{
		try{
			properties.load(SystemConfigUtil.class.getClassLoader().getResourceAsStream("system-config.properties"));
			
			//文件上传服务器参数
			SFTP_host = properties.getProperty("SFTP_host");
			SFTP_port=properties.getProperty("SFTP_port");
			SFTP_username = properties.getProperty("SFTP_username");
			SFTP_password = properties.getProperty("SFTP_password");
			SFTP_directory= properties.getProperty("SFTP_directory");
			SFTP_httpBaseUrl= properties.getProperty("SFTP_httpBaseUrl");
			
		}catch(IOException e){
			throw new ExceptionInInitializerError("加载系统配置信息出错！");
		}
	}
	
	public static void main(String[] args) {
		System.out.println(SystemConfigUtil.SFTP_host);	
		System.out.println(SystemConfigUtil.SFTP_port);	
		System.out.println(SystemConfigUtil.SFTP_username);	
		System.out.println(SystemConfigUtil.SFTP_password);	
		System.out.println(SystemConfigUtil.SFTP_directory);	
		System.out.println(SystemConfigUtil.SFTP_httpBaseUrl);	
	}
}
