package com.kpleasing.ewechat.service;

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

}
