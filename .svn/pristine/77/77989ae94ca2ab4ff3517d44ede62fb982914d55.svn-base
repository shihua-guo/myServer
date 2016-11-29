package com.briup.servlet;

import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;
import com.briup.http.Servlet;

public class HelloWorldServlet implements Servlet{

	@Override
	public void servive(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
//		res.printResponseContent(req.getRequestPath());
		try {
			res.getPrintWriter().write("i am hello servlet");
			res.getPrintWriter().flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
