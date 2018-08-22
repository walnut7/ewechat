package com.kpleasing.ewechat.entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the EXP_EMP_USER_E_V database table.
 * 
 */
public class ExpEmpUserEV implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal companyId;
	private String employeeCode;
	private BigDecimal employeeId;
	private String employeeName;
	private BigDecimal positionId;
	private String positionType;
	private String userDesc;
	private BigDecimal userId;
	private String userName;

	public ExpEmpUserEV() {
	}

	public BigDecimal getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}

	public String getEmployeeCode() {
		return this.employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public BigDecimal getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(BigDecimal employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public String getUserDesc() {
		return this.userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}