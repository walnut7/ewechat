package com.kpleasing.ewechat.mongo.dao.impl;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.kpleasing.ewechat.mongo.collections.CustomerDetail;
import com.kpleasing.ewechat.mongo.dao.CustomerDetailDao;


@Repository("CustomerDetailDao")
public class CustomerDetailDaoImpl extends AbstractBaseMongoTemplete implements CustomerDetailDao {

	@Override
	public void createCustomerDetailReport(CustomerDetail custDetail) {
		Criteria criteria = Criteria.where("report_date").is(custDetail.getReport_date()).and("sales_id").is(custDetail.getSales_id());
		Query query = new Query(criteria);
		mongoTemplate.findAndRemove(query, CustomerDetail.class, "report_customer_detail");
		mongoTemplate.save(custDetail);
	}

	
	@Override
	public CustomerDetail findCustomersBySalesAndDate(String searchDate, String salesID) {
		Criteria criteria = Criteria.where("report_date").is(searchDate).and("sales_id").is(salesID);
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, CustomerDetail.class, "report_customer_detail");
	}
  
}
