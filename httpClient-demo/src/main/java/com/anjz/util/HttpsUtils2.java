package com.anjz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsUtils2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpsUtils2.class);

	/**
	 * 设置信任自签名证书
	 * 
	 * @param keyStorePath
	 *            密钥库路径
	 * @param keyStorepass
	 *            密钥库密码
	 * @return
	 */
	private static SSLContext custom(String keyStorePath, String keyStorepass) {
		SSLContext sc = null;
		FileInputStream instream = null;
		KeyStore trustStore = null;
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			instream = new FileInputStream(new File(keyStorePath));
			trustStore.load(instream, keyStorepass.toCharArray());
			// 相信自己的CA和所有自签名的证书
			sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException
				| KeyManagementException e) {
			e.printStackTrace();
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
			}
		}
		return sc;
	}

	public static String post(String url, Map<String, String> param) throws ClientProtocolException, IOException {
		String content=null;
		// 证书、密码
		SSLContext sslcontext = custom(HttpsUtils2.class.getResource("/").getPath()+"tomcat.keystore", "123456");

		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslcontext)).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

		// 创建自定义的httpclient对象
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();

		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		// 装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (param != null) {
			for (Entry<String, String> entry : param.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		// 设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		LOGGER.info("请求地址:{}", url);
		LOGGER.info("请求参数:{}", nvps.toString());

		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
//		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
//		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		 //执行请求操作，并拿到结果（同步阻塞）  
	    CloseableHttpResponse response = client.execute(httpPost);  
	    //获取结果实体  
	    HttpEntity entity = response.getEntity();  
	    if (entity != null) {  
	        //按指定编码转换结果实体为String类型  
	    	content = EntityUtils.toString(entity);  
	    }  
//	    EntityUtils.consume(entity);  
	    //释放链接  
	    response.close();  
		return content;
	}
}
