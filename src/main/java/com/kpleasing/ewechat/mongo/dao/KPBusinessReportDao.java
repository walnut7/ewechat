package com.kpleasing.ewechat.mongo.dao;

import java.util.List;

import com.kpleasing.ewechat.mongo.collections.KPBusinessReport;

public interface KPBusinessReportDao {


	/**
	 * 
	 * @param businessReport
	 */
	public void createKPBusinessReport(KPBusinessReport businessReport);

	
	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public List<KPBusinessReport> findReportByDate(String date) throws Exception;

}
