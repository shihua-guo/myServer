package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorPageUtil {
	static private Properties p;
	static{
		p = new Properties();
		InputStream in = null;
		in = ErrorPageUtil.class.getResourceAsStream("error_page.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public String getErrorPage(String errorStr){
		return p.getProperty(errorStr);
	}
	static public String getErrorPage(int errorCode){
		return p.getProperty(errorCode+"");
	}
}
