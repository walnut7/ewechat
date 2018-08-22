package com.kpleasing.ewechat.mongo.collections;

import java.util.List;

public class BranchCompany {
	
	private String branchName;
	private List<BusinessTeam> businessTeam;

	public String getBranchName() {
		return branchName;
	}
	
	
	
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<BusinessTeam> getBusinessTeam() {
		return businessTeam;
	}

	public void setBusinessTeam(List<BusinessTeam> businessTeam) {
		this.businessTeam = businessTeam;
	}
}
