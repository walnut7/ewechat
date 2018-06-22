package com.kpleasing.ewechat.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigUtil {
	private static final String WECHAT_CONF_FILE = "/wechat.properties";
	private static ConfigUtil instance;
	private Properties property = new Properties();  

	public synchronized static ConfigUtil getInstance() {
		if (instance == null) {
			instance = new ConfigUtil();
		}
		return instance;
	}
	
	public ConfigUtil() {
		InputStream inStream = null;
		try {
			inStream = new BufferedInputStream(this.getClass().getResourceAsStream(WECHAT_CONF_FILE));
			property.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != inStream) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
	
	public String getPropertyParam(String key) {
		return property.getProperty(key);
	}
	
	public void setPropertyParam(String key, String value) {
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(WECHAT_CONF_FILE);
			
			property.setProperty(key, value);
			property.store(outStream, "update access_token");  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != outStream) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
