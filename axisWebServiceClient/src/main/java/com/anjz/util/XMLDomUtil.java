package com.anjz.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.anjz.model.soap.common.AccessoryContentsBaseVO;
import com.anjz.model.soap.common.AccessoryContentsErrorVO;
import com.anjz.model.soap.common.PageContentsBaseVO;
import com.anjz.model.soap.common.PageInfo;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.org.apache.xpath.internal.XPathAPI;

@SuppressWarnings("rawtypes")
public class XMLDomUtil {

	public static void getStringByList(StringBuffer sb, List list)
			throws Exception {
		if (list != null && list.size() != 0) {
			for (Object o : list) {
				sb.append("<");
				sb.append("Transaction");
				sb.append(">");
				getStringProperty1(sb, o);
				sb.append("</");
				sb.append("Transaction");
				sb.append(">");
			}
		}
	}

	private static void getStringProperty1(StringBuffer sb, Object vo)
			throws Exception {
		for (Field field : vo.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object obj = field.get(vo);
			if (obj != null) {
				sb.append("<");
				sb.append(field.getName());
				sb.append(">");
				if ("java.util.List".equals(field.getType().getName())) {
					getStringByList(sb, (List) obj);
				} else if ("java.lang.String".equals(field.getType().getName())
						|| "java.lang.Integer"
								.equals(field.getType().getName())
						|| "int".equals(field.getType().getName())) {
					sb.append(obj);
				} else {
					getStringProperty1(sb, obj);
				}
				sb.append("</");
				sb.append(field.getName());
				sb.append(">");
			}
		}
	}

