package com.briup.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.briup.httpImpl.HttpRequestImpl;

public class ServerTest2 {
	private ServerSocket serverSocket;
	private int port=8888;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	private String protocol;
	private String requestMethod;
	private String requestPath;
	private Map<String, String> paramMap;
	private Map<String, String> reqMap;

	// 解析方法
	public void parser() {
		try {
			
			serverSocket = new ServerSocket(port);
//			while (true) {
				socket = serverSocket.accept();
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
				String line = null;
				// read the first line
				line = br.readLine();
				String[] firstLine = line.split(" ");
				if (firstLine.length != 3) {
					System.out.println("请求不符合HTTP协议要求！");
//					continue;//如果不符合要求则继续while循环，继续阻塞
				}
				
				// store the RequestMethod
				setRequestMethod(firstLine[0]);
				// store the Protocol
				setProtocol(firstLine[2]);
				
				// now resole the request Header
				Map<String, String> reqMap = new HashMap<String, String>();
				while ( (line = br.readLine().trim()).length()!=0) {
					// pw.println(line);
					String[] reqHead = line.split(": ");
					reqMap.put(reqHead[0], reqHead[1]);
				}
				// store the request map
				setReqMap(reqMap);
				
				// 判断是get方法还是post,
				//在post中的body是没有换行的，所以需要ready，判断是否有准备好的字符串读取
				Map<String, String> param = new HashMap<String, String>();
				if(br.ready()){
					//post
					//读取参数
					char[] c = new char[512];
					int len = -1;
					String paramStr = "";
					while((len = br.read(c))!=-1){//读取所有参数
						paramStr+=new String(c,0,len);
					}
					String[] parma = paramStr.split("\\&");//分割参数
					for(String str:parma){//遍历，再把每个参数分割成KV对，并添加
						String[] str2=str.split("=");
						param.put(str2[0],str2[1]);
					}
					//存入Path
					setRequestPath(firstLine[1]);
				}
				else{//如果没有再可以读取的，则是get方法
					String[] pathParam = firstLine[1].split("\\?");//分割path和parameter
					//判断是否有参数
					if(pathParam.length==2){//如果有参数，则继续分解
						String[] parma = pathParam[1].split("&");
						for(String str:parma){//遍历，再把每个参数分割成KV对，并添加
							String[] str2=str.split("=");
							param.put(str2[0],str2[1]);
						}
					}
					//存入Path
					setRequestPath(pathParam[0]);
				}
				//存入参数map
				setParamMap(param);

				//输出解析得到的
				System.out.println("Protocol:"+protocol);
				System.out.println("requestMethod:"+requestMethod);
				System.out.println("requestPath:"+requestPath);
				// 遍历paramMap
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					System.out.println(entry.getKey() + "=" + entry.getValue());
				}
				// 遍历reqHead
				for (Map.Entry<String, String> entry : reqMap.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
				pw.println("HTTP/1.1 200 ok");
				pw.println("Content-Type: text/html;charset=UTF-8");
				pw.print("\r\n");
				pw.print("<html>");
				pw.print("<body>");
				pw.print("我是");
				pw.print("</body>");
				pw.print("</html>");
				pw.flush();
				pw.close();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pw != null) {
					pw.close();
				}
				if (br != null) {
					br.close();
				}
				if (socket != null) {
					socket.close();

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ServerTest2 s = new ServerTest2();
		s.parser();
		HttpRequestImpl requsetImpl = new HttpRequestImpl(s);
		System.out.println(requsetImpl.isDynamicResource());
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public Map<String, String> getReqMap() {
		return reqMap;
	}

	public void setReqMap(Map<String, String> reqMap) {
		this.reqMap = reqMap;
	}

	@Override
	public String toString() {
		return "Request [protocl=" + protocol + ", requestMethod=" + requestMethod + ", requestPath=" + requestPath
				+ ", paramMap=" + paramMap + ", reqMap=" + reqMap + "]";
	}

}
