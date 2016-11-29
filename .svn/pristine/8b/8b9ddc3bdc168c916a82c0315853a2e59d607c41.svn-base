package com.briup.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.briup.http.HttpAccessProcessor;
import com.briup.http.HttpCreator;
import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;
import com.briup.httpImpl.HttpCreatorImpl;

public class ServerTest5 {
	private ServerSocket server;
	private Socket socket;
	private int port = 8889;

	public void parser() {
		try {
			server = new ServerSocket(port);
			while (true) {
				socket = server.accept();
				Thread th = new Thread(new SThread(socket));
				System.out.println("i am a new Thread");
				th.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ServerTest5 test = new ServerTest5();
		test.parser();
	}
}


class SThread implements Runnable{
	private Socket socket;
	public SThread(){}
	public SThread(Socket socket){
		this.socket = socket;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpCreator creator = new HttpCreatorImpl(socket);
		HttpRequest req = creator.getHttpRequest();
		HttpResponse res = creator.getHttpResponse();
		HttpAccessProcessor proc = creator.getHttpAccessProcessor();
		if (req.isDynamicResource()) {
			System.out.println("isDynamicResource");
			if (res == null) {
				System.out.println("res is null");
			}
			proc.processDynamicResource(req, res);
		} else if (req.isStaticResource()) {
			System.out.println("isStaticResource");
			proc.processStaticResource(req, res);
		} else if (req.isNullRequest()) {
			System.out.println("isNullRequest");
			return;
		} else {
			System.out.println("404");
			
			proc.sendError(404, req, res);
		}
		if(socket!=null){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
