package com.xxl.job.core.handler;

import com.xxl.job.core.router.HandlerRouter;

/**
 * remote job handler
 * @author xuxueli 2015-12-19 19:06:38
 */
public abstract class IJobHandler extends HandlerRouter {
	
	/**
	 * job handler <br><br>
	 * the return Object will be and stored
	 * @param params
	 * @throws Exception  default sussecc, fail if catch exception
	 */
	public abstract void execute(String... params) throws Exception;
	
}
