package com.kpleasing.ewechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kpleasing.ewechat.service.ReportService;
import com.kpleasing.ewechat.service.WeChatService;
import com.kpleasing.ewechat.service.impl.WeChatServiceImpl;
import com.kpleasing.ewechat.vo.Merchant;
import com.kpleasing.ewechat.vo.Platform;


@Controller
@RequestMapping(value = "/report")
public class ReportController {
	
	private static Logger logger = Logger.getLogger(ReportController.class);
	
	@Autowired
	private WeChatService wechatService;
	
	@Autowired
	private ReportService reportServ;
	
	
	@RequestMapping(value = "ctrip30", method = RequestMethod.GET)
	public ModelAndView reportCtrip30(HttpServletRequest request, String date) {
		ModelMap model = new ModelMap();
		
		logger.info("打开日期1-1："+date);
		model.put("date", date);
		Platform platform = reportServ.getCtripR1(date);
		model.put("pf", platform);
		return new ModelAndView("report/ctrip30", model);
	}
	
	@RequestMapping(value = "ctrip30detail", method = RequestMethod.GET)
	public ModelAndView reportCtrip30Detail(HttpServletRequest request, String merchant_name, String date) {
		ModelMap model = new ModelMap();
		
		logger.info("打开日期1-2："+date);
		Platform platform = reportServ.getCtripR1(date);
		
		for(Merchant merchant : platform.getMerchants()) {
			if(merchant_name.equals(merchant.getMerchantName())) {
				model.put("merchant", merchant);
				break;
			}
		}
		return new ModelAndView("report/ctripdetail", model);
	}
	
	
	@RequestMapping(value = "purchase", method = RequestMethod.GET)
	public ModelAndView reportPurchase(HttpServletRequest request, String date) {
		ModelMap model = new ModelMap();
		
		logger.info("打开日期2-1："+date);
		Platform platform = reportServ.getCtripR2(date);
		model.put("pf", platform);
		return new ModelAndView("report/purchase", model);
	}
	
	
	@RequestMapping(value = "chatgroup", method = RequestMethod.GET)
	public ModelAndView createChatGroup(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		 wechatService.createChatGroup();
		return new ModelAndView("report/purchase", model);
	}

}
