package com.kpleasing.ewechat.service;

import java.util.List;

import com.kpleasing.ewechat.mongo.collections.BusinessTeam;
import com.kpleasing.ewechat.mongo.collections.CustomerDetail;
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


	/**
	 * 
	 */
	public void createWeeklyReport();

	
	/**
	 * @param map 
	 * 
	 */
	public void pushBusinessReport();

	/**
	 * 
	 * @param date
	 * @param branchCompanyName
	 * @param teamID
	 * @return
	 * @throws Exception
	 */
	public BusinessTeam findBusinessTeamReportMsg(String date, String branchCompanyName, String teamID) throws Exception;


	/**
	 * 
	 * @param searchDate
	 * @param branchName
	 * @return
	 * @throws Exception 
	 */
	public List<BusinessTeam> findBusinessBranchReportMsg(String searchDate, String branchName) throws Exception;


	/**
	 * 
	 * @param searchDate
	 * @param salesID
	 * @return
	 */
	public CustomerDetail findCustomersDetailReportMsg(String searchDate, String salesID);
}
