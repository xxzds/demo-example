package com.tech.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

/**
 * jdk自带，http请求封装
 * @author ding.shuai
 * @date 2016年7月20日下午1:36:48
 */
public class HttpUrlConnectionUtil {
	
	private static final  Logger logger = LoggerFactory.getLogger(HttpUrlConnectionUtil.class);

    public static final String Y = "1";
    public static final String N = "0";

    public static final String TRUE = "1";
    public static final String FALSE = "0";

    public static final String PATH_SIGN = "/";
    public static final String PAIR_SIGN = ";";
    public static final String KEY_VALUE_SIGN = ":";
    public static final String VALUE_NOOP = "xx";
    public static final String UTF8 = "UTF-8";
    public static final String LINE_SEPARATOR = "\r\n";

    public static class HTTP_CONTENT_TYPE {
        public static final String HEAD_KEY     = "Content-type";
        public static final String JSON         = "application/json";
    }

    public static class HTTP_METHOD {
        public static final String POST         = "POST";
        public static final String GET          = "GET";
    }
	
    private static Logger LOG = LoggerFactory.getLogger(HttpUrlConnectionUtil.class);

	public static String doPost(String urlStr, String param, int timeout) throws SocketTimeoutException, MalformedURLException, IOException {
		HttpURLConnection conn = null;
		BufferedReader in = null;
		OutputStream out = null;

		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod(HTTP_METHOD.POST);
			conn.setRequestProperty(HTTP_CONTENT_TYPE.HEAD_KEY, HTTP_CONTENT_TYPE.JSON);

			out = conn.getOutputStream();
			//参数
			if(param!=null && !"".equals(param)){
				out.write(param.getBytes(UTF8));
			}
			
			out.flush();

			StringBuilder sb = new StringBuilder();
			String line;

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), UTF8));

			while ((line = in.readLine()) != null) {
				sb.append(line).append(LINE_SEPARATOR);
			}
						
			logger.info("http响应的返回值：{}",sb.toString());
			return sb.toString();
		} catch (SocketTimeoutException e) {
			LOG.error("[SocketTimeoutException]remoteCallByJson:url[" + urlStr + "] timout:[" + timeout + "]  msg=" + e.getMessage());
			throw e;
		} catch (MalformedURLException e) {
			LOG.error("[MalformedURLException]remoteCallByJson:url[" + urlStr + "] msg=" + e.getMessage());
			throw e;
		} catch (IOException e) {
			LOG.error("[IOException]remoteCallByJson:url[" + urlStr + "] msg=" + e.getMessage());
			throw e;
		} finally {
			if (out != null) {
				out.flush();
			}

			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);

			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
	public static byte[] doGet(String urlStr, Map<String, String> params,
			int timeout) throws SocketTimeoutException, MalformedURLException,
			IOException {

		HttpURLConnection conn = null;
		InputStream in = null;
		OutputStream out = null;

		try {
			StringBuilder urlParam = new StringBuilder();
			if (params != null) {
				for (String key : params.keySet()) {
					urlParam.append(urlParam.length() == 0 ? "?" : "&");
					urlParam.append(key).append("=").append(params.get(key));
				}
			}

			URL url = new URL(urlStr + urlParam.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod(HTTP_METHOD.GET);

			byte[] buffer = new byte[conn.getContentLength()];
			int offset = 0;
			int numRead = 0;
		    in = conn.getInputStream();
		    
			while (offset < buffer.length
					&& (numRead=in.read(buffer, offset, buffer.length - offset))>= 0) {
				offset += numRead;
			}
			return buffer;
		} catch (SocketTimeoutException e) {
			LOG.error("[SocketTimeoutException]doGet: url[" + urlStr + "] timout:[" + timeout + "]  msg=" + e.getMessage());
			throw e;
		} catch (MalformedURLException e) {
			LOG.error("[MalformedURLException]doGet: url[" + urlStr + "] msg=" + e.getMessage());
			throw e;
		} catch (IOException e) {
			LOG.error("[IOException]doGet: url[" + urlStr + "] msg=" + e.getMessage());
			throw e;
		} finally {
			if (out != null) {
				out.flush();
			}

			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);

			if (conn != null) {
				conn.disconnect();
			}
		}
	}

}