	/**
	 * XML转换成PageInfo对象
	 * @param outputXML xml结果集
	 * @param PageContents 分页实体
	 * @param AccessoryContents 汇总信息
	 * @return PageInfo pageInfo 对象
	 */
	public static PageInfo getResultFromXml(String outputXML,PageContentsBaseVO PageContents,AccessoryContentsBaseVO AccessoryContents) throws Exception {
		PageInfo pageInfo = new PageInfo();
		Document doc = getDOMByString(outputXML);
		NodeList list = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if ("resultCode".equalsIgnoreCase(list.item(i).getNodeName())) {
				pageInfo.setResultCode(Integer.parseInt(list.item(i).getTextContent()));
				if (pageInfo.getResultCode() != 0) {
					AccessoryContentsErrorVO error = new AccessoryContentsErrorVO();
					for (int j = i + 1; j < list.getLength(); j++) {
						if ("accessoryContents".equalsIgnoreCase(list.item(j).getNodeName())) {
							error.setErrorInfo(list.item(j).getTextContent());
							break;
						}
					}
					pageInfo.setAccessoryContents(error);
					break;
				}
			} else if ("pageContents".equalsIgnoreCase(list.item(i).getNodeName())) {
				if (!StringUtil.isEmpty(list.item(i).getTextContent())&& PageContents != null) {
					NodeList subList = list.item(i).getChildNodes();
					List<PageContentsBaseVO> pageContentsList = new ArrayList<PageContentsBaseVO>();
					for (int j = 0; j < subList.getLength(); j++) {
						NodeList nodeList = subList.item(j).getChildNodes();
						Map<String, String> map = new HashMap<String, String>();
						for (int k = 0; k < nodeList.getLength(); k++) {
							map.put(nodeList.item(k).getNodeName().toUpperCase(), nodeList.item(k).getTextContent());
						}
						for (Field field : PageContents.getClass().getDeclaredFields()) {
							field.setAccessible(true);
							field.set(PageContents,map.get(field.getName().toUpperCase()));
						}
						pageContentsList.add(PageContents);
						PageContents = PageContents.getClass().newInstance();//反射创建新对象
					}
					pageInfo.setPageContents(pageContentsList);
				}
			} else if ("accessoryContents".equalsIgnoreCase(list.item(i).getNodeName())) {
				NodeList subList = list.item(i).getChildNodes();
				for (int j = 0; j < subList.getLength(); j++) {
					if (AccessoryContents == null) {
						AccessoryContents = new AccessoryContentsErrorVO();
					}
					String methodStr = subList.item(j).getNodeName();
					Method method = AccessoryContents.getClass().getMethod("set" + firstAlpUpCase(methodStr), String.class);
					method.invoke(AccessoryContents, subList.item(j).getTextContent());
				}
				pageInfo.setAccessoryContents(AccessoryContents);
			} else if ("currentPage".equalsIgnoreCase(list.item(i).getNodeName())) {
				pageInfo.setCurrentPage(Integer.parseInt(list.item(i).getTextContent()));
			} else if ("rowCount".equalsIgnoreCase(list.item(i).getNodeName())) {
				pageInfo.setRowCount(Integer.parseInt(list.item(i).getTextContent()));
			} else if ("pageSize".equalsIgnoreCase(list.item(i).getNodeName())) {
				pageInfo.setPageSize(Integer.parseInt(list.item(i).getTextContent()));
			}
		}
		//设置pageContents为非空
		List<PageContentsBaseVO> pageContents=pageInfo.getPageContents();
		if(pageContents == null){
			pageInfo.setPageContents(new ArrayList<PageContentsBaseVO>());
		}
		return pageInfo;
	}
	
	public static String firstAlpUpCase(String nodeName) {
		String s = nodeName.substring(0, 1);
		Pattern p = Pattern.compile("[a-z]");
		Matcher m = p.matcher(s);
		if (m.matches()) {
			char[] cs = nodeName.toCharArray();
			cs[0] -= 32;
			return String.valueOf(cs);
		}
		return nodeName;
	}

	private static DocumentBuilder getDocumentBuilder()
			throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		return dbf.newDocumentBuilder();
	}

	public static Document getDocument(InputSource inputSource)
			throws SAXException, IOException, ParserConfigurationException {
		return getDocumentBuilder().parse(inputSource);
	}

	public static Document getDOMByString(String text) throws SAXException,
			IOException, ParserConfigurationException {
		InputSource in = new InputSource(new StringReader(text));
		return getDocument(in);
	}

	public static Node string2Node(String xmlstr) throws SAXException,
			IOException, ParserConfigurationException {
		Document doc = string2Dom(xmlstr);
		return doc.getDocumentElement();
	}

	public static String dom2String(Document doc) throws IOException {
		doc.normalize();
		StringWriter wr = new StringWriter();
		OutputFormat of = new OutputFormat();
		of.setOmitXMLDeclaration(true);
		of.setIndenting(false);
		of.setPreserveSpace(true);
		XMLSerializer ser = new XMLSerializer(of);
		ser.setOutputCharStream(wr);
		ser.serialize(doc);
		return wr.toString();
	}

	public static Document string2Dom(String xml) throws SAXException,
			IOException, ParserConfigurationException {
		return getDOMByString(xml);
	}

	public static NodeList getNodes(Node doc, String xpath)
			throws TransformerException {
		return XPathAPI.selectNodeList(doc, xpath);
	}

	public static Node getSingleNode(Node doc, String xpath)
			throws TransformerException {
		return XPathAPI.selectSingleNode(doc, xpath);
	}

	public static Node createElement(Document doc, String name, String value) {
		Node element = doc.createElement(name);
		if (value != null)
			setNodeValue(element, value, true);
		return element;
	}

	public static void setNodeValue(Node node, String value)
			throws TransformerException {
		if (node == null) {
			return;
		}
		Node textNode = getSingleNode(node, "./text()");
		if (textNode != null) {
			textNode.setNodeValue(value);
		} else {
			textNode = node.getOwnerDocument().createTextNode(value);
			node.appendChild(textNode);
		}
	}

	public static void setNodeValue(Node node, String value, boolean replace) {
		if (node == null) {
			return;
		}

		Node textNode = node.getOwnerDocument().createTextNode(value);
		node.appendChild(textNode);
	}

	/**Object转XML*/
	public static String getStringByObject(Object vo,String messageId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		sb.append("<RequestContent>");
		sb.append("<messageId>");
		sb.append(messageId);
		sb.append("</messageId>");
		getStringProperty1(sb, vo);
		sb.append("</RequestContent>");
		return sb.toString();
	}

	/**PageInfo转XML*/
	public static String getStringByPageInfo(Object vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		sb.append("<PageInfo>");
		getStringProperty1(sb, vo);
		sb.append("</PageInfo>");
		return sb.toString();
	}
	
	/**XML转Object*/
	@SuppressWarnings("unchecked")
	public static <T> T getObjectByString(String inputXML,Object object) throws Exception {
		Document doc = getDOMByString(inputXML);
		NodeList list = doc.getFirstChild().getChildNodes();//获取第一个节点下面的所以子节点
		
		Map<String, String> map = new HashMap<String, String>();
		for (int k = 0; k < list.getLength(); k++) {
			map.put(list.item(k).getNodeName().toUpperCase(), list.item(k).getTextContent());
		}
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			field.set(object,map.get(field.getName().toUpperCase()));
		}
		return (T)object;
	}

}
