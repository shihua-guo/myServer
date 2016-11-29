package com.briup.servlet;

import java.io.PrintWriter;

import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;
import com.briup.http.Servlet;
import com.briup.httpImpl.HttpResponseImpl2;

public class LoginServlet implements Servlet {

	@Override
	public void servive(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
		PrintWriter pw = null;
		try {
			pw = res.getPrintWriter();
			res.printResponseHeader();
			if ("tom".equals(req.getParameter("name"))) {
				pw.print("\r\n");
				pw.print("<html>");
				pw.print("<body>");
				pw.print("登陆成功");
				pw.print("</body>");
				pw.print("</html>");
				pw.flush();
			}
			else{
				pw.print("\r\n");
				pw.print("<html>");
				pw.print("<body>");
				pw.print("登陆失败");
				pw.print("</body>");
				pw.print("</html>");
				pw.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
