package com.kpleasing.ewechat.mongo.collections;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="report_buss_crm")
public class KPBusinessReport {
	
	private String report_date;
	private List<BranchCompany> branchCompany;


	public String getReport_date() {
		return report_date;
	}

	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}

	public List<BranchCompany> getBranchCompany() {
		return branchCompany;
	}

	public void setBranchCompany(List<BranchCompany> branchCompany) {
		this.branchCompany = branchCompany;
	}
}
