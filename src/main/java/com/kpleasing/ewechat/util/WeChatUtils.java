package com.kpleasing.ewechat.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kpleasing.ewechat.common.Configure;
import com.kpleasing.ewechat.enums.APP_TYPE;
import com.kpleasing.ewechat.protocol.access_token.AccessTokenResData;

public class WeChatUtils {
	
	private static Logger logger = Logger.getLogger(WeChatUtils.class);
	
	public static String getAccessToken(APP_TYPE type) {
		return getAccessToken(type, false);
	}
	
	/**
	 * 获取微信access_token
	 * 
	 * @param retry
	 * @return
	 */
	public static String getAccessToken(APP_TYPE type, boolean retry) {
		ConfigUtil conf = ConfigUtil.getInstance();
		String access_token = null;
		if(type.equals(APP_TYPE.REPORT)) {
			access_token = conf.getPropertyParam("access_token1");
			
			if (null == access_token || StringUtils.isBlank(access_token) || retry) {
				AccessTokenResData token = parseAccessTokenResult(type);
				conf.setPropertyParam("access_token1", token.getAccess_token());
				access_token = token.getAccess_token();
			}
		} else if(type.equals(APP_TYPE.NOTIFY)) {
			access_token = conf.getPropertyParam("access_token2");
			
			if (null == access_token || StringUtils.isBlank(access_token) || retry) {
				AccessTokenResData token = parseAccessTokenResult(type);
				conf.setPropertyParam("access_token2", token.getAccess_token());
				access_token = token.getAccess_token();
			}
		} else if(type.equals(APP_TYPE.ADDRESS)) {
			access_token = conf.getPropertyParam("access_token3");
			
			if (null == access_token || StringUtils.isBlank(access_token) || retry) {
				AccessTokenResData token = parseAccessTokenResult(type);
				conf.setPropertyParam("access_token3", token.getAccess_token());
				access_token = token.getAccess_token();
			}
		}
		logger.info("access_token = " + access_token) ;
		
		return access_token;
	}

	
	/**
	 * 解析微信access_token值
	 * @return
	 */
	private static AccessTokenResData parseAccessTokenResult(APP_TYPE type) {
		try {
			String result = HttpHelper.doHttpPost(getAccessTokenUrl(type), "");
			logger.info("响应结果："+result);
			
			AccessTokenResData tokenRes = new AccessTokenResData();
			tokenRes = (AccessTokenResData) JsonUtil.jsonToBean(result, tokenRes.getClass());
			return tokenRes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 
	 * @param type
	 * @return
	 */
	private static String getAccessTokenUrl(APP_TYPE type) {
		StringBuilder strUrl = new StringBuilder();
		strUrl.append(Configure.WECHAT_ACCESS_TOKEN_URL)
		.append("?corpid=").append(Configure.corpid);
		if(type.equals(APP_TYPE.REPORT)) {
			strUrl.append("&corpsecret=").append(Configure.corpsecret_order);
		} else if(type.equals(APP_TYPE.NOTIFY)) {
			strUrl.append("&corpsecret=").append(Configure.corpsecret_notify);
		} else if(type.equals(APP_TYPE.ADDRESS)) {
			strUrl.append("&corpsecret=").append(Configure.corpsecret_address);
		} else {
			
		}
		logger.debug("Token请求URL："+strUrl.toString());
		return strUrl.toString();
	}
	
	
	/**
	 * 企业微信-主动发送消息接口地址
	 * @param accessToken
	 * @return
	 */
	public static String getSendMessageUrl(String accessToken) {
		StringBuilder strUrl = new StringBuilder();
		strUrl.append(Configure.WECHAT_SEND_MESSAGE_URL)
		.append("?access_token=").append(accessToken);
		
		return strUrl.toString();
	}
	
	
	/**
	 * 上传临时素材地址
	 * @param accessToken
	 * @param type
	 * @return
	 */
	public static String getUploadMediaUrl(String accessToken, String type) {
		StringBuilder strUrl = new StringBuilder();
		strUrl.append(Configure.WECHAT_UPLOAD_MEDIA_URL)
		.append("?access_token=").append(accessToken)
		.append("&type=").append(type);
		
		return strUrl.toString();
		
	}


	public static String getSendNewsUrl(String accessToken) {
		StringBuilder strUrl = new StringBuilder();
		strUrl.append(Configure.WECHAT_SEND_MESSAGE_URL)
		.append("?access_token=").append(accessToken);
		
		return strUrl.toString();
	}
	
	
	public static String getCreateChatGroupUrl(String accessToken) {
		StringBuilder strUrl = new StringBuilder();
		strUrl.append(Configure.WECHAT_CREATE_CHAT_URL)
		.append("?access_token=").append(accessToken);
		
		return strUrl.toString();
	}
}