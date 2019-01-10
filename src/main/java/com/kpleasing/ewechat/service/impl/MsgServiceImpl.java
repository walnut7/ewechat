package com.kpleasing.ewechat.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpleasing.ewechat.mongo.dao.ToUserInfoDao;
import com.kpleasing.ewechat.service.MsgService;


@Service("MsgService")
public class MsgServiceImpl implements MsgService {
	
	private static Logger logger = Logger.getLogger(MsgServiceImpl.class);

	@Autowired
	ToUserInfoDao toUserInfoDao;
}
