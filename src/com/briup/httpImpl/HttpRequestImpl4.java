package com.briup.httpImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.briup.http.HttpRequest;
import com.briup.util.ConfigUtil;
import com.briup.util.ServletMappingUtil;

/**
 * 参照老师的修正，isDynamic方法
 * @author alan
 * @date 2016年8月30日 下午3:18:45
 */
public class HttpRequestImpl4 implements HttpRequest {
	private Socket socket;
	private BufferedReader br;
	private String protocol;
	private String requestMethod;
	private String requestPath;
	private Map<String, String> parameter;
	private Map<String, String> requestHeader;

	public HttpRequestImpl4() {
	}

	public HttpRequestImpl4(Socket socket) {
		this.socket = socket;
		parameter = new HashMap<String, String>();
		requestHeader = new HashMap<String, String>();
		parser();
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return protocol;
	}

	@Override
	public String getRequestMethod() {
		// TODO Auto-generated method stub
		return requestMethod;
	}

	@Override
	public String getRequestPath() {
		// TODO Auto-generated method stub
		return requestPath;
	}

	@Override
	public Map<String, String> getRequestHeader() {
		// TODO Auto-generated method stub
		return requestHeader;
	}

	@Override
	public String getParameter(String parameterName) {
		return parameter.get(parameterName);
	}

	@Override
	public boolean isStaticResource() {
		if(requestPath!=null){
			String parent  = ConfigUtil.getConfigValue("root");
			File file = new File(parent, requestPath);
			return file.exists() && file.isFile();
		}
		return false;
	}

	@Override
	public boolean isDynamicResource() {
		if(requestPath!=null){
			return ServletMappingUtil.isContainKey(requestPath);
		}
		System.out.println("requestPath is null");
		return false;
	}

	@Override
	public boolean isNullRequest() {
		if(requestPath==null){
			System.out.println("发送空请求");
			return true;
		}
		return false;
	}

	public void parser() {
		try {
			// while (true) {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String line = null;
			// read the first line
			line = br.readLine();
			String[] firstLine = line.split(" ");
			if (firstLine.length != 3) {
				System.out.println("请求不符合HTTP协议要求！");
				return;
			}
			// store the RequestMethod
			setRequestMethod(firstLine[0]);
			// store the Protocol
			setProtocol(firstLine[2]);
			//
			setRequestHeader();

			setParameter(firstLine[1]);
			// 输出解析得到的
			printAll();
			// }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/* finally {
			try {
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
		}*/
	}

	public void printAll() {
		System.out.println("Protocol:" + protocol);
		System.out.println("requestMethod:" + requestMethod);
		System.out.println("requestPath:" + requestPath);
		// 遍历paramMap
		for (Map.Entry<String, String> entry : parameter.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		// 遍历reqHead
		for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	public void setParameter(String firstLine) {
		try {
			// 判断是get方法还是post,
			// 在post中的body是没有换行的，所以需要ready，判断是否有准备好的字符串读取
			if (br.ready()) {
				// post
				// 读取参数
				char[] c = new char[512];
				int len = -1;
				String paramStr = "";
				while ((len = br.read(c)) != -1) {// 读取所有参数
					paramStr += new String(c, 0, len);
				}
				String[] parma = paramStr.split("\\&");// 分割参数
				for (String str : parma) {// 遍历，再把每个参数分割成KV对，并添加
					String[] str2 = str.split("=");
					this.parameter.put(str2[0], str2[1]);
				}
				// 存入Path
				setRequestPath(firstLine);
			} else {// 如果没有再可以读取的，则是get方法
				String[] pathParam = firstLine.split("\\?");// 分割path和parameter
				// 判断是否有参数
				if (pathParam.length == 2) {// 如果有参数，则继续分解
					String[] parma = pathParam[1].split("&");
					for (String str : parma) {// 遍历，再把每个参数分割成KV对，并添加
						String[] str2 = str.split("=");
						this.parameter.put(str2[0], str2[1]);
					}
				}
				// 存入Path
				setRequestPath(pathParam[0]);
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public void setRequestHeader() {
		// now resolve the request Header
		try {
			String line = null;
			while ((line = br.readLine().trim()).length() != 0) {
				// pw.println(line);
				String[] reqHead = line.split(": ");
				// store the request map
				this.requestHeader.put(reqHead[0], reqHead[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

}
