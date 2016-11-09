package com.xxl.job.executor.service.jobhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@JobHander(value="myJobHandler")
@Service
public class MyJobHandler extends IJobHandler{
	private static transient Logger logger = LoggerFactory.getLogger(MyJobHandler.class);
	
	@Override
	public void execute(String... params) throws Exception {
		logger.info("test myjobHandle 执行器的使用方法!");		
	}

}
