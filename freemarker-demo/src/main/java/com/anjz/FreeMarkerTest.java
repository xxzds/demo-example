package com.anjz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerTest.class);
	

	@Test
	public void test1(){
		try{
			parseTeplete("simple2.ftl");
		}catch(Exception e){
			LOGGER.error("exception",e);
		}
		
	}
	
	/**
	 * 解析模板
	 * @author shuai.ding
	 * @param name          文件名称
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void parseTeplete(String name) throws IOException, TemplateException{
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setDefaultEncoding("utf-8");
		
		String path = this.getClass().getClassLoader().getResource("template").getPath();
		
		TemplateLoader templateLoader = new FileTemplateLoader(new File(path));
		configuration.setTemplateLoader(templateLoader);
		Template template = configuration.getTemplate(name);
		System.out.println(template);
		
		Map<String, String> root = new HashMap<String, String>();
		root.put("message", "test");
		root.put("name", "测试");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream("C:/Users/Administrator/Desktop/new.ftl"), "UTF-8"));  
		template.process(root, out);
	}
}
