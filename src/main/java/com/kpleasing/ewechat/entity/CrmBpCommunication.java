package com.kpleasing.ewechat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CRM_BP_COMMUNICATION database table.
 * 
 */
public class CrmBpCommunication implements Serializable {
	private static final long serialVersionUID = 1L;

	private long communRecordId;
	private Date bookDate;
	private BigDecimal bpRecordId;
	private String communicationContent;
	private Date communicationDate;
	private String communicationMethod;
	private BigDecimal createdBy;
	private Date creationDate;
	private Date lastUpdateDate;
	private BigDecimal lastUpdatedBy;
	
	public CrmBpCommunication() {
	}

	public long getCommunRecordId() {
		return this.communRecordId;
	}

	public void setCommunRecordId(long communRecordId) {
		this.communRecordId = communRecordId;
	}

	public Date getBookDate() {
		return this.bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public BigDecimal getBpRecordId() {
		return this.bpRecordId;
	}

	public void setBpRecordId(BigDecimal bpRecordId) {
		this.bpRecordId = bpRecordId;
	}

	public String getCommunicationContent() {
		return this.communicationContent;
	}

	public void setCommunicationContent(String communicationContent) {
		this.communicationContent = communicationContent;
	}

	public Date getCommunicationDate() {
		return this.communicationDate;
	}

	public void setCommunicationDate(Date communicationDate) {
		this.communicationDate = communicationDate;
	}

	public String getCommunicationMethod() {
		return this.communicationMethod;
	}

	public void setCommunicationMethod(String communicationMethod) {
		this.communicationMethod = communicationMethod;
	}

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public BigDecimal getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}