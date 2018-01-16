package com.anjz.test;

import java.io.UnsupportedEncodingException;

public class CodingTest3 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(EncodeUtil.stringToUnicode("木999，你好，不错,~!@#$"));
		System.out.println(EncodeUtil.unicodeToString("\\u6728\\u0039\\u0039\\u0039\\uff0c\\u4f60\\u597d\\uff0c\\u4e0d\\u9519\\u002c\\u007e\\u0021\\u0040\\u0023\\u0024"));
		
		System.out.println(EncodeUtil.convertStringToUTF8("你好"));
		System.out.println(EncodeUtil.convertUTF8ToString("E4BDA0E5A5BD"));
		
		System.out.println(EncodeUtil.unicodeToUTF8("\\u6728\\u0039\\u0039\\u0039\\uff0c\\u4f60\\u597d\\uff0c\\u4e0d\\u9519\\u002c\\u007e\\u0021\\u0040\\u0023\\u0024"));
		
		System.out.println(EncodeUtil.utf8ToUnicode("E69CA8393939EFBC8CE4BDA0E5A5BDEFBC8CE4B88DE994992C7E21402324"));
	}
}