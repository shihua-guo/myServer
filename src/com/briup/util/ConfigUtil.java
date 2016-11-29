package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	static private Properties p;
	static {
		p = new Properties();
		InputStream in = null;
		in = ConfigUtil.class.getResourceAsStream("config.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static public String getConfigValue(String key){
		return p.getProperty(key);
	}
	public static void main(String[] args) {
		System.out.println(ConfigUtil.getConfigValue("root"));
	}
}
