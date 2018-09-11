package com.kpleasing.ewechat.mongo.dao;

import com.kpleasing.ewechat.mongo.collections.CustomerDetail;

public interface CustomerDetailDao {

	
	/**
	 * 
	 * @param custDetail
	 */
	public void createCustomerDetailReport(CustomerDetail custDetail);

	
	/**
	 * 
	 * @param searchDate
	 * @param salesID
	 * @return
	 */
	public CustomerDetail findCustomersBySalesAndDate(String searchDate, String salesID);

}
