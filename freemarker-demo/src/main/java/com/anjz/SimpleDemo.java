package com.anjz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 简单例子
 * @author shuai.ding
 * @date 2017年9月8日上午11:32:14
 */
public class SimpleDemo {
	public static void main(String[] args) throws IOException, TemplateException {
		
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setDefaultEncoding("utf-8");
		
		String path = SimpleDemo.class.getClassLoader().getResource("template").getPath();
		
		TemplateLoader templateLoader = new FileTemplateLoader(new File(path));
		configuration.setTemplateLoader(templateLoader);
		Template template = configuration.getTemplate("simple.ftl");
		System.out.println(template);
		
		Map<String, String> root = new HashMap<String, String>();
		root.put("message", "test");
		root.put("name", "测试");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream("C:/Users/Administrator/Desktop/new.ftl"), "UTF-8"));  
		template.process(root, out);
	}

}
