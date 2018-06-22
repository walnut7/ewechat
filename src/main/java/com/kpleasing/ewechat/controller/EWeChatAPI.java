package com.kpleasing.ewechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpleasing.ewechat.service.NotifyService;
import com.kpleasing.ewechat.service.ReportService;


@Controller
@RequestMapping(value = "/api")
public class EWeChatAPI {
	
	private static Logger logger = Logger.getLogger(EWeChatAPI.class);
	
	@Autowired
	private NotifyService notifyServ;
	
	@Autowired
	private ReportService reportServ;
	
	
	// @RequestMapping(value = "push", method = RequestMethod.POST, consumes = "application/xml")
	@RequestMapping(value = "push")
	public @ResponseBody String getStaffLogin() {
		
		// 下载文件
		reportServ.pushNews(true);
		
		return "main";
		
	}
	
	
	@RequestMapping(value = "notify")
	public @ResponseBody String notifyCreateOrder(HttpServletRequest request, @RequestBody String requestBody) {
		String custName = request.getParameter("cust_name");
		String certCode = request.getParameter("cert_code");
		String phone = request.getParameter("phone");
		
		StringBuilder sbMsg = new StringBuilder();
		sbMsg.append("用户：").append(custName)
		.append("，手机号：").append(phone)
		.append("，证件号码：").append(certCode)
		.append("\n已通过微信身份验证，请为其提交相关【融资租赁方案申请】！");
		
		// 下载文件
		notifyServ.sendOrderTodo(sbMsg.toString());
		
		return "main";
		
	}
	
	
	@RequestMapping(value = "newUser")
	public @ResponseBody String notifyNewUser(HttpServletRequest request, @RequestBody String requestBody) {
		
		// 下载文件
		notifyServ.sendNewUser();
		
		return "main";
	}
}
