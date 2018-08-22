package com.kpleasing.ewechat.mongo.collections;

import java.util.List;

public class BusinessTeam {
	
	private String teamName;
	private String teamId;
	private Integer memberNum;
	private List<BusinessMember> businessMember;
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public List<BusinessMember> getBusinessMember() {
		return businessMember;
	}

	public void setBusinessMember(List<BusinessMember> businessMember) {
		this.businessMember = businessMember;
	}

}
