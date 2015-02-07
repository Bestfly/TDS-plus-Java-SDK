package com.higds.sdk.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.higds.sdk.model.HttpResultBase;
import com.higds.sdk.model.HttpResultInfo;

public class HttpUtil {

	public static HttpResultInfo doHttpGet(String url,HashMap<String,String> headers) throws MalformedURLException{
		return doHttpRequest(new URL(url),headers,null);
	}
	
	public static HttpResultInfo doHttpPost(String url,HashMap<String,String> headers,String data) throws MalformedURLException{
		return doHttpRequest(new URL(url),headers,data);
	}
	
	private static HttpResultInfo doHttpRequest(URL url,HashMap<String,String> headers,String data){
		HttpResultInfo result=new HttpResultInfo();
		URLConnection uc = null;
		try{
			uc = url.openConnection();
		}
		catch (IOException e) {
			LogUtil.info(e.getMessage());
			result.setMessage(e.getMessage());
			return result;
		}
		HttpURLConnection httpUC = (HttpURLConnection) uc;
		// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 
		// 默认情况下是false; 
		httpUC.setDoOutput(true);	// Post 请求不能使用缓存 
		httpUC.setUseCaches(false);
		// 设定传送的内容类型是可序列化的java对象 
		// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
//		httpUC.setRequestProperty("Content-type","application/x-java-serialized-object");
		Iterator iter = headers.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			httpUC.setRequestProperty(entry.getKey().toString(),entry.getValue().toString());
		}
		// 设定请求的方法为"POST"，默认是GET
		try {
			if(data!=null){
				httpUC.setRequestMethod("POST");
			}
			else{
				httpUC.setRequestMethod("GET");
			}
		} catch (ProtocolException e) {
			LogUtil.info(e.getMessage());
			result.setMessage(e.getMessage());
			return result;
		}
		DataOutputStream out=null;
		try {
			httpUC.connect();
			if(httpUC.getRequestMethod().equals("POST")){
				out = new DataOutputStream(httpUC
		                .getOutputStream());
				out.write(data.getBytes("UTF8")); 
		        out.flush();
		        out.close(); 
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpUC.getInputStream(),"UTF8"));
			String responseString="";
			String line="";
			while ((line = reader.readLine()) != null) {
				responseString+=line;
	        }
			HttpResultBase httpbase=JSON.parseObject(responseString, HttpResultBase.class);
			result=httpbase.getError();
			reader.close();
		} catch (IOException e) {
			LogUtil.info(e.getMessage());
			result.setMessage(e.getMessage());
			return result;
		}
		catch(Exception e){
			LogUtil.info(e.getMessage());
			result.setMessage(e.getMessage());
			return result;
		}
		finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(httpUC!=null){
				httpUC.disconnect();
			}
		}
		return result;
	}
}
