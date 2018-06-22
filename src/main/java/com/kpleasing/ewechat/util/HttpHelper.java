package com.kpleasing.ewechat.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpHelper {
	
	private static Logger logger = Logger.getLogger(HttpHelper.class);
	
    private static final int CONNECT_TIMEOUT = 30000;
	
	private static final int SOCKET_TIMEOUT = 30000;
	
	private static final String HTTP_URI_CHARSET = "UTF-8";
	
	/** HTTP保留时间 */
	private final static int MAX_HTTP_KEEP_ALIVE = 30 * 1000;

	/** 每个路由最大连接数 */
	private final static int MAX_ROUTE_CONNECTIONS = 400;
	
	/** 最大连接数 */
	private final static int MAX_TOTAL_CONNECTIONS = 800;
	
	private static PoolingHttpClientConnectionManager connManager = null;
	
	private static CloseableHttpClient httpclient = null;
	
	static {
		HttpRequestRetryHandler myRetryHandler = customRetryHandler();
		ConnectionKeepAliveStrategy customKeepAliveHander = customKeepAliveHander();
		connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		connManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
		HttpHost localhost = new HttpHost("locahost", 80);
		connManager.setMaxPerRoute(new HttpRoute(localhost), 50);
		httpclient = HttpClients.custom().setConnectionManager(connManager).setKeepAliveStrategy(customKeepAliveHander)
		        .setRetryHandler(myRetryHandler).build();
	}
	
	/**
	 * 设置HTTP连接保留时间
	 *
	 * @return 保留策略
	 */
	private static ConnectionKeepAliveStrategy customKeepAliveHander() {
		ConnectionKeepAliveStrategy myKeepAliveHander = new ConnectionKeepAliveStrategy() {

			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				return MAX_HTTP_KEEP_ALIVE;
			}
		};
		return myKeepAliveHander;
	}

	/**
	 * 是否重试
	 *
	 * @return false，不重试
	 */
	private static HttpRequestRetryHandler customRetryHandler() {
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				return false;
			}
		};
		return myRetryHandler;
	}
	
	
	/**
	 * 下载文件保存到本地
	 * @param url
	 * @param paras
	 * @return
	 */
	public static String doHttpDownload(String url, Map<String, String> paras) {
		CloseableHttpResponse response = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		BufferedInputStream bufInputStream = null;
		BufferedOutputStream  bufOutputStream = null;
		HttpPost httpPost = new HttpPost(url);
		
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type","text/html; charset=utf-8"); 
		
		String result = null;
		try {
			response = httpclient.execute(httpPost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				
				inputStream = entity.getContent();
				outputStream = new FileOutputStream(new File(paras.get("save_file_addr")));
				bufOutputStream = new BufferedOutputStream(outputStream);
				bufInputStream = new BufferedInputStream(inputStream);
				byte[] buffer = new byte[1024];
				
				int count = 0; 
				while((count=bufInputStream.read(buffer))!=-1)  {
					if(count!=0) {
						bufOutputStream.write(buffer, 0, count);
					}
				}
				bufOutputStream.flush();
				
			} else {
				HttpEntity httpEntity =  response.getEntity();
				String contentR = EntityUtils.toString(httpEntity);
				logger.info(contentR);
				logger.info("post 状态：" + response.getStatusLine().toString() + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			close(outputStream, inputStream, bufOutputStream, bufInputStream);
			httpPost.releaseConnection();
			HttpClientUtils.closeQuietly(response);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param outputStream
	 * @param inputStream
	 * @param bufOutputStream
	 * @param bufInputStream
	 */
	private static void close(OutputStream outputStream, InputStream inputStream, BufferedOutputStream bufOutputStream, BufferedInputStream bufInputStream) {
		try {
			if(outputStream!=null) {
				outputStream.close();
			}
			
			if(inputStream!=null) {
				inputStream.close();
			}
			
			if(bufOutputStream!=null) {
				bufOutputStream.close();
			}
			
			if(bufInputStream!=null) {
				bufInputStream.close();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * http post request
 	 * @param url
	 * @param content
	 * @param type
	 * @return
	 */
	public static String doHttpPost(String url, String content) {
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		
		logger.info("post 报文信息:"+content);
		httpPost.setHeader("Content-Type","application/xml; charset=utf-8"); 
		StringEntity reqEntity = new StringEntity(content, ContentType.TEXT_HTML.withCharset("UTF-8"));
		httpPost.setEntity(reqEntity);
		
		String result = null;
		try {
			response = httpclient.execute(httpPost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				StringBuffer sb = new StringBuffer();
				HttpEntity entity = response.getEntity();

				BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent(), HTTP_URI_CHARSET));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
				result = sb.toString();
				logger.info("post 結果：" + result);
				in.close();
			} else {
				HttpEntity httpEntity =  response.getEntity();
				String contentR = EntityUtils.toString(httpEntity);
				logger.info(contentR);
				logger.info("post 状态：" + response.getStatusLine().toString() + response.getStatusLine().getStatusCode());
				
			}
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			
		}
		finally {
			httpPost.releaseConnection();
			HttpClientUtils.closeQuietly(response);
		}
		return result;
	}
	
	
	public static String doHttpBinaryPost(String url, String base64string) {
		CloseableHttpResponse response = null;
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		
		try {
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
			httpPost.setConfig(requestConfig);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

			// multipartEntityBuilder.addTextBody("filelength", "");
		    // multipartEntityBuilder.addBinaryBody("filename", new File("D:\\www\\y.jpg"), ContentType.APPLICATION_OCTET_STREAM.withCharset("UTF-8"), "media.jpg");
			multipartEntityBuilder.addBinaryBody("filename", Base64.decodeBase64(base64string), ContentType.APPLICATION_OCTET_STREAM.withCharset("UTF-8"), "media.jpg");
			HttpEntity entityBuild = multipartEntityBuilder.build();
			httpPost.setEntity(entityBuild);
			
			response = httpclient.execute(httpPost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				StringBuffer sb = new StringBuffer();
				HttpEntity entity = response.getEntity();

				BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent(), HTTP_URI_CHARSET));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
				result = sb.toString();
				System.out.println("post 結果：" + result);
				in.close();
			} else {
				HttpEntity httpEntity =  response.getEntity();
				String contentR = EntityUtils.toString(httpEntity);
				System.out.println(contentR);
				System.out.println("post 状态：" + response.getStatusLine().toString() + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result="FALSE";
		}
		finally {
			httpPost.releaseConnection();
			HttpClientUtils.closeQuietly(response);
		}
		return result;
	}
}
