/*package com.anjz.itext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

*//**
 * http://blog.csdn.net/u012377333/article/details/51264122
 * @author dingshuai
 *
 *//*
public class Test1 {

	@Test
	public void fillTemplate() throws IOException, DocumentException {
		PdfReader reader = new PdfReader("C:/Users/Administrator/Desktop/a.pdf"); // 模版文件目录
		PdfStamper ps = new PdfStamper(reader, new FileOutputStream("C:/Users/Administrator/Desktop/protocol.pdf")); // 生成的输出流
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// PdfStamper ps = new PdfStamper(reader, bos);

		 使用中文字体 
		BaseFont bf = BaseFont.createFont(Test1.class.getResource("") + "simsun.ttc,1",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
		fontList.add(bf);

		AcroFields s = ps.getAcroFields();
		s.setSubstitutionFonts(fontList);

		Map<String, Item> fieldMap = s.getFields(); // pdf表单相关信息展示
		for (Map.Entry<String, Item> entry : fieldMap.entrySet()) {
			String name = entry.getKey(); // name就是pdf模版中各个文本域的名字
			Item item = entry.getValue();
			System.out.println("[name]:" + name + ", [value]: " + item);
		}

//		s.setField("abc",
//				"as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司as该多好公司");
//		s.setField("name", "123456asdzxc");
		// s.setField("CONTACT", "我是联系人123");
		
		s.setField("userNameAndIdentity", "丁帅(341224199105103332)");
		s.setField("account", "(二)您设定易借条账户与您现有的慧易通账户【"+"你好1234567、test45454545、悲哀456789876"+"】绑定，自动代扣，由此产生的欠还款项，您须在到期还款日之前完成还款。");

		ps.setFormFlattening(true); // 这句不能少
		ps.close();
		reader.close();
	}
}
*/