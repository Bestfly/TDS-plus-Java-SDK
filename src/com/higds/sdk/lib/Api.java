package com.higds.sdk.lib;

import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

import com.higds.sdk.model.HttpResultInfo;
import com.higds.sdk.util.HttpUtil;
import com.higds.sdk.util.SafeUtil;

public class Api {
	private String api_url="http://182.92.239.16/api/higds/";
	
	public String getApi_url() {
		return api_url;
	}

	public void setApi_url(String api_url) {
		this.api_url = api_url;
	}

	public HttpResultInfo saveTDS(HashMap<String,String> requestInfo,HashMap<String,String> validateInfo) throws Exception{
		String url=api_url+requestInfo.get("buckName")+"/tds?taskType=" + requestInfo.get("taskType") + "&priority=" + requestInfo.get("priority");
		HashMap<String,String> headers=new HashMap<String,String>();
		String flag = "MB";
		String signature = "Method=POST" + "\n" +
            "Bucket=" + requestInfo.get("buckName") + "\n";
		
		if (validateInfo!=null && validateInfo.isEmpty()==false) {
			if (validateInfo.containsKey("time")==true) {
                signature += "Time=" + validateInfo.get("time") + "\n";
                flag += "T";
                headers.put("auth-time", validateInfo.get("time"));
            }
            if (validateInfo.containsKey("ip")==true) {
                signature += "Ip=" + validateInfo.get("ip") + "\n";
                flag += "I";
                headers.put("auth-ip", validateInfo.get("ip"));
            }
            if (validateInfo.containsKey("size")==true) {
                signature += "Size=" + validateInfo.get("size") + "\n";
                flag += "S";
                headers.put("auth-size",validateInfo.get("size"));
            }
            if(validateInfo.containsKey("md5")==true){
            	headers.put("content-md5",SafeUtil.md5(requestInfo.get("post_data")).toLowerCase());
            }
        }
		signature = SafeUtil.doSHAR1(requestInfo.get("secretKey"),flag + "\n" + signature);
		headers.put("content-length",String.valueOf(requestInfo.get("post_data").length()));
		headers.put("auth-sign",flag + ":" + requestInfo.get("accessKey") + ":" + signature);
		return HttpUtil.doHttpPost(url, headers, requestInfo.get("post_data"));
	}
	
	public HttpResultInfo getTDS(HashMap<String,String> requestInfo,HashMap<String,String> validateInfo) throws Exception{
		String url=api_url+requestInfo.get("buckName")+"/tds?size=" + requestInfo.get("size");
		HashMap<String,String> headers=new HashMap<String,String>();
		if (validateInfo!=null && validateInfo.isEmpty()==false) {
			String flag = "MB";
			String signature = "Method=GET" + "\n" +
	            "Bucket=" + requestInfo.get("buckName") + "\n";
            if (validateInfo.containsKey("time")==true) {
                signature += "Time=" + validateInfo.get("time") + "\n";
                flag += "T";
                headers.put("auth-time", validateInfo.get("time"));
            }
            if (validateInfo.containsKey("ip")==true) {
                signature += "Ip=" + validateInfo.get("ip") + "\n";
                flag += "I";
                headers.put("auth-ip", validateInfo.get("ip"));
            }
            signature = SafeUtil.doSHAR1(requestInfo.get("secretKey"),flag + "\n" + signature);
    		headers.put("auth-sign",flag + ":" + requestInfo.get("accessKey") + ":" + signature);
        }
		return HttpUtil.doHttpGet(url, headers);
	}

	public HttpResultInfo saveTDSPlusQueue(HashMap<String,String> requestInfo,HashMap<String,String> validateInfo) throws InvalidKeyException, NoSuchAlgorithmException, MalformedURLException{
		String var_sc="";
        if(Integer.parseInt(requestInfo.get("errorNum"))>0){
            var_sc="";
        }
        else{
            var_sc="&score="+(requestInfo.get("score")==null?String.format("%d",new Date().getTime()/1000):requestInfo.get("score"));
        }
        System.out.println(requestInfo.get("score"));
        System.out.println(var_sc);
		String url=api_url + requestInfo.get("buckName") + "/tdsplus?type=queue&taskType=" + requestInfo.get("taskType") + "&priority=" + requestInfo.get("priority")+"&errorNum="+requestInfo.get("errorNum")+var_sc+"&key="+(requestInfo.get("key")==null?String.format("%d",new Date().getTime()/1000):requestInfo.get("key"));
		HashMap<String,String> headers=new HashMap<String,String>();
		String flag = "MB";
		String signature = "Method=POST" + "\n" +
            "Bucket=" + requestInfo.get("buckName") + "\n";
		
		if (validateInfo!=null && validateInfo.isEmpty()==false) {
			if (validateInfo.containsKey("time")==true) {
                signature += "Time=" + validateInfo.get("time") + "\n";
                flag += "T";
                headers.put("auth-time", validateInfo.get("time"));
            }
            if (validateInfo.containsKey("ip")==true) {
                signature += "Ip=" + validateInfo.get("ip") + "\n";
                flag += "I";
                headers.put("auth-ip", validateInfo.get("ip"));
            }
            if (validateInfo.containsKey("size")==true) {
                signature += "Size=" + validateInfo.get("size") + "\n";
                flag += "S";
                headers.put("auth-size",validateInfo.get("size"));
            }
            if(validateInfo.containsKey("md5")==true){
            	headers.put("content-md5",SafeUtil.md5(requestInfo.get("post_data")).toLowerCase());
            }
        }
		signature = SafeUtil.doSHAR1(requestInfo.get("secretKey"),flag + "\n" + signature);
		headers.put("content-length",String.valueOf(requestInfo.get("post_data").length()));
		headers.put("auth-sign",flag + ":" + requestInfo.get("accessKey") + ":" + signature);
		return HttpUtil.doHttpPost(url, headers, requestInfo.get("post_data"));
	}

