package com.anjz.itext.pdf;

import java.io.UnsupportedEncodingException;

public class StrHelp {

    public static String getChinese(String s) {
        try {
            return new String(s.getBytes("utf-8"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
}
