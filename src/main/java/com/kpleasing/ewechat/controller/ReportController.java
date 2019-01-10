package com.kpleasing.ewechat.controller;

import java.util.List;

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
import com.kpleasing.ewechat.mongo.collections.CustomerDetail;
import com.kpleasing.ewechat.mongo.collections.CustomerInfo;
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
    public String generateCrmReport(Model model) {
		try {
	        reportServ.createWeeklyReport();
	        logger.info("报表创建，执行完成 " );
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "success";
    }
	
	@RequestMapping("/pushCR")
    public String pushCrmReportMsg(Model model) {
		try {
	        reportServ.pushBusinessReport();
	        logger.info("报表推送，执行完成 " );
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "success";
    }
	
	
	@RequestMapping("/teamTopCR")
    public String branchCrmReportMsg(String searchDate, String branchName, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t分公司名称："+branchName);
	        List<BusinessTeam> teamList = reportServ.findBusinessBranchReportMsg(searchDate, branchName);
	        
	        model.addAttribute("teams", teamList);
	        model.addAttribute("searchDate", searchDate);
	        model.addAttribute("branchName", branchName);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "teamTopReport";
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
	        model.addAttribute("searchDate", searchDate);
	        model.addAttribute("branchName", branchName);
	        model.addAttribute("teamID", teamID);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "teamReport";
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
	        model.addAttribute("branchName", branchName);
	        model.addAttribute("searchDate", searchDate);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "teamDetailReport";
    }
	
	
	/**
	 * 销售个人页
	 * @param searchDate
	 * @param branchName
	 * @param teamID
	 * @param salesID
	 * @param model
	 * @return
	 */
	@RequestMapping("/personalCR")
    public String personalCrmReportMsg(String searchDate, String branchName, String teamID, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t分公司名称："+branchName+"\tTeamID："+teamID+"\t销售ID："+salesID);
	        BusinessTeam businessTeam = reportServ.findBusinessTeamReportMsg(searchDate, branchName, teamID);
	        
	        model.addAttribute("team", businessTeam);
	        model.addAttribute("branchName", branchName);
	        model.addAttribute("searchDate", searchDate);
	        model.addAttribute("salesID", salesID);
	        List<BusinessMember> businessMemberList = businessTeam.getBusinessMember();
	        for(BusinessMember businessMember : businessMemberList) {
	        	if(salesID.equals(businessMember.getUserId())) {
	        		model.addAttribute("sales", businessMember);
	        	}
	        }
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "personalReport";
    }
	
	
	/**
	 * 
	 * @param searchDate
	 * @param salesID
	 * @param model
	 * @return
	 */
	@RequestMapping("/fallowCustomerDetailCR")
    public String fallowCustomerReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID："+salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> fallowCustomerDetail = customerDetail.getFallowCustomerDetail();
	       
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", fallowCustomerDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "fallowCustomerDetailReport";
    }
	
	
	/**
	 * 
	 * @param searchDate
	 * @param salesID
	 * @param model
	 * @return
	 */
	@RequestMapping("/lastWeekAddCustomerDetailCR")
    public String lastWeekAddCustomerReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID："+salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> lastWeekAddCustomerDetail = customerDetail.getLastWeekAddCustomerDetail();
	        
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", lastWeekAddCustomerDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "lastWeekAddCustomerDetailReport";
    }
	

	@RequestMapping("/lastWeekRentCustomerDetailCR")
    public String lastWeekRentCustomerReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID："+salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> lastWeekRentCustomerDetail = customerDetail.getLastWeekRentCustomerDetail();
	        
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", lastWeekRentCustomerDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "lastWeekRentCustomerDetailReport";
    }
	
	
	@RequestMapping("/customerADetailCR")
    public String customerAReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID：" + salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> customerADetail = customerDetail.getCustomerADetail();
	        
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", customerADetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "customerADetailReport";
    }
	
	
	@RequestMapping("/customerBDetailCR")
    public String customerBReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID："+salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> customerBDetail = customerDetail.getCustomerBDetail();
	        
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", customerBDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "customerBDetailReport";
    }
	
	@RequestMapping("/lastWeekCallbackCustomerDetailCR")
    public String lastWeekCallbackCustomerReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID："+salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> lastWeekCallbackCustomerDetail = customerDetail.getLastWeekCallbackCustomerDetail();
	        
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", lastWeekCallbackCustomerDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "lastWeekCallbackCustomerDetailReport";
    }
	
	
	@RequestMapping("/uncallbackCustomerABDetailCR")
    public String uncallbackCustomerABReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID："+salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> uncallbackCustomerABDetail = customerDetail.getUncallback3CustomerDetail();
	        
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", uncallbackCustomerABDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "uncallbackABCustomerDetailReport";
    }
	
	
	@RequestMapping("/uncallback7CustomerDetailCR")
    public String uncallback7CustomerReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID："+salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> uncallback7CustomerDetail = customerDetail.getUncallback7CustomerDetail();
	        
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", uncallback7CustomerDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "uncallback7CustomerDetailReport";
    }
	
	
	@RequestMapping("/uncallback15CustomerDetailCR")
    public String uncallback15CustomerReportDetail(String searchDate, String salesID, Model model) {
		try {
	        logger.info("报表日期："+searchDate+"\t销售员ID："+salesID);
	        CustomerDetail customerDetail = reportServ.findCustomersDetailReportMsg(searchDate, salesID);
	        List<CustomerInfo> uncallback15CustomerDetail = customerDetail.getUncallback15CustomerDetail();
	        
	        model.addAttribute("owername", customerDetail.getSales_name());
	        model.addAttribute("customers", uncallback15CustomerDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
        return "uncallback15CustomerDetailReport";
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