	public HttpResultInfo getTDSPlusQueue(HashMap<String,String> requestInfo,HashMap<String,String> validateInfo) throws Exception{
		String url=api_url+requestInfo.get("buckName")+"/tdsplus?type=queue&size=" + requestInfo.get("size");
		HashMap<String,String> headers=new HashMap<String,String>();
		if (validateInfo!=null && validateInfo.isEmpty()==false) {
			String flag = "MB";
			String signature = "Method=GET" + "\n" +
	            "Bucket=" + requestInfo.get("buckName") + "\n";
            if (validateInfo.containsKey("time")==true) {
                signature += "Time=" + validateInfo.get("time") + "\n";
                flag += "T";
                headers.put("auth-time", validateInfo.get("time"));
            }
            if (validateInfo.containsKey("ip")==true) {
                signature += "Ip=" + validateInfo.get("ip") + "\n";
                flag += "I";
                headers.put("auth-ip", validateInfo.get("ip"));
            }
            signature = SafeUtil.doSHAR1(requestInfo.get("secretKey"),flag + "\n" + signature);
    		headers.put("auth-sign",flag + ":" + requestInfo.get("accessKey") + ":" + signature);
        }
		return HttpUtil.doHttpGet(url, headers);
	}

	public HttpResultInfo saveTDSPlusCacheQueue(HashMap<String,String> requestInfo,HashMap<String,String> validateInfo) throws InvalidKeyException, NoSuchAlgorithmException, MalformedURLException{
		String var_sc="&score="+(requestInfo.get("score")==null?String.format("%d",new Date().getTime()/1000):requestInfo.get("score"));
        System.out.println(requestInfo.get("score"));
        System.out.println(var_sc);
		String url=api_url + requestInfo.get("buckName") + "/tdsplus?type=cacheQueue&taskType=" + requestInfo.get("taskType") + "&priority=" + requestInfo.get("priority")+"&errorNum="+requestInfo.get("errorNum")+var_sc+"&key="+(requestInfo.get("key")==null?String.format("%d",new Date().getTime()/1000):requestInfo.get("key"));
		HashMap<String,String> headers=new HashMap<String,String>();
		String flag = "MB";
		String signature = "Method=POST" + "\n" +
            "Bucket=" + requestInfo.get("buckName") + "\n";
		
		if (validateInfo!=null && validateInfo.isEmpty()==false) {
			if (validateInfo.containsKey("time")==true) {
                signature += "Time=" + validateInfo.get("time") + "\n";
                flag += "T";
                headers.put("auth-time", validateInfo.get("time"));
            }
            if (validateInfo.containsKey("ip")==true) {
                signature += "Ip=" + validateInfo.get("ip") + "\n";
                flag += "I";
                headers.put("auth-ip", validateInfo.get("ip"));
            }
            if (validateInfo.containsKey("size")==true) {
                signature += "Size=" + validateInfo.get("size") + "\n";
                flag += "S";
                headers.put("auth-size",validateInfo.get("size"));
            }
            if(validateInfo.containsKey("md5")==true){
            	headers.put("content-md5",SafeUtil.md5(requestInfo.get("post_data")).toLowerCase());
            }
        }
		signature = SafeUtil.doSHAR1(requestInfo.get("secretKey"),flag + "\n" + signature);
		headers.put("content-length",String.valueOf(requestInfo.get("post_data").length()));
		headers.put("auth-sign",flag + ":" + requestInfo.get("accessKey") + ":" + signature);
		return HttpUtil.doHttpPost(url, headers, requestInfo.get("post_data"));
	}

	public HttpResultInfo getTDSPlusCacheQueue(HashMap<String,String> requestInfo,HashMap<String,String> validateInfo) throws Exception{
		String url=api_url+requestInfo.get("buckName")+"/tdsplus?type=cacheQueue&taskType=" + requestInfo.get("taskType");
		if(requestInfo.get("key")!=null){
            url+="&key="+URLEncoder.encode(requestInfo.get("key"),"utf-8");
        }
		HashMap<String,String> headers=new HashMap<String,String>();
		if (validateInfo!=null && validateInfo.isEmpty()==false) {
			String flag = "MB";
			String signature = "Method=GET" + "\n" +
	            "Bucket=" + requestInfo.get("buckName") + "\n";
            if (validateInfo.containsKey("time")==true) {
                signature += "Time=" + validateInfo.get("time") + "\n";
                flag += "T";
                headers.put("auth-time", validateInfo.get("time"));
            }
            if (validateInfo.containsKey("ip")==true) {
                signature += "Ip=" + validateInfo.get("ip") + "\n";
                flag += "I";
                headers.put("auth-ip", validateInfo.get("ip"));
            }
            signature = SafeUtil.doSHAR1(requestInfo.get("secretKey"),flag + "\n" + signature);
    		headers.put("auth-sign",flag + ":" + requestInfo.get("accessKey") + ":" + signature);
        }
		return HttpUtil.doHttpGet(url, headers);
	}

}
