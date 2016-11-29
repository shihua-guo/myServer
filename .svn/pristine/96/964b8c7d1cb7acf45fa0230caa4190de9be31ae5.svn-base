package com.briup.httpImpl;

import com.briup.http.HttpAccessProcessor;
import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;
import com.briup.http.Servlet;
import com.briup.util.MIMEUtil;
import com.briup.util.ServletMappingUtil;

public class HttpAccessProcessorImpl implements HttpAccessProcessor{

	@Override
	public void processStaticResource(HttpRequest req, HttpResponse res) {
		System.out.println("now is static");
		String contentType = MIMEUtil.getContentType(getFileType(req.getRequestPath()));
		res.setStatusLine(200);//响应行
		System.out.println(contentType);
		res.setContentType(contentType, "UTF-8");
		
		res.printResponseContent(req.getRequestPath());
	}
	public String getFileType(String path){
		System.out.println(path);
		String[] dividePath = path.split("[.]");
		return dividePath[dividePath.length-1];
	}
	@Override
	public void processDynamicResource(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
		String key = req.getRequestPath();
		String className = ServletMappingUtil.getValue(key);
		try {
			Object obj = Class.forName(className).newInstance();
			//处理响应行 响应头 
			res.setStatusLine(200);//响应行
			res.setContentType("text/html", "UTF-8");
			
			if(obj instanceof Servlet){
				System.out.println("调用请求类的service方法");
				((Servlet) obj).servive(req, res);
			}else{
				System.out.println("当前请求类没有实现Servlet接口");
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendError(int errorCode, HttpRequest req, HttpResponse res) {
		res.setStatusLine(404);//响应行
		res.setContentType("text/html", "UTF-8");
		res.printResponseContent("404.html");
	}

}
