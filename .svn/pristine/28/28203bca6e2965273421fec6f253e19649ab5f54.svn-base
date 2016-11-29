package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MIMEUtil {
	static private Properties p;
	static {
		p = new Properties();
		InputStream in = null;
		in = MIMEUtil.class.getResourceAsStream("mime.properties");
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

	static public String getContentType(String contentType) {
		return p.getProperty(contentType);
	}
	public static void main(String[] args) {
		System.out.println(MIMEUtil.getContentType("html"));
	}
}
