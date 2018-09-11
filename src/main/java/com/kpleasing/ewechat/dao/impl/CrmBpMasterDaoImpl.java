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
import com.kpleasing.ewechat.mongo.collections.CustomerInfo;


@Repository("CrmBpMasterDao")
public class CrmBpMasterDaoImpl implements CrmBpMasterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<BusinessTeam> findBusinessTeamByPositionId(String teamId) {
		String sql = "select * from exp_org_position_vl where parent_position_id = ? and enabled_flag = 'Y' ";
		return jdbcTemplate.query(sql, new java.lang.Object[]{teamId}, new BusinessTeamRowMapper());
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
	public String findBusinessTeamLeaderByPositionId(String teamId) {
		try {
			String sql = "select USER_DESC from  exp_emp_user_e_v where assign_enabled_flag='Y' and position_id = ? and rownum <=1 ";
			return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{teamId}, String.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@Override
	public List<BusinessMember> findBusinessMemeberByPositionId(String teamId) {
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
		String sql="select count(*) from  \r\n" + 
				"       (select cbma.owner_user_id, cbma.bp_record_id, cbma.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"               and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,           \r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmb,\r\n" + 
				"              (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"             and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,           \r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmc,\r\n" + 
				"              (select m.id_card_no, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"              and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc\r\n" + 
				"        where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"          and ca.bp_record_id = cc.bp_record_id          \r\n" + 
				"          and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"          and ca.owner_user_id = ? ";
		return jdbcTemplate.queryForObject(sql,new java.lang.Object[]{userId}, Integer.class);
	}
	
	
	@Override
	public List<CustomerInfo> getAllCrmBpMasterDetailList(String userId) {
		String sql = "select ca.name, ca.cell_phone1, ca.cell_phone2, ca.id_card_no, ca.intention_level, vv.code_value_name from\r\n" + 
				"        (select cbma.*, bpc.contract_status\r\n" + 
				"                from CRM_BP_MASTER cbma,\r\n" + 
				"                      (select m.cell_phone, c.contract_status\r\n" + 
				"                       from hls_bp_master m, con_contract c\r\n" + 
				"                      where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"              where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"                   and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,           \r\n" + 
				"            (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"             from CRM_BP_MASTER cbmb,\r\n" + 
				"                   (select m.cell_phone, c.contract_status\r\n" + 
				"                      from hls_bp_master m, con_contract c\r\n" + 
				"                     where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"            where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"                 and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,        \r\n" + 
				"             (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"               from CRM_BP_MASTER cbmc,\r\n" + 
				"                   (select m.id_card_no, c.contract_status\r\n" + 
				"                       from hls_bp_master m, con_contract c\r\n" + 
				"                       where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"               where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"                    and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc,\r\n" + 
				"              (select v.code_value,v.code_value_name from sys_code_values_v v where v.code='CRM100_CUSTOMER_SOURCE') vv\r\n" + 
				"             where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"               and ca.bp_record_id = cc.bp_record_id\r\n" + 
				"               and ca.customer_source = vv.code_value(+)\r\n" + 
				"               and (ca.final_result = 'SUCCESS' or ca.final_result is null)            \r\n" + 
				"               and ca.owner_user_id = ? ";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new FallowCustomerDetailRowMapper());
	}
	
	
	public class FallowCustomerDetailRowMapper implements RowMapper<CustomerInfo> {
		@Override
		public CustomerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerInfo customer = new CustomerInfo();
			customer.setCust_name(rs.getString("name"));
			customer.setPhone1(rs.getString("cell_phone1"));
			customer.setPhone2(rs.getString("cell_phone2"));
			customer.setCert_code(rs.getString("id_card_no"));
			customer.setLevel(rs.getString("intention_level"));
			customer.setSource(rs.getString("code_value_name"));
			return customer;
		}
    }
	

