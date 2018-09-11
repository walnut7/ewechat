package com.kpleasing.ewechat.mongo.collections;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="report_business_parameter")
public class ToUserInfo {
	private String sendUrl;
	private String msgIconUrl;
	private String toUser;
	private String toParty;
	private String toTag;
	private String title;
	private String desc;
	private String branchName;
	private String teamId;
	private String salesId;
	

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}

	public String getMsgIconUrl() {
		return msgIconUrl;
	}

	public void setMsgIconUrl(String msgIconUrl) {
		this.msgIconUrl = msgIconUrl;
	}

	public String getToUser() {
		return (null==toUser)?"":toUser;
	}
	
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getToParty() {
		return (null==toParty)?"":toParty;
	}

	public void setToParty(String toParty) {
		this.toParty = toParty;
	}

	public String getToTag() {
		return (null==toTag)?"":toTag;
	}

	public void setToTag(String toTag) {
		this.toTag = toTag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return (null==desc)?"":desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
}
