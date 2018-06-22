package com.kpleasing.ewechat.service;

import com.kpleasing.ewechat.protocol.send_media.SendMediaReqData;
import com.kpleasing.ewechat.protocol.send_text.SendTextReqData;

public interface WeChatService {

	/**
	 * 推送文本信息
	 * @param type
	 * @param textReq
	 */
	public void sendEWeChatTextMessage(String type, SendTextReqData textReq);

	
	/**
	 * 推送多媒体信息
	 * @param string
	 * @param mediaReq
	 * @param base64String
	 */
	public void sendEWeChatMediaMessage(String string, SendMediaReqData mediaReq, String base64String);


	/**
	 * 
	 * @param string
	 */
	public void sendEWeChatNewsMessage();

	public void createChatGroup();
}
