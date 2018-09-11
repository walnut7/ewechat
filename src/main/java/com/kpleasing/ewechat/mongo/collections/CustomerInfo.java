package com.kpleasing.ewechat.mongo.collections;

public class CustomerInfo {
	private String cust_name;
	private String phone1;
	private String phone2;
	private String cert_code;
	private String level;
	private String source;
	private String callback_time;
	private String car_model;
	private String leasing_start;
	
	public String getCust_name() {
		return cust_name;
	}
	
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getCert_code() {
		return cert_code;
	}

	public void setCert_code(String cert_code) {
		this.cert_code = cert_code;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCallback_time() {
		return callback_time;
	}

	public void setCallback_time(String callback_time) {
		this.callback_time = callback_time;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public String getLeasing_start() {
		return leasing_start;
	}

	public void setLeasing_start(String leasing_start) {
		this.leasing_start = leasing_start;
	}
}
