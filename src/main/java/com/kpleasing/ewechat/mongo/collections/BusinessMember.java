package com.kpleasing.ewechat.mongo.collections;

public class BusinessMember {
	
	private String userId;
	private String userName;
	private int fallowCustomers;              // 跟进客户数
	private int lastWeekAddCustomers;         // 上周新登记客户数
	private int lastWeekRentCustomers;        // 上周起租合同数
	private int customerA;                  // A 类客户数
	private int customerB;                  // B 类客户数
	private int lastWeekCallbackCustomers;  // 上周回访客户数
	private int callbackCustomers;          // 总回访次数
	private int uncallback3Customers;       // 未及时回访AB类客户数
	private int uncallback7Customers;       // 超1周未回访客户数
	private int uncallback15Customers;      // 应强制转交或放弃客户数
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getFallowCustomers() {
		return fallowCustomers;
	}

	public void setFallowCustomers(int fallowCustomers) {
		this.fallowCustomers = fallowCustomers;
	}

	public int getLastWeekAddCustomers() {
		return lastWeekAddCustomers;
	}

	public void setLastWeekAddCustomers(int lastWeekAddCustomers) {
		this.lastWeekAddCustomers = lastWeekAddCustomers;
	}

	public int getLastWeekRentCustomers() {
		return lastWeekRentCustomers;
	}

	public void setLastWeekRentCustomers(int lastWeekRentCustomers) {
		this.lastWeekRentCustomers = lastWeekRentCustomers;
	}

	public int getCustomerA() {
		return customerA;
	}

	public void setCustomerA(int customerA) {
		this.customerA = customerA;
	}

	public int getCustomerB() {
		return customerB;
	}

	public void setCustomerB(int customerB) {
		this.customerB = customerB;
	}

	public int getLastWeekCallbackCustomers() {
		return lastWeekCallbackCustomers;
	}

	public void setLastWeekCallbackCustomers(int lastWeekCallbackCustomers) {
		this.lastWeekCallbackCustomers = lastWeekCallbackCustomers;
	}

	public int getCallbackCustomers() {
		return callbackCustomers;
	}

	public void setCallbackCustomers(int callbackCustomers) {
		this.callbackCustomers = callbackCustomers;
	}

	public int getUncallback3Customers() {
		return uncallback3Customers;
	}

	public void setUncallback3Customers(int uncallback3Customers) {
		this.uncallback3Customers = uncallback3Customers;
	}

	public int getUncallback7Customers() {
		return uncallback7Customers;
	}

	public void setUncallback7Customers(int uncallback7Customers) {
		this.uncallback7Customers = uncallback7Customers;
	}

	public int getUncallback15Customers() {
		return uncallback15Customers;
	}

	public void setUncallback15Customers(int uncallback15Customers) {
		this.uncallback15Customers = uncallback15Customers;
	}
}
