package com.anjz.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 使用最原始的javax.xml.parsers，标准的jdk api
 * DOM方式
 * @author shuai.ding
 * @date 2017年9月13日下午2:54:19
 */
public class DomXml {
	private static final Logger LOGGER = LoggerFactory.getLogger(DomXml.class);
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		//字符串转xml
		String pathStr = DomXml.class.getClassLoader().getResource("data1.txt").getPath();
		String xmlStr = FileUtils.readFileToString(new File(pathStr), "utf-8");
		LOGGER.info(xmlStr);
		StringReader sr = new StringReader(xmlStr);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document doc = builder.parse(is);
		
		LOGGER.info("--------------------------------------------------------------");
		//xml转字符串
		TransformerFactory  tf  =  TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.setOutputProperty("encoding","UTF-8");//解决中文问题
		ByteArrayOutputStream  bos  =  new  ByteArrayOutputStream();
		t.transform(new DOMSource(doc), new StreamResult(bos));
		String xmlStr2 = bos.toString();
		LOGGER.info(xmlStr2);
	}

}
