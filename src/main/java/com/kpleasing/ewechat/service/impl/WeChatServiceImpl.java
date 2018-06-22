package com.kpleasing.ewechat.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kpleasing.ewechat.common.Configure;
import com.kpleasing.ewechat.enums.APP_TYPE;
import com.kpleasing.ewechat.protocol.send_media.SendMediaReqData;
import com.kpleasing.ewechat.protocol.send_text.SendTextReqData;
import com.kpleasing.ewechat.protocol.send_text.SendTextResData;
import com.kpleasing.ewechat.protocol.upload_media.UploadMediaResData;
import com.kpleasing.ewechat.service.WeChatService;
import com.kpleasing.ewechat.util.HttpHelper;
import com.kpleasing.ewechat.util.JsonUtil;
import com.kpleasing.ewechat.util.WeChatUtils;


@Service("WeChatService")
public class WeChatServiceImpl implements WeChatService {
	
	private static Logger logger = Logger.getLogger(WeChatServiceImpl.class);


	@Override
	public void sendEWeChatTextMessage(String type, SendTextReqData textReq) {
		StringBuilder strRequest = new StringBuilder();
		
		strRequest.append("{")
		.append("\"touser\":\"").append(textReq.getTouser()).append("\",")
		.append("\"toparty\":\"\",")
		.append("\"totag\":\"\",")
		.append("\"msgtype\":\"text\",")
		.append("\"agentid\":\"1000002\",")
		.append("\"text\":{\"content\":\"").append(textReq.getText()).append("\"},")
		.append("\"safe\":\"0\"}");
		
		try {
			String url = WeChatUtils.getSendMessageUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT));
			logger.info(url);
			
			String result = HttpHelper.doHttpPost(url, strRequest.toString());
			logger.info("响应结果："+result);
			
			SendTextResData resText = new SendTextResData();
			
			resText = (SendTextResData) JsonUtil.jsonToBean(result, resText.getClass());
			
