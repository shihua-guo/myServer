package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StatusCodeUtil {
	static private Properties p;
	static {
		p = new Properties();
		InputStream in = null;
		in = StatusCodeUtil.class.getResourceAsStream("status_code.properties");
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
	static public String getStatusMsg(String statusStr){
		return p.getProperty(statusStr);
	}
	//为什么要调用
	static public String getStatusMsg(int statusCode){
		return p.getProperty(statusCode+"");
	}
	public static void main(String[] args) {
		System.out.println(StatusCodeUtil.getStatusMsg("404"));
	}
}
