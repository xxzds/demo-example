package com.anjz.test;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class EncodeUtil {

	/**
	 * 获取字符串的unicode编码
	 * 
	 * \ufeff控制字符 用来表示「字节次序标记（Byte Order Mark）」不占用宽度 unicode码中一个字符占用2个字节
	 * @param s
	 * @return
	 */
	public static String stringToUnicode(String s) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}

		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");

			for (int i = 0; i < bytes.length - 1; i += 2) {
				out.append("\\u");

				// 将字节码转化成十六进制(& oxff 是进行补码操作)
				String str = Integer.toHexString(bytes[i] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				out.append(str);
				String str1 = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str1.length(); j < 2; j++) {
					out.append("0");
				}
				out.append(str1);
			}

			out.delete(0, "\\ufeff".length());
			return out.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * unicode码转化成字符串
	 * @param str
	 * @return
	 */
	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			String group = matcher.group(2);
			ch = (char) Integer.parseInt(group, 16);
			String group1 = matcher.group(1);
			str = str.replace(group1, ch + "");
		}
		return str;
	}

	/**
	 * 字符串转化成对应的utf8编码
	 * @param s
	 * @return 16进制的数据流
	 */
	public static String convertStringToUTF8(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			char c;
			for (int i = 0; i < s.length(); i++) {
				c = s.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(Integer.toHexString(c).toUpperCase());
				} else {
					byte[] b;
					b = Character.toString(c).getBytes("utf-8");
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						// 转换为unsigned integer 无符号integer
						/*
						 * if (k < 0) k += 256;
						 */
						k = k < 0 ? k + 256 : k;
						// 返回整数参数的字符串表示形式 作为十六进制（base16）中的无符号整数
						// 该值以十六进制（base16）转换为ASCII数字的字符串
						sb.append(Integer.toHexString(k).toUpperCase());

						// url转置形式
						// sb.append("%" +Integer.toHexString(k).toUpperCase());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * UTF-8编码 转换为对应的 字符串
	 * 实现方式：将16进制数转化成有符号的十进制数
	 * @param s
	 * @return
	 */
	public static String convertUTF8ToString(String s) {  
	    if (s == null || s.equals("")) {  
	        return null;  
	    }  
	    try {  
	        s = s.toUpperCase();  
	        int total = s.length() / 2;  
	        //标识字节长度  
	        int pos = 0;  
	        byte[] buffer = new byte[total];  
	        for (int i = 0; i < total; i++) {  
	            int start = i * 2;  
	            //将字符串参数解析为第二个参数指定的基数中的有符号整数。  
	            buffer[i] = (byte) Integer.parseInt(s.substring(start, start + 2), 16);  
	            pos++;  
	        }  
	        //通过使用指定的字符集解码指定的字节子阵列来构造一个新的字符串。  
	        //新字符串的长度是字符集的函数，因此可能不等于子数组的长度。  
	        return new String(buffer, 0, pos, "UTF-8");  
	    } catch (UnsupportedEncodingException e) {  
	        e.printStackTrace();  
	    }  
	    return s;  
	}  
	
	/**
	 * unicode码转化成utf8码
	 * unicode码 -> 字符串 -> utf8码
	 * @param str
	 * @return
	 */
	public static String unicodeToUTF8(String str){
		return EncodeUtil.convertStringToUTF8(EncodeUtil.unicodeToString(str));  
	}
	
	/**
	 * utf8码转化成unicode码
	 * utf8码 -> 字符串 -> unicode码
	 * @param str
	 * @return
	 */
	public static String utf8ToUnicode(String str){
		return EncodeUtil.stringToUnicode(EncodeUtil.convertUTF8ToString(str));  
	}

}
