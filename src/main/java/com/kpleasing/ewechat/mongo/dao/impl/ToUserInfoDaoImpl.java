package com.kpleasing.ewechat.mongo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kpleasing.ewechat.mongo.collections.ToUserInfo;
import com.kpleasing.ewechat.mongo.dao.ToUserInfoDao;


@Repository("ToUserInfoDao")
public class ToUserInfoDaoImpl extends AbstractBaseMongoTemplete implements ToUserInfoDao {

	@Override
	public List<ToUserInfo> findAll() {
		return mongoTemplate.findAll(ToUserInfo.class, "report_business_parameter");
	}
}
