package com.kpleasing.ewechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpleasing.ewechat.protocol.send_media.SendMediaReqData;
import com.kpleasing.ewechat.protocol.send_text.SendTextReqData;
import com.kpleasing.ewechat.service.WeChatService;


@Controller
public class EWeChatDemo {
	
	private static Logger logger = Logger.getLogger(EWeChatDemo.class);
	
	@Autowired
    private WeChatService wechatService;

	
	@RequestMapping(value = "/sendText", method = RequestMethod.POST )
	public @ResponseBody String sendTextDemo(HttpServletRequest request, @RequestBody String requestBody) throws Exception {
		SendTextReqData textReq = new SendTextReqData();
		textReq.setTouser(request.getParameter("touser"));
		textReq.setText(request.getParameter("text"));
		
		wechatService.sendEWeChatTextMessage("text", textReq);
		return "{\"result\":\"success\",\"message\":\"文本信息发送成功\"}";
	}
	
	
	@RequestMapping(value = "/sendImage", method = RequestMethod.POST )
	public @ResponseBody String sendImageDemo(HttpServletRequest request, @RequestBody String requestBody) throws Exception {
		SendMediaReqData mediaReq = new SendMediaReqData();
		mediaReq.setTouser(request.getParameter("touser"));
		String base64String = request.getParameter("base64string");
		
		wechatService.sendEWeChatMediaMessage("image", mediaReq, base64String);
		return "{\"result\":\"success\",\"message\":\"文本信息发送成功\"}";
	}
	
	
	
	/**
	 * 图文发送
	 * @param request
	 * @param requestBody
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendNews", method = RequestMethod.POST )
	public @ResponseBody String sendNewsDemo(HttpServletRequest request, @RequestBody String requestBody) {
		try {
			wechatService.sendEWeChatNewsMessage();
			return "{\"result\":\"success\",\"message\":\"图文信息发送成功\"}";
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "{\"result\":\"failed\",\"message\":\"图文信息送失败\"}";
	}
}
