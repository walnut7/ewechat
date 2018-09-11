package com.kpleasing.ewechat.mongo.collections;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="report_customer_detail")  
public class CustomerDetail {
	private String report_date;
	private String sales_id;
	private String sales_name;
	private List<CustomerInfo> fallowCustomerDetail;
	private List<CustomerInfo> lastWeekAddCustomerDetail;
	private List<CustomerInfo> lastWeekRentCustomerDetail;
	private List<CustomerInfo> customerADetail;
	private List<CustomerInfo> customerBDetail;
	private List<CustomerInfo> lastWeekCallbackCustomerDetail;
	private List<CustomerInfo> callbackCustomerDetail;
	private List<CustomerInfo> uncallback3CustomerDetail;
	private List<CustomerInfo> uncallback7CustomerDetail;
	private List<CustomerInfo> uncallback15CustomerDetail;
	
	public String getReport_date() {
		return report_date;
	}

	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}

	public String getSales_id() {
		return sales_id;
	}

	public void setSales_id(String sales_id) {
		this.sales_id = sales_id;
	}

	public String getSales_name() {
		return sales_name;
	}

	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}

	public List<CustomerInfo> getFallowCustomerDetail() {
		return fallowCustomerDetail;
	}
	
	public void setFallowCustomerDetail(List<CustomerInfo> fallowCustomerDetail) {
		this.fallowCustomerDetail = fallowCustomerDetail;
	}

	public List<CustomerInfo> getLastWeekAddCustomerDetail() {
		return lastWeekAddCustomerDetail;
	}

	public void setLastWeekAddCustomerDetail(List<CustomerInfo> lastWeekAddCustomerDetail) {
		this.lastWeekAddCustomerDetail = lastWeekAddCustomerDetail;
	}

	public List<CustomerInfo> getLastWeekRentCustomerDetail() {
		return lastWeekRentCustomerDetail;
	}

	public void setLastWeekRentCustomerDetail(List<CustomerInfo> lastWeekRentCustomerDetail) {
		this.lastWeekRentCustomerDetail = lastWeekRentCustomerDetail;
	}

	public List<CustomerInfo> getCustomerADetail() {
		return customerADetail;
	}

	public void setCustomerADetail(List<CustomerInfo> customerADetail) {
		this.customerADetail = customerADetail;
	}

	public List<CustomerInfo> getCustomerBDetail() {
		return customerBDetail;
	}

	public void setCustomerBDetail(List<CustomerInfo> customerBDetail) {
		this.customerBDetail = customerBDetail;
	}

	public List<CustomerInfo> getLastWeekCallbackCustomerDetail() {
		return lastWeekCallbackCustomerDetail;
	}

	public void setLastWeekCallbackCustomerDetail(List<CustomerInfo> lastWeekCallbackCustomerDetail) {
		this.lastWeekCallbackCustomerDetail = lastWeekCallbackCustomerDetail;
	}

	public List<CustomerInfo> getCallbackCustomerDetail() {
		return callbackCustomerDetail;
	}

	public void setCallbackCustomerDetail(List<CustomerInfo> callbackCustomerDetail) {
		this.callbackCustomerDetail = callbackCustomerDetail;
	}

	public List<CustomerInfo> getUncallback3CustomerDetail() {
		return uncallback3CustomerDetail;
	}

	public void setUncallback3CustomerDetail(List<CustomerInfo> uncallback3CustomerDetail) {
		this.uncallback3CustomerDetail = uncallback3CustomerDetail;
	}

	public List<CustomerInfo> getUncallback7CustomerDetail() {
		return uncallback7CustomerDetail;
	}

	public void setUncallback7CustomerDetail(List<CustomerInfo> uncallback7CustomerDetail) {
		this.uncallback7CustomerDetail = uncallback7CustomerDetail;
	}

	public List<CustomerInfo> getUncallback15CustomerDetail() {
		return uncallback15CustomerDetail;
	}

	public void setUncallback15CustomerDetail(List<CustomerInfo> uncallback15CustomerDetail) {
		this.uncallback15CustomerDetail = uncallback15CustomerDetail;
	}
}
