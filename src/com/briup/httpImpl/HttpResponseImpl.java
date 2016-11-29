package com.briup.httpImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.briup.http.HttpResponse;

public class HttpResponseImpl implements HttpResponse{
	private Socket socket;
	private OutputStream out;
	private PrintWriter pw;
	private String statusLine;
	private Map<String,String> responseHeader;
	private String contentType;
	private String CRLF;
	public HttpResponseImpl(){}
	public HttpResponseImpl(Socket socket){
		this.socket = socket;
		try {
			this.out = socket.getOutputStream();
			this.pw = new PrintWriter(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseHeader = new HashMap<String,String>();
	}
	@Override
	//获得一个指向客户端的字节流
	public OutputStream getOutputStream() throws Exception {
		return out;
	}

	@Override
	//获得一个指向客户端的字符流
	public PrintWriter getPrintWriter() throws Exception {
		return pw;
	}

	@Override
	//设置响应的状态行 参数为String类型
	public void setStatusLine(String statusCode) {
		this.statusLine = statusCode;
	}

	@Override
	//设置响应的状态行 参数为int类型
	public void setStatusLine(int statusCode) {
		this.statusLine = ""+statusCode;
	}

	@Override
	//设置响应消息报头
	public void setResponseHeader(String hName, String hValue) {
		responseHeader.put(hName, hValue);
	}

	@Override
	//设置响应消息报头中Content-Type属性
	public void setContentType(String contentType) {
		this.contentType = "Content-Type: "+contentType;
	}

	@Override
	//设置响应消息报头中Content-Type属性 并且同时设置编码
	public void setContentType(String contentType, String charsetName) {
		this.contentType = "Content-Type: "+contentType+";charset="+charsetName;
	}

	@Override
	//设置CRLF 回车换行  \r\n
	public void setCRLF() {
		pw.print("\r\n");
	}

	@Override
	//把设置好的响应状态行、响应消息报头、固定空行这三部分写给浏览器
	public void printResponseHeader() {
		
	}

	@Override
	//把响应正文写给浏览器
	public void printResponseContent(String requestPath) {
		
	}

}
