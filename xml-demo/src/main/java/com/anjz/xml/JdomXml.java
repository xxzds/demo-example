package com.anjz.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.io.FileUtils;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * JDOM
 * @author shuai.ding
 *
 * @date 2017年9月13日下午5:44:49
 */
public class JdomXml {
	private static final Logger LOGGER = LoggerFactory.getLogger(Dom4jXml.class);

	public static void main(String[] args) throws IOException, JDOMException {
		//字符串转XML
		String pathStr = DomXml.class.getClassLoader().getResource("data1.txt").getPath();
		String xmlStr = FileUtils.readFileToString(new File(pathStr), "utf-8");
		LOGGER.info(xmlStr);
		StringReader sr = new StringReader(xmlStr);
		InputSource is = new InputSource(sr);
		Document doc = (new SAXBuilder()).build(is);
		LOGGER.info("--------------------------------------------------------------");
		//XML转字符串
		Format format = Format.getPrettyFormat();
		format.setEncoding("utf-8");//设置xml文件的字符为utf-8，解决中文问题
		XMLOutputter xmlout = new XMLOutputter(format);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		xmlout.output(doc,bo);
		String xmlStr2 = bo.toString();
		LOGGER.info(xmlStr2);
	}
}
