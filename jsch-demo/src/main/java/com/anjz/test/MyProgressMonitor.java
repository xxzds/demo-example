package com.anjz.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.SftpProgressMonitor;

/**
 * 监控传输进度(简单实现)
 * @author shuai.ding
 *
 * @date 2016年12月2日下午4:38:12
 */
public class MyProgressMonitor implements SftpProgressMonitor{
	
	private static final Logger logger=LoggerFactory.getLogger(MyProgressMonitor.class);
	
	 private long transfered;

	@Override
	public void init(int op, String src, String dest, long max) {
		logger.debug("Transferring begin.");		
	}

	@Override
	public boolean count(long count) {
		transfered = transfered + count;
		logger.debug("Currently transferred total size: " + transfered + " bytes");
        return true;
	}

	@Override
	public void end() {
		logger.debug("Transferring done.");
	}

}