	@Override
	public int getLastWeekCrmBpMasterCount(String userId) {
		String sql = "select count(*) from CRM_BP_MASTER where owner_user_id = ? and (final_result = 'SUCCESS' or final_result is null) and creation_date > (sysdate - interval '7' day)";
		return jdbcTemplate.queryForObject(sql,new java.lang.Object[]{userId}, Integer.class);
	}
	
	
	@Override
	public List<CustomerInfo> getLastWeekCrmBpMasterDetailList(String userId) {
		String sql = "select cbm.name, cbm.cell_phone1, cbm.cell_phone2, cbm.id_card_no, cbm.intention_level, vv.code_value_name\r\n" + 
				"  from CRM_BP_MASTER cbm,\r\n" + 
				"    (select v.code_value,v.code_value_name from sys_code_values_v v where v.code='CRM100_CUSTOMER_SOURCE') vv\r\n" + 
				" where owner_user_id = ? \r\n" + 
				"   and cbm.customer_source = vv.code_value(+)\r\n" + 
				"   and (final_result = 'SUCCESS' or final_result is null)\r\n" + 
				"   and creation_date > (sysdate - interval '7' day) ";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new LastWeekAddCustomerDetailRowMapper());
	}
	
	
	public class LastWeekAddCustomerDetailRowMapper implements RowMapper<CustomerInfo> {
		@Override
		public CustomerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerInfo customer = new CustomerInfo();
			customer.setCust_name(rs.getString("name"));
			customer.setPhone1(rs.getString("cell_phone1"));
			customer.setPhone2(rs.getString("cell_phone2"));
			customer.setCert_code(rs.getString("id_card_no"));
			customer.setLevel(rs.getString("intention_level"));
			customer.setSource(rs.getString("code_value_name"));
			return customer;
		}
    }


	@Override
	public int getLaskWeekRentCrmBpMasterCount(String userId) {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("select count(*) from con_contract c, hls_bp_master m, CRM_BP_MASTER n where c.bp_id_tenant = m.bp_id ")
				 .append("and (m.cell_phone = n.cell_phone1 or m.cell_phone = n.cell_phone2) ")
				 .append("and c.contract_status = 'INCEPT' ")
				 .append("and c.data_class='NORMAL' ")
				 .append("and n.owner_user_id = ? ")
				 .append("and c.lease_start_date > (sysdate - interval '7' day) ");
		   
		return jdbcTemplate.queryForObject(sqlString.toString(), new java.lang.Object[]{userId}, Integer.class);
	}
	
	
	@Override
	public List<CustomerInfo> getLaskWeekRentCrmBpMasterDetailList(String userId) {
		String sql = "select cbm.name, cm.description, to_char(cm.lease_start_date, 'yyyy-mm-dd') as lease_start_date, vv.code_value_name  \r\n" + 
				"  from hls_bp_master bm,\r\n" + 
				"       (select m.description,\r\n" + 
				"               c.lease_start_date,\r\n" + 
				"               c.contract_status,\r\n" + 
				"               c.data_class,\r\n" + 
				"               c.bp_id_tenant\r\n" + 
				"          from tm_lease_item i, con_contract c, hls_car_model m\r\n" + 
				"         where c.contract_number = i.applyno(+)\r\n" + 
				"           and i.model_id = m.model_id(+)\r\n" + 
				"           and c.contract_status = 'INCEPT'\r\n" + 
				"           and c.data_class = 'NORMAL') cm,\r\n" + 
				"       CRM_BP_MASTER cbm,\r\n" + 
				"     (select v.code_value,v.code_value_name from sys_code_values_v v where v.code='CRM100_CUSTOMER_SOURCE') vv\r\n" + 
				"   where cm.bp_id_tenant = bm.bp_id\r\n" + 
				"      and cbm.customer_source = vv.code_value(+)\r\n" + 
				"      and (bm.cell_phone = cbm.cell_phone1 or bm.cell_phone = cbm.cell_phone2)\r\n" + 
				"      and cbm.owner_user_id = ?\r\n" + 
				"      and cm.lease_start_date > (sysdate - interval '7' day) ";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new LastWeekRentCustomerDetailRowMapper());
	}
	
	
	public class LastWeekRentCustomerDetailRowMapper implements RowMapper<CustomerInfo> {
		@Override
		public CustomerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerInfo customer = new CustomerInfo();
			customer.setCust_name(rs.getString("name"));
			customer.setCar_model(rs.getString("description"));
			customer.setLeasing_start(rs.getString("lease_start_date"));
			customer.setSource(rs.getString("code_value_name"));
			return customer;
		}
    }


	@Override
	public int getTypeACrmBpMasterCount(String userId) {
		String sql = "select count(*) from  \r\n" + 
				"       (select cbma.owner_user_id, cbma.intention_level, cbma.bp_record_id, cbma.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"               and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,           \r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmb,\r\n" + 
				"              (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"             and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,           \r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmc,\r\n" + 
				"              (select m.id_card_no, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"              and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc\r\n" + 
				"        where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"          and ca.bp_record_id = cc.bp_record_id          \r\n" + 
				"          and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"          and ca.intention_level = 'A' " +
				"          and ca.owner_user_id = ? ";
		// String sql = "select count(*) from CRM_BP_MASTER where owner_user_id = ? and intention_level = 'A'";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}
	
	
	@Override
	public List<CustomerInfo> getCrmABpMasterDetailList(String userId) {
		String sql = "select ca.name, ca.cell_phone1, ca.cell_phone2, ca.id_card_no, ca.intention_level, vv.code_value_name\r\n" + 
				"  from (select cbma.*,\r\n" + 
				"               bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+) and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,\r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmb,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,\r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmc,\r\n" + 
				"               (select m.id_card_no, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc,\r\n" + 
				"        (select v.code_value,v.code_value_name from sys_code_values_v v where v.code='CRM100_CUSTOMER_SOURCE') vv\r\n" + 
				" where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"   and ca.bp_record_id = cc.bp_record_id\r\n" + 
				"   and ca.customer_source = vv.code_value(+)\r\n" + 
				"   and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"   and ca.intention_level = 'A'\r\n" + 
				"   and ca.owner_user_id = ?";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new CustomerADetailRowMapper());
	}
	
	
	public class CustomerADetailRowMapper implements RowMapper<CustomerInfo> {
		@Override
		public CustomerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerInfo customer = new CustomerInfo();
			customer.setCust_name(rs.getString("name"));
			customer.setPhone1(rs.getString("cell_phone1"));
			customer.setPhone2(rs.getString("cell_phone2"));
			customer.setCert_code(rs.getString("id_card_no"));
			customer.setLevel(rs.getString("intention_level"));
			customer.setSource(rs.getString("code_value_name"));
			return customer;
		}
    }
	
	
	@Override
	public int getTypeBCrmBpMasterCount(String userId) {
		String sql = "select count(*) from  \r\n" + 
				"       (select cbma.owner_user_id, cbma.intention_level, cbma.bp_record_id, cbma.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"               and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,           \r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmb,\r\n" + 
				"              (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"             and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,           \r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmc,\r\n" + 
				"              (select m.id_card_no, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"              and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc\r\n" + 
				"        where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"          and ca.bp_record_id = cc.bp_record_id          \r\n" + 
				"          and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"          and ca.intention_level = 'B' " +
				"          and ca.owner_user_id = ? ";
		
		//String sql = "select count(*) from CRM_BP_MASTER where owner_user_id = ? and intention_level = 'B'";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}
	
	
	@Override
	public List<CustomerInfo> getCrmBBpMasterDetailList(String userId) {
		String sql = "select ca.name, ca.cell_phone1, ca.cell_phone2, ca.id_card_no, ca.intention_level, vv.code_value_name\r\n" + 
				"  from (select cbma.*,\r\n" + 
				"               bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+) and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,\r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmb,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,\r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmc,\r\n" + 
				"               (select m.id_card_no, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc,\r\n" + 
				"        (select v.code_value,v.code_value_name from sys_code_values_v v where v.code='CRM100_CUSTOMER_SOURCE') vv\r\n" + 
				" where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"   and ca.bp_record_id = cc.bp_record_id\r\n" + 
				"   and ca.customer_source = vv.code_value(+)\r\n" + 
				"   and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"   and ca.intention_level = 'B'\r\n" + 
				"   and ca.owner_user_id = ?";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new CustomerBDetailRowMapper());
	}
	
	public class CustomerBDetailRowMapper implements RowMapper<CustomerInfo> {
		@Override
		public CustomerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerInfo customer = new CustomerInfo();
			customer.setCust_name(rs.getString("name"));
			customer.setPhone1(rs.getString("cell_phone1"));
			customer.setPhone2(rs.getString("cell_phone2"));
			customer.setCert_code(rs.getString("id_card_no"));
			customer.setLevel(rs.getString("intention_level"));
			customer.setSource(rs.getString("code_value_name"));
			return customer;
		}
    }
	

	@Override
	public int getLastWeekCallBackCrmBpMasterCount(String userId) {
		String sql = "select count(*) from (select distinct bp_record_id from CRM_BP_COMMUNICATION where created_by = ? and creation_date > (sysdate - interval '7' day))";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}
	
	
	@Override
	public List<CustomerInfo> getLaskWeekCallbackCrmBpMasterDetailList(String userId) {
		String sql = "select ca.name, ca.cell_phone1, ca.cell_phone2, ca.id_card_no, ca.intention_level, to_char(cb.creation_date, 'yyyy-mm-dd hh24:mi:ss') as creation_date, vv.code_value_name\r\n" + 
				"     from CRM_BP_MASTER ca,          \r\n" + 
				"          (select distinct bp_record_id, creation_date\r\n" + 
				"             from CRM_BP_COMMUNICATION\r\n" + 
				"            where created_by = ?  and creation_date > (sysdate - interval '7' day)) cb,\r\n" + 
				"          (select v.code_value, v.code_value_name\r\n" + 
				"             from sys_code_values_v v\r\n" + 
				"            where v.code = 'CRM100_CUSTOMER_SOURCE') vv\r\n" + 
				"    where ca.bp_record_id = cb.bp_record_id and ca.customer_source = vv.code_value(+)";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new LastWeekCallbackCustomerDetailRowMapper());
	}
	
	
	public class LastWeekCallbackCustomerDetailRowMapper implements RowMapper<CustomerInfo> {
		@Override
		public CustomerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerInfo customer = new CustomerInfo();
			customer.setCust_name(rs.getString("name"));
			customer.setPhone1(rs.getString("cell_phone1"));
			customer.setPhone2(rs.getString("cell_phone2"));
			customer.setCert_code(rs.getString("id_card_no"));
			customer.setLevel(rs.getString("intention_level"));
			customer.setSource(rs.getString("code_value_name"));
			customer.setCallback_time(rs.getString("creation_date"));
			return customer;
		}
    }
	


	@Override
	public int getAllCallBackCrmBpMasterCount(String userId) {
		String sql = "select count(*) from CRM_BP_COMMUNICATION where created_by = ? and creation_date > (sysdate - interval '7' day) ";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}


	@Override
	public int getUnCallBackABCrmBpMasterCount(String userId) {
		String sql = "select count(*) from  \r\n" + 
				"       (select cbma.creation_date, cbma.owner_user_id, cbma.intention_level, cbma.bp_record_id, cbma.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"               and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,           \r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmb,\r\n" + 
				"              (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"             and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,           \r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmc,\r\n" + 
				"              (select m.id_card_no, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"              and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc\r\n" + 
				"        where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"          and ca.bp_record_id = cc.bp_record_id          \r\n" + 
				"          and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"          and (ca.intention_level = 'A' or ca.intention_level = 'B')\r\n" + 
				"          and ca.Creation_Date < (sysdate - interval '3' day)\r\n" + 
				"          and ca.owner_user_id = ? ";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}
	
	
	@Override
	public List<CustomerInfo> getUncallback3CrmBpMasterDetailList(String userId) {
		String sql = "select ca.name, ca.cell_phone1, ca.cell_phone2, ca.id_card_no, ca.intention_level, vv.code_value_name, to_char(cd.creation_date, 'yyyy-mm-dd hh24:mi:ss') as creation_date \r\n" + 
				"  from (select cbma.*,\r\n" + 
				"               bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,\r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmb,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,\r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmc,\r\n" + 
				"               (select m.id_card_no, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc,\r\n" + 
				"       (select v.code_value,v.code_value_name from sys_code_values_v v where v.code='CRM100_CUSTOMER_SOURCE') vv,\r\n" + 
				"       (select bp_record_id, max(creation_date) as creation_date from CRM_BP_COMMUNICATION group by bp_record_id, created_by) cd\r\n" + 
				" where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"   and ca.bp_record_id = cc.bp_record_id\r\n" + 
				"   and ca.bp_record_id = cd.bp_record_id(+)\r\n" + 
				"   and ca.customer_source = vv.code_value(+)  \r\n" + 
				"   and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"   and (ca.intention_level = 'A' or ca.intention_level = 'B')\r\n" + 
				"   and ca.Creation_Date < (sysdate - interval '3' day)\r\n" + 
				"   and ca.owner_user_id = ? ";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new UnCallbackCustomerDetailRowMapper());
	}
	
	
	public class UnCallbackCustomerDetailRowMapper implements RowMapper<CustomerInfo> {
		@Override
		public CustomerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerInfo customer = new CustomerInfo();
			customer.setCust_name(rs.getString("name"));
			customer.setPhone1(rs.getString("cell_phone1"));
			customer.setPhone2(rs.getString("cell_phone2"));
			customer.setCert_code(rs.getString("id_card_no"));
			customer.setLevel(rs.getString("intention_level"));
			customer.setSource(rs.getString("code_value_name"));
			customer.setCallback_time(rs.getString("creation_date"));
			return customer;
		}
    }
	
	


	@Override
	public int getLastWeekUnCallBackCrmBpMasterCount(String userId) {
		
		String sql = "select count(*) from  \r\n" + 
				"       (select cbma.creation_date, cbma.owner_user_id, cbma.intention_level, cbma.bp_record_id, cbma.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"               and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,           \r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmb,\r\n" + 
				"              (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"             and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,           \r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmc,\r\n" + 
				"              (select m.id_card_no, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"              and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc\r\n" + 
				"        where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"          and ca.bp_record_id = cc.bp_record_id          \r\n" + 
				"          and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"          and ca.Creation_Date < (sysdate - interval '7' day)\r\n" + 
				"          and ca.owner_user_id = ? ";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}
	
	
	@Override
	public List<CustomerInfo> getUncallback7CrmBpMasterDetailList(String userId) {
		String sql = "select ca.name, ca.cell_phone1, ca.cell_phone2, ca.id_card_no, ca.intention_level, vv.code_value_name, to_char(cd.creation_date, 'yyyy-mm-dd hh24:mi:ss') as creation_date \r\n" + 
				"  from (select cbma.*,\r\n" + 
				"               bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,\r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmb,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,\r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmc,\r\n" + 
				"               (select m.id_card_no, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc,\r\n" + 
				"       (select v.code_value,v.code_value_name from sys_code_values_v v where v.code='CRM100_CUSTOMER_SOURCE') vv,\r\n" + 
				"       (select bp_record_id, max(creation_date) as creation_date from CRM_BP_COMMUNICATION group by bp_record_id, created_by) cd\r\n" + 
				" where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"   and ca.bp_record_id = cc.bp_record_id\r\n" + 
				"   and ca.bp_record_id = cd.bp_record_id(+)\r\n" + 
				"   and ca.customer_source = vv.code_value(+)  \r\n" + 
				"   and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"   and ca.Creation_Date < (sysdate - interval '7' day)\r\n" + 
				"   and ca.owner_user_id = ? ";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new UnCallbackCustomerDetailRowMapper());
	}


	@Override
	public int getTransCrmBpMasterCount(String userId) {
		String sql = "select count(*) from  \r\n" + 
				"       (select cbma.creation_date, cbma.owner_user_id, cbma.intention_level, cbma.bp_record_id, cbma.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"               and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,           \r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmb,\r\n" + 
				"              (select m.cell_phone, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"             and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,           \r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"         from CRM_BP_MASTER cbmc,\r\n" + 
				"              (select m.id_card_no, c.contract_status\r\n" + 
				"                 from hls_bp_master m, con_contract c\r\n" + 
				"                where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"        where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"              and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc\r\n" + 
				"        where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"          and ca.bp_record_id = cc.bp_record_id          \r\n" + 
				"          and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"          and ca.Creation_Date < (sysdate - interval '15' day)\r\n" + 
				"          and ca.owner_user_id = ? ";
		return jdbcTemplate.queryForObject(sql, new java.lang.Object[]{userId}, Integer.class);
	}


	@Override
	public List<CustomerInfo> getUncallback15CrmBpMasterDetailList(String userId) {
		String sql = "select ca.name, ca.cell_phone1, ca.cell_phone2, ca.id_card_no, ca.intention_level, vv.code_value_name, to_char(cd.creation_date, 'yyyy-mm-dd hh24:mi:ss') as creation_date \r\n" + 
				"  from (select cbma.*,\r\n" + 
				"               bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbma,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbma.cell_phone1 = bpc.cell_phone(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) ca,\r\n" + 
				"       (select cbmb.bp_record_id, cbmb.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmb,\r\n" + 
				"               (select m.cell_phone, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmb.cell_phone2 = bpc.cell_phone(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cb,\r\n" + 
				"       (select cbmc.bp_record_id, cbmc.final_result, bpc.contract_status\r\n" + 
				"          from CRM_BP_MASTER cbmc,\r\n" + 
				"               (select m.id_card_no, c.contract_status from hls_bp_master m, con_contract c where m.bp_id = c.bp_id_tenant(+)) bpc\r\n" + 
				"         where cbmc.id_card_no = bpc.id_card_no(+)\r\n" + 
				"           and (bpc.contract_status <> 'INCEPT' or bpc.contract_status is null)) cc,\r\n" + 
				"       (select v.code_value,v.code_value_name from sys_code_values_v v where v.code='CRM100_CUSTOMER_SOURCE') vv,\r\n" + 
				"       (select bp_record_id, max(creation_date) as creation_date from CRM_BP_COMMUNICATION group by bp_record_id, created_by) cd\r\n" + 
				" where ca.bp_record_id = cb.bp_record_id\r\n" + 
				"   and ca.bp_record_id = cc.bp_record_id\r\n" + 
				"   and ca.bp_record_id = cd.bp_record_id(+)\r\n" + 
				"   and ca.customer_source = vv.code_value(+)  \r\n" + 
				"   and (ca.final_result = 'SUCCESS' or ca.final_result is null)\r\n" + 
				"   and ca.Creation_Date < (sysdate - interval '15' day)\r\n" + 
				"   and ca.owner_user_id = ? ";
		return jdbcTemplate.query(sql, new java.lang.Object[]{userId}, new UnCallbackCustomerDetailRowMapper());
	}
}
