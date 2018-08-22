package com.kpleasing.ewechat.dao;

import java.util.List;

import com.kpleasing.ewechat.mongo.collections.BusinessMember;
import com.kpleasing.ewechat.mongo.collections.BusinessTeam;

public interface CrmBpMasterDao {
	
	
	// 跟进客户数
	public int getAllCrmBpMasterCount(String userId);
	
	
	// 上周新登记客户数
	public int getLastWeekCrmBpMasterCount(String userId) ; 
	
	
	// 上周起租合同数
	public int getLaskWeekRentCrmBpMasterCount(String userId);
	
	
	// A类客户数
	public int getTypeACrmBpMasterCount(String userId);
	
	
	// B类客户数
	public int getTypeBCrmBpMasterCount(String userId);
	
	
	// 上周回访客户数
	public int getLastWeekCallBackCrmBpMasterCount(String userId);
	
	
	// 总回访次数
	public int getAllCallBackCrmBpMasterCount(String userId);
	
	
	// 未及时回访AB类客户数 
	public void getUnCallBackABCrmBpMasterCount();
	
	
	// 超一周未回访客户数 
	public void getLastWeekUnCallBackCrmBpMasterCount();
	
	
	// 应强制转交或放弃客户数 
	public void getTransCrmBpMasterCount();


	/**
	 * 
	 * @param teamId
	 * @return
	 */
	public List<BusinessTeam> findBusinessTeamByPositionId(String parentTeamId);


	
	/**
	 * 
	 * @param teamId
	 * @return
	 */
	public List<BusinessMember> findBusinessMemeberByPositionId(String teamId);
}
