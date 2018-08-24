package com.kpleasing.ewechat.mongo.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.kpleasing.ewechat.mongo.collections.KPBusinessReport;
import com.kpleasing.ewechat.mongo.dao.KPBusinessReportDao;


@Repository("KPBusinessReportDao")
public class KPBusinessReportDaoImpl extends AbstractBaseMongoTemplete implements KPBusinessReportDao {

	
	@Override
	public void createKPBusinessReport(KPBusinessReport businessReport) {
		Criteria criteria = Criteria.where("report_date").is(businessReport.getReport_date());
		Query query = new Query(criteria);
		mongoTemplate.findAndRemove(query, KPBusinessReport.class, "report_buss_crm");
		mongoTemplate.save(businessReport);
	}

	
	@Override
	public List<KPBusinessReport> findReportByDate(String date) throws Exception {
		Criteria criteria ;
		
		if(StringUtils.isNotBlank(date)) {
			criteria = Criteria.where("report_date").is(date);
		} else throw  new Exception("report date is null!");
		
		Query query = new Query();
		query.addCriteria(criteria).with(new Sort(Sort.Direction.DESC, "report_date"));
		return mongoTemplate.find(query, KPBusinessReport.class, "report_buss_crm");
	}
}
