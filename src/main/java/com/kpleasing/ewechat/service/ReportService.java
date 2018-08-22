package com.kpleasing.ewechat.service;

import com.kpleasing.ewechat.mongo.collections.BusinessTeam;
import com.kpleasing.ewechat.vo.Platform;

public interface ReportService {

	/**
	 * 
	 * @param b
	 */
	public void pushNews(boolean b);

	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public Platform getCtripR1(String date);

	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public Platform getCtripR2(String date);


	public void createWeeklyReport();

	public void pushBusinessTeamReport();


	public BusinessTeam findBusinessTeamReportMsg(String date, String branchCompanyName, String teamID) throws Exception;


	public void findBusinessPersonalReportMsg();
}
