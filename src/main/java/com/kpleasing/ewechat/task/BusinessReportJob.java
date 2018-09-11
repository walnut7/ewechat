package com.kpleasing.ewechat.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kpleasing.ewechat.service.ReportService;

@Component
public class BusinessReportJob {
	
	private static Logger logger = Logger.getLogger(BusinessReportJob.class);
	
	@Autowired
	private ReportService reportServ;
	
	@Scheduled(cron = "0 30 0 ? * MON")
	public void generateCustomerReport() {
		logger.info("start create Weekly Report info...");
		reportServ.createWeeklyReport();
		logger.info("execute finished!");
	}
	
	
	@Scheduled(cron = "0 0 9 ? * MON")
	public void pushCustomerReport() {
		try {
			logger.info("start push customer report info...");
	        reportServ.pushBusinessReport();
	        logger.info("execute finished!");
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
}
