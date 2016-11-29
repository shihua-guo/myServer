package com.briup.http;

public interface HttpAccessProcessor {
	//处理静态资源请求的方法
	public void processStaticResource(HttpRequest req,HttpResponse res);
	//处理动态资源请求的方法
	public void processDynamicResource(HttpRequest req,HttpResponse res);
	//返回错误页面
	public void sendError(int errorCode,HttpRequest req,HttpResponse res);
}
