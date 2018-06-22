package com.kpleasing.ewechat.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kpleasing.ewechat.common.Configure;
import com.kpleasing.ewechat.enums.APP_TYPE;
import com.kpleasing.ewechat.protocol.send_text.SendTextResData;
import com.kpleasing.ewechat.service.NotifyService;
import com.kpleasing.ewechat.util.ConfigUtil;
import com.kpleasing.ewechat.util.HttpHelper;
import com.kpleasing.ewechat.util.JsonUtil;
import com.kpleasing.ewechat.util.WeChatUtils;

@Service("NotifyService")
public class NotifyServiceImpl implements NotifyService {
	
	private static Logger logger = Logger.getLogger(NotifyServiceImpl.class);

	@Override
	public void sendOrderTodo(String sendMsg) {
		ConfigUtil conf = ConfigUtil.getInstance();
		StringBuilder strRequest = new StringBuilder();
		strRequest.append("{")
		.append("\"touser\":\"").append(Configure.userid).append("\",")
		.append("\"toparty\":\"\",")
		.append("\"totag\":\"").append(conf.getPropertyParam("totag.notfiy.label3")).append("\",")
		.append("\"msgtype\":\"text\",")
		.append("\"agentid\":\"").append(Configure.NOTIFY_AGENT_ID).append("\",")
		.append("\"text\":{\"content\":\"").append(sendMsg).append("\"},")
		.append("\"safe\":\"0\"}");
		
		try {
			String url = WeChatUtils.getSendMessageUrl(WeChatUtils.getAccessToken(APP_TYPE.NOTIFY));
			logger.info(url);
			
			String result = HttpHelper.doHttpPost(url, strRequest.toString());
			logger.info("响应结果："+result);
			
			SendTextResData resText = new SendTextResData();
			
			resText = (SendTextResData) JsonUtil.jsonToBean(result, resText.getClass());
			
			logger.info("响应结果："+resText);
			if(null != resText.getErrcode() && "42001".equals(resText.getErrcode())) {
				url = WeChatUtils.getSendMessageUrl(WeChatUtils.getAccessToken(APP_TYPE.NOTIFY, true));
				logger.info(url);
				
				result = HttpHelper.doHttpPost(url, strRequest.toString());
				logger.info("响应结果："+result);
			}
			
		} catch (Exception e) {
			logger.error("响应结果：" , e);
			e.printStackTrace();
		}
	}
	
	
	
	/*{
		   "touser" : "UserID1|UserID2|UserID3",
		   "toparty" : "PartyID1 | PartyID2",
		   "totag" : "TagID1 | TagID2",
		   "msgtype" : "textcard",
		   "agentid" : 1,
		   "textcard" : {
		            "title" : "领奖通知",
		            "description" : "<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>",
		            "url" : "URL",
		            "btntxt":"更多"
		   }
		}*/

	@Override
	public void sendNewUser() {
		ConfigUtil conf = ConfigUtil.getInstance();
		StringBuilder strRequest = new StringBuilder();
		strRequest.append("{")
		.append("\"touser\":\"").append(Configure.userid).append("\",")
		.append("\"toparty\":\"\",")
		.append("\"totag\":\"").append(conf.getPropertyParam("totag.notfiy.label1")).append("\",")
		.append("\"msgtype\":\"textcard\",")
		.append("\"agentid\":\"").append(Configure.NOTIFY_AGENT_ID).append("\",")
		.append("\"textcard\":{")
		.append("\"title\" : \"领奖通知\",")
		//.append("\"description\" : \"<div class='gray'>2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>",")
		.append("")
		.append("");
		
		try {
			String url = WeChatUtils.getSendMessageUrl(WeChatUtils.getAccessToken(APP_TYPE.NOTIFY));
			logger.info(url);
			
			String result = HttpHelper.doHttpPost(url, strRequest.toString());
			logger.info("响应结果："+result);
			
			SendTextResData resText = new SendTextResData();
			
			resText = (SendTextResData) JsonUtil.jsonToBean(result, resText.getClass());
			
			logger.info("响应结果："+resText);
			if(null != resText.getErrcode() && "42001".equals(resText.getErrcode())) {
				url = WeChatUtils.getSendMessageUrl(WeChatUtils.getAccessToken(APP_TYPE.NOTIFY, true));
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
