package com.acfun.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Administrator
 *
 */
public class HttpClientUtil {


	/**
	 * 发送Get请求
	 */
	 public static String sendHttpGet(String url) {
		String result = "";
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口

		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpMethod method = new GetMethod(url);
		// 使用POST方法
		// HttpMethod method = new PostMethod("http://java.sun.com");
		try {
			client.executeMethod(method);
			result = method.getResponseBodyAsString();
			System.out.println("send:" + method.getStatusLine().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			method.releaseConnection();
		}
		return result;

	}

	public static String sendHttpPOST(String url, Map<String, Object> paramMap) {
		String result = "";
		HttpClient client = new HttpClient();

		try {
			// 设置代理服务器地址和端口
			HttpMethod method = postMethod(url, paramMap);
			// 使用POST方法
			client.executeMethod(method);
			result = method.getResponseBodyAsString();
			// 释放连接
			method.releaseConnection();
			System.out.println("send:" + method.getStatusLine().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	private static HttpMethod postMethod(String url,
			Map<String, Object> paramMap) throws IOException {
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		post.setRequestHeader("Accept-Charset",
				"GBK");

		NameValuePair[] param = new NameValuePair[paramMap.size()];
		int index = 0;
		for (String key : paramMap.keySet()) {
			param[index] = new NameValuePair(key, paramMap.get(key).toString());
			index++;
		}
		post.setRequestBody(param);
		post.releaseConnection();
		return post;
	}

	public static void main(String[] args) throws Exception {

		System.out.println(sendHttpGet("http://10.10.168.150:8080/uapws/service/com.yonyou.itf.mdm07.sharing.IMdSharingCenterService?wsdl"));

	}
}
