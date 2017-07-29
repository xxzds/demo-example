package com.anjz.service.impl;

import com.anjz.service.intf.TestService;

/**
 * @author ding.shuai
 * @date 2017年7月29日下午5:20:11
 */
public class TestServiceImpl implements TestService{

	@Override
	public String test() {
		return "test";
	}

}
