package com.xxl.job.admin.dao.impl;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.dao.IXxlJobInfoDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * job info
 * @author xuxueli 2016-1-12 18:03:45
 */
@Repository
public class XxlJobInfoDaoImpl implements IXxlJobInfoDao {
	
	@Resource
	public SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<XxlJobInfo> pageList(int offset, int pagesize, int jobGroup, String executorHandler) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("jobGroup", jobGroup);
		params.put("executorHandler", executorHandler);
		
		return sqlSessionTemplate.selectList("XxlJobInfoMapper.pageList", params);
	}

	@Override
	public int pageListCount(int offset, int pagesize, int jobGroup, String executorHandler) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("jobGroup", jobGroup);
		params.put("executorHandler", executorHandler);
		
		return sqlSessionTemplate.selectOne("XxlJobInfoMapper.pageListCount", params);
	}

	@Override
	public int save(XxlJobInfo info) {
		return sqlSessionTemplate.insert("XxlJobInfoMapper.save", info);
	}

	@Override
	public XxlJobInfo load(int jobGroup, String jobName) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("jobGroup", jobGroup);
		params.put("jobName", jobName);
		
		return sqlSessionTemplate.selectOne("XxlJobInfoMapper.load", params);
	}

	@Override
	public int update(XxlJobInfo item) {
		return sqlSessionTemplate.update("XxlJobInfoMapper.update", item);
	}

	@Override
	public int delete(int jobGroup, String jobName) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("jobGroup", jobGroup);
		params.put("jobName", jobName);
		
		return sqlSessionTemplate.update("XxlJobInfoMapper.delete", params);
	}

	@Override
	public List<XxlJobLog> getJobsByGroup(String jobGroup) {
		return sqlSessionTemplate.selectList("XxlJobInfoMapper.getJobsByGroup", jobGroup);
	}

}
