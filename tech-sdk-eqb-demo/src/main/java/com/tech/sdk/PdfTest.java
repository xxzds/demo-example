package com.tech.sdk;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.junit.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.tech.service.pdf.PdfExportService;
import com.tech.util.HttpUrlConnectionUtil;
import com.tech.util.PropertiesUtil;
import com.tech.util.UUIDUtil;
import com.tech.util.sftp.SFTPChannel;
import com.tech.util.sftp.SftpUtil;

/**
 * 
 * @author shuai.ding
 * @date 2017年3月15日下午3:12:43
 */
public class PdfTest extends BaseTest{
	
	@Resource
	private PdfExportService pdfExportService;

	
	@Test
	public void exportProtocolPdf() throws SftpException{		
		SFTPChannel sftpChannel = new SFTPChannel();
		PropertiesUtil propertiesUtil = PropertiesUtil.getInstance("config.properties");
		String host = propertiesUtil.getValue("sftp.host");
		String port = propertiesUtil.getValue("sftp.port");
		String username = propertiesUtil.getValue("sftp.username");
		String password = propertiesUtil.getValue("sftp.password");

		ChannelSftp channelSftp = sftpChannel.getChannel(host, Integer.valueOf(port), username, password);
		
		String distDir=propertiesUtil.getValue("sftp.directory")+"attach/";
		SftpUtil.createDir(distDir,channelSftp);
		String fileName = UUIDUtil.generateUuid32()+".pdf";
		OutputStream out = channelSftp.put(distDir+fileName, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
		pdfExportService.exportProtocolPdf(out);
		
		try {
			 HttpUrlConnectionUtil.doGet(propertiesUtil.getValue("sftp.httpBaseUrl")+"attach/"+fileName, null, 5000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
