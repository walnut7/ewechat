package com.kpleasing.ewechat.util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *  JsonHelper.java 2018-02-06 All rights reserved.
 * 
 * @author Howared huang
 */
public class JsonHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 561609003517307499L;


	/**
	 * Json数组转换成List
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> parseJSON2List(String json) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArray.iterator();
		while (it.hasNext()) {
			JSONObject j = it.next();
			list.add(parseJSON2Map(j.toString()));
		}
		return list;
	}

	
	/**
	 * Json数据转换成Map
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject objJson = JSONObject.fromObject(json);
		for (Object k : objJson.keySet()) {
			Object v = objJson.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject objJson2 = it.next();
					System.out.println(objJson2.toString());
					list.add(parseJSON2Map(objJson2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
}
