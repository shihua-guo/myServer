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

public class ServerTest {
	private ServerSocket serverSocket;
	private int port;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	//解析方法
	public void parser(){
		try {
			serverSocket = new ServerSocket(8888);
			while(true){
				socket = serverSocket.accept();
				br = new BufferedReader(
						new InputStreamReader(
							socket.getInputStream(), "UTF-8"));
				pw = new PrintWriter(
						new OutputStreamWriter(
							socket.getOutputStream(),"UTF-8"));
				String line = null;
				line = br.readLine();//读取第一行
				String[] firstLine = line.split(" ");
				Request request = new Request();
				System.out.println("Requestmethod:"+firstLine[0]);
				request.setRequestMethod(firstLine[0]);
				
				//判断是否有path/parameter
				if (firstLine.length>2) {
					System.out.println("Protocl:"+firstLine[2]);
					request.setProtocl(firstLine[2]);
					//分解path和Param
					String[] pathParam = firstLine[1].split("\\?");
					System.out.println("RequestPath:" + pathParam[0]);
					//存入path
					request.setRequestPath(pathParam[0]);
					//判断是否有参数
					if(pathParam.length>1){
						HashMap<String,String> paramMap = new HashMap<String,String>();
						
						String[] param = pathParam[1].split("\\&");
						//解析参数
						for (String str : param) {
							String[] keyValue = str.split("=");
							paramMap.put(keyValue[0], keyValue[1]);
						}
						//存入参数map
						request.setParamMap(paramMap);
						//遍历参数
						for (Map.Entry<String, String> entry : paramMap.entrySet()) {
							System.out.println(entry.getKey() + "=" + entry.getValue());
						} 
					}
				}
				else{
					System.out.println("Protocl:"+firstLine[1]);
					request.setProtocl(firstLine[1]);
				}
				
				HashMap<String,String> reqMap = new HashMap<String,String>();
				
				while(!"".equals(line = br.readLine())){
//					pw.println(line);
					String[] reqHead = line.split(": ");
					reqMap.put(reqHead[0], reqHead[1]);
//					System.out.println(line);
				}
				//存入reqmap
				request.setReqMap(reqMap);
				
				//遍历reqHead
				for (Map.Entry<String, String> entry : reqMap.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				} 
				System.out.println(request.toString());
				
				//readLine()阻塞
				/*while((line = br.readLine())!=null){
					System.out.println(line);
				}*/				
//				pw.println(com.briup.util.GetTime.getTime());
//				pw.println("fdsf");
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
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(pw!=null){
					pw.close();
				}
				if(br!=null){
					br.close();
				}
				if(socket!=null){
					socket.close();
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ServerTest s = new ServerTest();
		s.parser();
	}
}
class Request{
	private String protocl;
	private String requestMethod;
	private String requestPath;
	private HashMap<String,String> paramMap;
	private HashMap<String,String> reqMap;
	public Request(){}
	public Request(String protocl,String requestMethod,String requestPath,
			HashMap<String,String> reqMap){
		this.protocl = protocl;
		this.requestMethod = requestMethod;
		this.requestPath  =requestPath;
		this.reqMap = reqMap;
	}
	public Request(String protocl,String requestMethod,String requestPath,
			HashMap<String,String> paramMap,HashMap<String,String> reqMap){
		this.protocl = protocl;
		this.requestMethod = requestMethod;
		this.requestPath  =requestPath;
		this.paramMap = paramMap;
		this.reqMap = reqMap;
	}
	public String getProtocl() {
		return protocl;
	}
	public void setProtocl(String protocl) {
		this.protocl = protocl;
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
	public HashMap<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(HashMap<String, String> paramMap) {
		this.paramMap = paramMap;
	}
	public HashMap<String, String> getReqMap() {
		return reqMap;
	}
	public void setReqMap(HashMap<String, String> reqMap) {
		this.reqMap = reqMap;
	}
	@Override
	public String toString() {
		return "Request [protocl=" + protocl + ", requestMethod=" + requestMethod + ", requestPath=" + requestPath
				+ ", paramMap=" + paramMap + ", reqMap=" + reqMap + "]";
	}
	
}
