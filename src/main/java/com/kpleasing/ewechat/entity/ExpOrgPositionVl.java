package com.kpleasing.ewechat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the EXP_ORG_POSITION_VL database table.
 * 
 */
public class ExpOrgPositionVl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal companyId;
	private BigDecimal createdBy;
	private Date creationDate;
	private String description;
	private BigDecimal employeeJobId;
	private String enabledFlag;
	private Date lastUpdateDate;
	private BigDecimal lastUpdatedBy;
	private BigDecimal parentPositionId;
	private String positionCode;
	private BigDecimal positionId;
	private String positionType;
	private BigDecimal unitId;

	public ExpOrgPositionVl() {
	}

	public BigDecimal getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
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

	public BigDecimal getEmployeeJobId() {
		return this.employeeJobId;
	}

	public void setEmployeeJobId(BigDecimal employeeJobId) {
		this.employeeJobId = employeeJobId;
	}

	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
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

	public BigDecimal getParentPositionId() {
		return this.parentPositionId;
	}

	public void setParentPositionId(BigDecimal parentPositionId) {
		this.parentPositionId = parentPositionId;
	}

	public String getPositionCode() {
		return this.positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public BigDecimal getPositionId() {
		return this.positionId;
	}

	public void setPositionId(BigDecimal positionId) {
		this.positionId = positionId;
	}

	public String getPositionType() {
		return this.positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public BigDecimal getUnitId() {
		return this.unitId;
	}

	public void setUnitId(BigDecimal unitId) {
		this.unitId = unitId;
	}
}