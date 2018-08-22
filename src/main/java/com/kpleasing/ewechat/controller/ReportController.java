package com.kpleasing.ewechat.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kpleasing.ewechat.mongo.collections.BusinessMember;
import com.kpleasing.ewechat.mongo.collections.BusinessTeam;
import com.kpleasing.ewechat.service.ReportService;
import com.kpleasing.ewechat.service.WeChatService;
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
	
	@RequestMapping("/generateCR")
    public String generateCrmReport(Map<String, Object> map) {
		try {
	        map.put("hello", "Hello Spring Boot");
	        reportServ.createWeeklyReport();
	        System.out.println("执行完成");
	        logger.info("^^^^^^^^^^^^^^^^ 执行完成 " );
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "/helloHtml";
    }
	
	@RequestMapping("/pushCR")
    public String pushCrmReportMsg(Map<String, Object> map) {
		try {
	        map.put("hello", "Hello Spring Boot");
	        System.out.println("------------------------");
	        reportServ.pushBusinessTeamReport();
	      //  reportServ.sendBusinessMemberReport();
	        System.out.println("执行完成");
	        logger.info("^^^^^^^^^^^^^^^^");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "/success";
    }
	
	
	/**
	 * 销售组组长汇总页
	 * @param searchDate
	 * @param branchName
	 * @param teamID
	 * @param model
	 * @return
	 */
	@RequestMapping("/teamleaderCR")
    public String teamLeaderCrmReportMsg(String searchDate, String branchName, String teamID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t分公司名称："+branchName+"\tTeamID："+teamID);
	        BusinessTeam businessTeam = reportServ.findBusinessTeamReportMsg(searchDate, branchName, teamID);
	        
	        model.addAttribute("team", businessTeam);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "/teamReport";
    }
	
	
	/**
	 * 销售组组长明细页
	 * @param searchDate
	 * @param branchName
	 * @param teamID
	 * @param model
	 * @return
	 */
	@RequestMapping("/teamDetailCR")
    public String teamLeaderCrmReportDetail(String searchDate, String branchName, String teamID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t分公司名称："+branchName+"\tTeamID："+teamID);
	        BusinessTeam businessTeam = reportServ.findBusinessTeamReportMsg(searchDate, branchName, teamID);
	        
	        model.addAttribute("team", businessTeam);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "/teamDetailReport";
    }
	
	
	/**
	 * 销售个人页
	 * @param searchDate
	 * @param branchName
	 * @param teamID
	 * @param saleID
	 * @param model
	 * @return
	 */
	@RequestMapping("/personalCR")
    public String personalCrmReportMsg(String searchDate, String branchName, String teamID, String saleID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t分公司名称："+branchName+"\tTeamID："+teamID+"\t销售ID："+saleID);
	        BusinessTeam businessTeam = reportServ.findBusinessTeamReportMsg(searchDate, branchName, teamID);
	        
	        model.addAttribute("team", businessTeam);
	        List<BusinessMember> businessMemberList = businessTeam.getBusinessMember();
	        for(BusinessMember businessMember : businessMemberList) {
	        	if(saleID.equals(businessMember.getUserId())) {
	        		model.addAttribute("sales", businessMember);
	        	}
	        }
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "/teamLeaderReport";
    }
	
	
	
	@RequestMapping(value = "crm", method = RequestMethod.GET)
	public ModelAndView reportCRM(HttpServletRequest request, String date) {
		ModelMap model = new ModelMap();
		
		logger.info("打开日期1-1："+date);
		model.put("date", date);
		Platform platform = reportServ.getCtripR1(date);
		model.put("pf", platform);
		return new ModelAndView("report/ctrip30", model);
	}
	
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
