package com.briup.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import com.briup.httpImpl.HttpRequestImpl2;

public class ServerTest3 {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = serverSocket.accept();
			HttpRequestImpl2 httpImpl = new HttpRequestImpl2(socket);
			System.out.println("Protocol:"+httpImpl.getProtocol());
			System.out.println("RequestMethod:"+httpImpl.getRequestMethod());
			System.out.println("RequestPath:"+httpImpl.getRequestPath());
			System.out.println("Parameter:"+httpImpl.getParameter("name"));
			System.out.println("Parameter:"+httpImpl.getParameter("age"));
			System.out.println("RequestHeader:"+httpImpl.getRequestHeader());
			for(Map.Entry<String, String> en:httpImpl.getRequestHeader().entrySet()){
				System.out.println(en.getKey()+":"+en.getValue());
			}
			System.out.println("是否是动态："+httpImpl.isDynamicResource());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
