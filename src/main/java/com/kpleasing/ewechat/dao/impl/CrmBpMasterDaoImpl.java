package com.kpleasing.ewechat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kpleasing.ewechat.dao.CrmBpMasterDao;
import com.kpleasing.ewechat.mongo.collections.BusinessMember;
import com.kpleasing.ewechat.mongo.collections.BusinessTeam;


@Repository("CrmBpMasterDao")
public class CrmBpMasterDaoImpl implements CrmBpMasterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<BusinessTeam> findBusinessTeamByPositionId(String teamId) {
		String sql = "select * from exp_org_position_vl where parent_position_id = 201 and enabled_flag = 'Y' ";
		return jdbcTemplate.query(sql, new BusinessTeamRowMapper());
	}
	
	public class BusinessTeamRowMapper implements RowMapper<BusinessTeam> {

		@Override
		public BusinessTeam mapRow(ResultSet rs, int rowNum) throws SQLException {
			BusinessTeam businessTeam = new BusinessTeam();
			businessTeam.setTeamName(rs.getString("DESCRIPTION"));
			businessTeam.setTeamId(rs.getString("POSITION_ID"));
			return businessTeam;
		}
    }
	
	
	@Override
	public List<BusinessMember> findBusinessMemeberByPositionId(String teamId) {
		// String sql = "select * from  exp_emp_user_e_v where position_id in  (select position_id from exp_org_position_vl where parent_position_id= ? or position_id= ?) ";
		String sql = "select * from  exp_emp_user_e_v where position_id in (select position_id from exp_org_position_vl where parent_position_id= ? ) ";
		return jdbcTemplate.query(sql, new java.lang.Object[]{teamId}, new BusinessMemberRowMapper());
	}
	
	
	public class BusinessMemberRowMapper implements RowMapper<BusinessMember> {

		@Override
		public BusinessMember mapRow(ResultSet rs, int rowNum) throws SQLException {
			BusinessMember businessMember = new BusinessMember();
			businessMember.setUserId(rs.getString("USER_ID"));
			businessMember.setUserName(rs.getString("EMPLOYEE_NAME"));
			return businessMember;
		}
    }
	
	
	@Override
	public int getAllCrmBpMasterCount(String userId) {
		String sql = "select count(*) from CRM_BP_MASTER where owner_user_id = ? and (final_result <> 'FAILED' or final_result is null) ";
		return jdbcTemplate.queryForObject(sql,new java.lang.Object[]{userId}, Integer.class);
	}
	

	@Override
	public int getLastWeekCrmBpMasterCount(String userId) {
		String sql = "select count(*) from CRM_BP_MASTER where owner_user_id = ? and (final_result = 'SUCCESS' or final_result is null) and creation_date > (sysdate - interval '7' day)";
		return jdbcTemplate.queryForObject(sql,new java.lang.Object[]{userId}, Integer.class);
	}


	@Override
	public int getLaskWeekRentCrmBpMasterCount(String userId) {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("select count(*) from con_contract c, hls_bp_master m, CRM_BP_MASTER n where c.bp_id_tenant = m.bp_id ")
				 .append("and (m.cell_phone = n.cell_phone1 or m.cell_phone = n.cell_phone2) ")
				 .append("and c.contract_status = 'INCEPT'")
				 .append("and c.data_class='NORMAL'")
				 .append("and n.owner_user_id = ? ")
				 .append("and c.lease_start_date > (sysdate - interval '7' day)");
		   
		return jdbcTemplate.queryForObject(sqlString.toString(), new java.lang.Object[]{userId}, Integer.class);
	}


	@Override
	public int getTypeACrmBpMasterCount(String userId) {
		String sql = "select count(*) from CRM_BP_MASTER where owner_user_id = ? and intention_level = 'A'";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}


	@Override
	public int getTypeBCrmBpMasterCount(String userId) {
		String sql = "select count(*) from CRM_BP_MASTER where owner_user_id = ? and intention_level = 'B'";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}


	@Override
	public int getLastWeekCallBackCrmBpMasterCount(String userId) {
		String sql = "select count(*) from (select distinct bp_record_id from CRM_BP_COMMUNICATION where created_by = ? and creation_date > (sysdate - interval '7' day))";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}


	@Override
	public int getAllCallBackCrmBpMasterCount(String userId) {
		String sql = "select count(*) from (select distinct bp_record_id from CRM_BP_COMMUNICATION where created_by = ?)";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}


	@Override
	public void getUnCallBackABCrmBpMasterCount() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getLastWeekUnCallBackCrmBpMasterCount() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getTransCrmBpMasterCount() {
		// TODO Auto-generated method stub
		
	}
}
