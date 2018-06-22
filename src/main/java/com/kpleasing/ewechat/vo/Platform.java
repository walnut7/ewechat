package com.kpleasing.ewechat.vo;

import java.util.List;

public class Platform {
	private String platformName;    // 平台名称
	private String authCounts;      // 新审批通过
	private String rentCounts;      // 已放款起租
	private String purcCounts;      // 采购中车型
	private String coopCounts;      // 已合作车型
	private List<Merchant> merchants;
	
	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getAuthCounts() {
		return authCounts;
	}

	public void setAuthCounts(String authCounts) {
		this.authCounts = authCounts;
	}

	public String getRentCounts() {
		return rentCounts;
	}

	public void setRentCounts(String rentCounts) {
		this.rentCounts = rentCounts;
	}

	public String getPurcCounts() {
		return purcCounts;
	}

	public void setPurcCounts(String purcCounts) {
		this.purcCounts = purcCounts;
	}

	public String getCoopCounts() {
		return coopCounts;
	}

	public void setCoopCounts(String coopCounts) {
		this.coopCounts = coopCounts;
	}

	public List<Merchant> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants;
	}
}
