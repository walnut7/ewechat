package com.kpleasing.ewechat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CRM_BP_TRANSFER_HIS database table.
 * 
 */
public class CrmBpTransferHi implements Serializable {
	private static final long serialVersionUID = 1L;

	private long hisRecordId;
	private BigDecimal bpRecordId;
	private BigDecimal createdBy;
	private Date creationDate;
	private String description;
	private BigDecimal fromUserId;
	private Date lastUpdateDate;
	private BigDecimal lastUpdatedBy;
	private BigDecimal toUserId;
	private Date transferDate;

	public CrmBpTransferHi() {
	}

	public long getHisRecordId() {
		return this.hisRecordId;
	}

	public void setHisRecordId(long hisRecordId) {
		this.hisRecordId = hisRecordId;
	}

	public BigDecimal getBpRecordId() {
		return this.bpRecordId;
	}

	public void setBpRecordId(BigDecimal bpRecordId) {
		this.bpRecordId = bpRecordId;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getFromUserId() {
		return this.fromUserId;
	}

	public void setFromUserId(BigDecimal fromUserId) {
		this.fromUserId = fromUserId;
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

	public BigDecimal getToUserId() {
		return this.toUserId;
	}

	public void setToUserId(BigDecimal toUserId) {
		this.toUserId = toUserId;
	}

	public Date getTransferDate() {
		return this.transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

}