package com.kpleasing.ewechat.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kpleasing.ewechat.service.MsgService;


@Controller
@RequestMapping(value = "/manage")
public class MsgController {
	
	private static Logger logger = Logger.getLogger(MsgController.class);
	
	@Autowired
	private MsgService msgService;
	
	@RequestMapping("/userlist")
    public String pushUserList(Model model) {
		/*try {
	        logger.info("报表日期："+searchDate+"\t分公司名称："+branchName);
	        List<BusinessTeam> teamList = reportServ.findBusinessBranchReportMsg(searchDate, branchName);
	        
	        model.addAttribute("teams", teamList);
	        model.addAttribute("searchDate", searchDate);
	        model.addAttribute("branchName", branchName);
		} catch(Exception ex) {
			ex.printStackTrace();
		}*/
        return "manage/pushUserList";
    }

}
