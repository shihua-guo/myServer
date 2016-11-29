package com.briup.http;

public interface HttpServlet {
	void doPost(HttpRequest request,HttpResponse response);
	void doGet(HttpRequest request,HttpResponse response);
}
