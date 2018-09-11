package com.kpleasing.ewechat.dao;

import java.util.List;

import com.kpleasing.ewechat.mongo.collections.BusinessMember;
import com.kpleasing.ewechat.mongo.collections.BusinessTeam;
import com.kpleasing.ewechat.mongo.collections.CustomerInfo;

public interface CrmBpMasterDao {
	
	
	/**
	 * 跟进客户数
	 * @param userId
	 * @return
	 */
	public int getAllCrmBpMasterCount(String userId);
	
	
	/**
	 * 跟进客户清单
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getAllCrmBpMasterDetailList(String userId);
	
	
	/**
	 * 上周新等级客户数
	 * @param userId
	 * @return
	 */
	public int getLastWeekCrmBpMasterCount(String userId) ; 
	
	
	/**
	 * 上周新等级客户清单
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getLastWeekCrmBpMasterDetailList(String userId);
	
	
	/**
	 * 上周起租合同数
	 * @param userId
	 * @return
	 */
	public int getLaskWeekRentCrmBpMasterCount(String userId);
	
	
	/**
	 * 上周起租合同清单
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getLaskWeekRentCrmBpMasterDetailList(String userId);
	
	
	// A类客户数
	public int getTypeACrmBpMasterCount(String userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getCrmABpMasterDetailList(String userId);
	
	
	// B类客户数
	public int getTypeBCrmBpMasterCount(String userId);
	
	
	/**
	 * B类客户清单
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getCrmBBpMasterDetailList(String userId);
	
	
	
	// 上周回访客户数
	public int getLastWeekCallBackCrmBpMasterCount(String userId);
	
	
	/**
	 * 上周回访客户清单
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getLaskWeekCallbackCrmBpMasterDetailList(String userId);
	
	
	// 总回访次数
	public int getAllCallBackCrmBpMasterCount(String userId);
	
	
	// 未及时回访AB类客户数 
	public int getUnCallBackABCrmBpMasterCount(String userId);
	
	
	/**
	 * 未及时回访AB类客户清单
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getUncallback3CrmBpMasterDetailList(String userId);
	
	
	// 超一周未回访客户数 
	public int getLastWeekUnCallBackCrmBpMasterCount(String userId);
	
	
	/**
	 * 超一周未回访客户清单
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getUncallback7CrmBpMasterDetailList(String userId);

	
	
	// 应强制转交或放弃客户数 
	public int getTransCrmBpMasterCount(String userId);
	
	
	
	/**
	 * 应强制转交或放弃客户清单
	 * @param userId
	 * @return
	 */
	public List<CustomerInfo> getUncallback15CrmBpMasterDetailList(String userId);


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


	
	/**
	 * 
	 * @param teamId
	 * @return
	 */
	public String findBusinessTeamLeaderByPositionId(String teamId);
}
