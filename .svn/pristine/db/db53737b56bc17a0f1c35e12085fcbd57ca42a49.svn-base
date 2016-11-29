package com.briup.httpImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.briup.http.HttpResponse;
import com.briup.util.ConfigUtil;
import com.briup.util.StatusCodeUtil;

public class HttpResponseImpl2 implements HttpResponse {
	private Socket socket;
	private OutputStream out;
	private PrintWriter pw;
	private Map<String, String> responseHeader;
	private StringBuffer sb;

	public HttpResponseImpl2() {
	}

	public HttpResponseImpl2(Socket socket) {
		this.socket = socket;
		try {
			this.out = socket.getOutputStream();
			this.pw = new PrintWriter(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseHeader = new HashMap<String, String>();
		sb = new StringBuffer();
	}

	@Override
	// 获得一个指向客户端的字节流
	public OutputStream getOutputStream() throws Exception {
		return out;
	}

	@Override
	// 获得一个指向客户端的字符流
	public PrintWriter getPrintWriter() throws Exception {
		return pw;
	}

	@Override
	// 设置响应的状态行 参数为String类型
	public void setStatusLine(String statusCode) {
		sb.append("HTTP/1.1 " + statusCode + " " + StatusCodeUtil.getStatusMsg(statusCode));
		setCRLF();
	}

	@Override
	// 设置响应的状态行 参数为int类型
	public void setStatusLine(int statusCode) {
		sb.append("HTTP/1.1 " + statusCode + " " + StatusCodeUtil.getStatusMsg(statusCode));
		setCRLF();
	}

	@Override
	// 设置响应消息报头
	public void setResponseHeader(String hName, String hValue) {
		sb.append(hName + ": " + hValue);
		setCRLF();
	}

	@Override
	// 设置响应消息报头中Content-Type属性
	public void setContentType(String contentType) {
		setResponseHeader("Content-Type: ", contentType);
	}

	@Override
	// 设置响应消息报头中Content-Type属性 并且同时设置编码
	public void setContentType(String contentType, String charsetName) {
		setResponseHeader("Content-Type", contentType+";charset="+charsetName);
	}

	@Override
	// 设置CRLF 回车换行 \r\n
	public void setCRLF() {
		sb.append("\r\n");
	}

	@Override
	// 把设置好的响应状态行、响应消息报头、固定空行这三部分写给浏览器
	public void printResponseHeader() {
		String str = sb.toString();
		System.out.println(str);
		pw.print(str);
		pw.print("\r\n");
		pw.flush();
	}

	@Override
	// 把响应正文写给浏览器
	public void printResponseContent(String requestPath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(
					new File(ConfigUtil.getConfigValue("root"), requestPath));
			byte[] b = new byte[512];
			int len = -1;
			printResponseHeader();
			while ((len = fis.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
