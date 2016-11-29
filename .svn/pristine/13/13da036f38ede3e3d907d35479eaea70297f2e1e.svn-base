package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServletMappingUtil {
	static private Properties p;
	static{
		p  =new Properties();
		InputStream in = null;
		in = ServletMappingUtil.class.getResourceAsStream(
				"servlet_mapping.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static public boolean isContainKey(String key){
		return p.containsKey(key);
	}
	static public String getValue(String key){
		return p.getProperty(key);
	}
	public static void main(String[] args) {
		System.out.println(ServletMappingUtil.isContainKey("/Login"));
		System.out.println(ServletMappingUtil.getValue("/Login"));
	}
}
