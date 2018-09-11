package com.kpleasing.ewechat.mongo.dao;

import java.util.List;

import com.kpleasing.ewechat.mongo.collections.ToUserInfo;

public interface ToUserInfoDao {

	public List<ToUserInfo> findAll();

}
