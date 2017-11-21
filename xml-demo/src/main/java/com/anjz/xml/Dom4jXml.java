package com.anjz.xml;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * dom4j
 * @author shuai.ding
 * @date 2017年9月13日下午3:30:44
 */
public class Dom4jXml {
	private static final Logger LOGGER = LoggerFactory.getLogger(Dom4jXml.class);
	
	public static void main(String[] args) throws IOException, DocumentException {
		// 字符串转XML
		String pathStr = DomXml.class.getClassLoader().getResource("data1.txt").getPath();
		String xmlStr = FileUtils.readFileToString(new File(pathStr), "utf-8");
		LOGGER.info(xmlStr);
		Document document = DocumentHelper.parseText(xmlStr);
		LOGGER.info("--------------------------------------------------------------");
		// XML转字符串 
		String text = document.asXML();
		LOGGER.info(text);
	}
}