			logger.info("响应结果："+resText);
			if(null != resText.getErrcode() && "42001".equals(resText.getErrcode())) {
				url = WeChatUtils.getSendMessageUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT, true));
				logger.info(url);
				
				result = HttpHelper.doHttpPost(url, strRequest.toString());
				logger.info("响应结果："+result);
			}
			
		} catch (Exception e) {
			logger.error("响应结果：" , e);
			e.printStackTrace();
		}
		
	}


	@Override
	public void sendEWeChatMediaMessage(String string, SendMediaReqData mediaReq, String base64String) {
		try {
			// 3iMPKUY1scUyTT-RtSYiJqXLEkn9OuczMYMbPU25fIkU
			String mediaUrl = WeChatUtils.getUploadMediaUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT), "image");
			logger.info("开始上传图片。。。。。。。"+mediaUrl);
			String result = HttpHelper.doHttpBinaryPost(mediaUrl, base64String);
			logger.info("多媒体图片上传响应结果==》"+result);
			UploadMediaResData  resMedia = new UploadMediaResData();
			resMedia = (UploadMediaResData) JsonUtil.jsonToBean(result, resMedia.getClass());
			if(StringUtils.isNotBlank(resMedia.getErrcode()) && "42001".equals(resMedia.getErrcode())) {
				mediaUrl = WeChatUtils.getSendMessageUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT, true));
				
				result = HttpHelper.doHttpBinaryPost(mediaUrl, base64String);
				logger.info("重新获取token后，多媒体图片上传响应结果==》" + result);
				resMedia = (UploadMediaResData) JsonUtil.jsonToBean(result, resMedia.getClass());
			}
			logger.info("media_id=："+resMedia.getMedia_id());
			if(!"FALSE".equals(result) && StringUtils.isNotBlank(resMedia.getMedia_id())) {
				String imageUrl = WeChatUtils.getSendMessageUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT));
				
				StringBuilder content = new StringBuilder();
				content.append("{\"touser\":\"HuangZhenHua\",")
				.append("\"toparty\":\"\",")
				.append("\"totag\":\"\",")
				.append("\"msgtype\":\"image\",")
				.append("\"agentid\":\"1000002\",")
				.append("\"image\":{\"media_id\":\"").append(resMedia.getMedia_id()).append("\"},")
				.append("\"safe\":\"0\"}");
				
				result = HttpHelper.doHttpPost(imageUrl, content.toString());
				logger.info("图片信息推送响应结果=》"+result);
				
				SendTextResData resText = new SendTextResData();
				resText = (SendTextResData) JsonUtil.jsonToBean(result, resText.getClass());
					
			}
		} catch (Exception e) {
			logger.error("失败响应结果=>" , e);
			e.printStackTrace();
		}
	}


	@Override
	public void sendEWeChatNewsMessage() {
		logger.info("start info ...... ");
		StringBuilder strRequest = new StringBuilder();
		strRequest.append("{")
		.append("\"touser\":\"").append(Configure.userid).append("\",")
		.append("\"toparty\":\"\",")
		.append("\"totag\":\"\",")
		.append("\"msgtype\":\"news\",")
		.append("\"agentid\":\"1000002\",")
		.append("\"news\":{")
		.append("     \"articles\": [")
		.append("          {")
		.append("               \"title\":\"中秋节礼品领取\", ")
		.append("               \"description\":\"今年中秋节公司有豪礼相送\", ")
		.append("               \"url\":\"http://www.baidu.com/\", ")
		.append("               \"picurl\":\"http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png\", ")
		.append("               \"btntxt\":\"更多\" ")
		.append("          }")
		.append("      ]")
		.append("    }")
		.append("}");
		logger.info(strRequest.toString());
		
		try {
			String url = WeChatUtils.getSendNewsUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT));
			logger.info(url);
			
			String result = HttpHelper.doHttpPost(url, strRequest.toString());
			logger.info("响应结果："+result);
			
			SendTextResData resText = new SendTextResData();
			
			resText = (SendTextResData) JsonUtil.jsonToBean(result, resText.getClass());
			
			logger.info("响应结果："+resText);
			if(null != resText.getErrcode() && "42001".equals(resText.getErrcode())) {
				url = WeChatUtils.getSendNewsUrl(WeChatUtils.getAccessToken(APP_TYPE.REPORT, true));
				logger.info(url);
				
				result = HttpHelper.doHttpPost(url, strRequest.toString());
				logger.info("响应结果："+result);
			}
			
		} catch (Exception e) {
			logger.error("响应结果：" , e);
			e.printStackTrace();
		}
	}
	

	@Override
	public void createChatGroup() {
		logger.info("start info ...... ");
		
		StringBuilder strRequest = new StringBuilder();
		strRequest.append("{")
			.append(" \"name\" : \"测试群\",")
			.append(" \"owner\" : \"HuangZhenHua\",")
			.append(" \"userlist\" : [\"HuangZhenHua\", \"WangLei\"],")
			.append(" \"chatid\" : \"CHATID\" ")
			.append("}");
		logger.info(strRequest.toString());
		
		try {
			String url = WeChatUtils.getCreateChatGroupUrl(WeChatUtils.getAccessToken(APP_TYPE.ADDRESS));
			logger.info(url);
			
			String result = HttpHelper.doHttpPost(url, strRequest.toString());
			logger.info("响应结果："+result);
			
			SendTextResData resText = new SendTextResData();
			
			resText = (SendTextResData) JsonUtil.jsonToBean(result, resText.getClass());
			
			logger.info("响应结果："+resText);
			if(null != resText.getErrcode() && "42001".equals(resText.getErrcode())) {
				url = WeChatUtils.getSendNewsUrl(WeChatUtils.getAccessToken(APP_TYPE.ADDRESS, true));
				logger.info(url);
				
				result = HttpHelper.doHttpPost(url, strRequest.toString());
				logger.info("响应结果："+result);
			}
			
		} catch (Exception e) {
			logger.error("响应结果：" , e);
			e.printStackTrace();
		}
	
		
	}
}
