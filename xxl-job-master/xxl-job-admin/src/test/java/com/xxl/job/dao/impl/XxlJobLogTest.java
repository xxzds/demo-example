package com.xxl.job.dao.impl;

import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.dao.IXxlJobLogDao;
import com.xxl.job.core.router.model.ResponseModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationcontext-*.xml")
public class XxlJobLogTest {
	
	@Resource
	private IXxlJobLogDao xxlJobLogDao;
	
	@Test
	public void save_load(){
		XxlJobLog xxlJobLog = new XxlJobLog();
		xxlJobLog.setJobName("job_name");
		int count = xxlJobLogDao.save(xxlJobLog);
		System.out.println(count);
		System.out.println(xxlJobLog.getId());
		
		XxlJobLog item = xxlJobLogDao.load(xxlJobLog.getId());
		System.out.println(item);
	}
	
	@Test
	public void updateTriggerInfo(){
		XxlJobLog xxlJobLog = xxlJobLogDao.load(29);
		xxlJobLog.setTriggerTime(new Date());
		xxlJobLog.setTriggerStatus(ResponseModel.SUCCESS);
		xxlJobLog.setTriggerMsg("trigger msg");
		xxlJobLogDao.updateTriggerInfo(xxlJobLog);
	}
	
	@Test
	public void updateHandleInfo(){
		XxlJobLog xxlJobLog = xxlJobLogDao.load(29);
		xxlJobLog.setHandleTime(new Date());
		xxlJobLog.setHandleStatus(ResponseModel.SUCCESS);
		xxlJobLog.setHandleMsg("handle msg");
		xxlJobLogDao.updateHandleInfo(xxlJobLog);
	}
	
	@Test
	public void pageList(){
		List<XxlJobLog> list = xxlJobLogDao.pageList(0, 20, 0, null, null, null);
		int list_count = xxlJobLogDao.pageListCount(0, 20, 0, null, null, null);
		
		System.out.println(list);
		System.out.println(list_count);
	}
	
}
