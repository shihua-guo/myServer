package com.briup.httpImpl;

import java.net.Socket;

import com.briup.http.HttpAccessProcessor;
import com.briup.http.HttpCreator;
import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;

public class HttpCreatorImpl implements HttpCreator{
	private HttpRequest req;
	private HttpResponse res;
	private HttpAccessProcessor proc;
	public HttpCreatorImpl(){}
	public HttpCreatorImpl(Socket socket){
		this.req  = new HttpRequestImpl4(socket);
		this.res  = new HttpResponseImpl2(socket);
		this.proc = new HttpAccessProcessorImpl();
	}
	@Override
	public HttpRequest getHttpRequest() {
		// TODO Auto-generated method stub
		return this.req;
	}

	@Override
	public HttpResponse getHttpResponse() {
		// TODO Auto-generated method stub
		return this.res;
	}

	@Override
	public HttpAccessProcessor getHttpAccessProcessor() {
		// TODO Auto-generated method stub
		return this.proc;
	}

}
