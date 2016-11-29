package com.briup.httpImpl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.briup.http.HttpRequest;
import com.briup.server.ServerTest2;

public class HttpRequestImpl 
implements HttpRequest{
	ServerTest2 st2 = new ServerTest2();
	public HttpRequestImpl(){}
	public HttpRequestImpl(ServerTest2 st2){
		this.st2 = st2;
	}
	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return st2.getProtocol();
	}

	@Override
	public String getRequestMethod() {
		// TODO Auto-generated method stub
		return st2.getRequestMethod();
	}

	@Override
	public String getRequestPath() {
		// TODO Auto-generated method stub
		return st2.getRequestPath();
	}

	@Override
	public Map<String, String> getRequestHeader() {
		// TODO Auto-generated method stub
		return st2.getReqMap();
	}

	@Override
	public String getParameter(String parameterName) {
		String value = null;
		for(Entry<String,String> str:st2.getParamMap().entrySet()){
			if(parameterName.equals(value = str.getValue())){
				return value;
			}
		}
		System.out.println("no this paramter");
		return null;
	}

	@Override
	public boolean isStaticResource() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDynamicResource() {
		String[] path = st2.getRequestPath().split("/");
		String reqPath = "";
		for(int i = 2;i<path.length;i++){
			reqPath=reqPath+"/"+path[i];
		}
		System.out.println(reqPath);
		Properties p = null;
		try {
			p = new Properties();
			p.load(new FileReader("doc/pro.properties"));
			String classStr = null;
			if((classStr = p.getProperty(reqPath))!=null){
				Class<?> c = Class.forName(classStr);
				//new the instance
//				Constructor<?> con = c.getConstructor();
				return true;
//				Object stu = con.newInstance();
			}
			System.out.println("我是标志");
			System.out.println("我是classStr："+classStr);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			System.out.println("不是动态方法");
			return false;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isNullRequest() {
		// TODO Auto-generated method stub
		return false;
	}

}
