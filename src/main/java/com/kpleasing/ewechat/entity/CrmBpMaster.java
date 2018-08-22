package com.kpleasing.ewechat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CRM_BP_MASTER database table.
 * 
 */
public class CrmBpMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long bpRecordId;
	private String cellPhone1;
	private String cellPhone2;
	private BigDecimal createdBy;
	private Date creationDate;
	private String customerSource;
	private String description;
	private String failureReason;
	private String finalResult;
	private String idCardNo;
	private String intentionLevel;
	private Date lastUpdateDate;
	private BigDecimal lastUpdatedBy;
	private String name;
	private BigDecimal ownerUserId;
	private String planDescription;

	public CrmBpMaster() {
	}

	public Long getBpRecordId() {
		return this.bpRecordId;
	}

	public void setBpRecordId(Long bpRecordId) {
		this.bpRecordId = bpRecordId;
	}

	public String getCellPhone1() {
		return this.cellPhone1;
	}

	public void setCellPhone1(String cellPhone1) {
		this.cellPhone1 = cellPhone1;
	}

	public String getCellPhone2() {
		return this.cellPhone2;
	}

	public void setCellPhone2(String cellPhone2) {
		this.cellPhone2 = cellPhone2;
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

	public String getCustomerSource() {
		return this.customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFailureReason() {
		return this.failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public String getFinalResult() {
		return this.finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	public String getIdCardNo() {
		return this.idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getIntentionLevel() {
		return this.intentionLevel;
	}

	public void setIntentionLevel(String intentionLevel) {
		this.intentionLevel = intentionLevel;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOwnerUserId() {
		return this.ownerUserId;
	}

	public void setOwnerUserId(BigDecimal ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public String getPlanDescription() {
		return this.planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}
}