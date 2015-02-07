package test;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.higds.sdk.lib.Api;
import com.higds.sdk.model.HttpResultInfo;
import com.higds.sdk.util.LogUtil;

public class test {
	
	public static HashMap<String,String> putBase(){
		HashMap<String,String> requestInfo=new HashMap<String,String>();
		requestInfo.put("accessKey","***");
		requestInfo.put("secretKey","***");
		requestInfo.put("buckName","***");
		return requestInfo;
	}
	
	public static void saveTDS(){
		Api api=new Api();
		HashMap<String,String> requestInfo=test.putBase();
		HashMap<String,String> validateInfo=new HashMap<String,String>();
		requestInfo.put("taskType", "hotelInfo");
		requestInfo.put("priority", "1");
		requestInfo.put("post_data", "{\"path\":\"/work/芒果网/DOCUMENT_svn2/mobile/MobileAPI/GTA/Response.1421770458/999D_FIT.xml\"}");
		try {
			HttpResultInfo result=api.saveTDS(requestInfo, validateInfo);
			LogUtil.info(String.valueOf("返回码："+result.getCode()));
			LogUtil.info(String.valueOf("返回消息："+result.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getTDS(){
		Api api=new Api();
		HashMap<String,String> requestInfo=test.putBase();
		HashMap<String,String> validateInfo=new HashMap<String,String>();
		requestInfo.put("size", "99");
		requestInfo.remove("accessKey");
		requestInfo.remove("secretKey");
		try {
			HttpResultInfo result=api.getTDS(requestInfo, validateInfo);
			LogUtil.info(String.valueOf("返回码："+result.getCode()));
			if(result.getCode()==0){
				List<String> list=JSON.parseArray(result.getMessage().toString(),String.class);
				for(String object:list){
					LogUtil.info("返回消息："+object);
				}
			}
			else{
				LogUtil.info("返回消息："+result.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveTDSPlusQueue(){
		Api api=new Api();
		HashMap<String,String> requestInfo=test.putBase();
		HashMap<String,String> validateInfo=new HashMap<String,String>();
		try {
			requestInfo.put("taskType","tests");    //source name
			requestInfo.put("errorNum", "0");	//error count
			requestInfo.put("priority","1");    //is jump to top
			requestInfo.put("score","20150202104845001"); //order number(if 0,this attribute is invalid)
			requestInfo.put("key","testuk2");   //primary key
			requestInfo.put("post_data","{\"currentVer\":\"4.0.4\",\"androidPath\":\"http://www.mangocity.com/3g/apk/upgrade/\",\"iosPath\":\"https://itunes.apple.com/cn/app/mang-guo-lu-you/id441029483?mt=8\",\"updateContent\":\"这是一个更新说明内容！\",\"uver\":\"4.0.0,4.0.1,4.0.2\"}");   //user data
			HttpResultInfo result=api.saveTDSPlusQueue(requestInfo,validateInfo);
			LogUtil.info(String.valueOf("返回码："+result.getCode()));
			LogUtil.info(String.valueOf("返回消息："+result.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getTDSPlusQueue(){
		Api api=new Api();
		HashMap<String,String> requestInfo=test.putBase();
		HashMap<String,String> validateInfo=new HashMap<String,String>();
		requestInfo.put("size", "1");
		requestInfo.remove("accessKey");
		requestInfo.remove("secretKey");
		try {
			HttpResultInfo result=api.getTDS(requestInfo, validateInfo);
			LogUtil.info(String.valueOf("返回码："+result.getCode()));
			if(result.getCode()==0){
				List<String> list=JSON.parseArray(result.getMessage().toString(),String.class);
				for(String object:list){
					LogUtil.info("返回消息："+object);
				}
			}
			else{
				LogUtil.info("返回消息："+result.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveTDSPlusCacheQueue(){
		Api api=new Api();
		HashMap<String,String> requestInfo=test.putBase();
		HashMap<String,String> validateInfo=new HashMap<String,String>();
		try {
			requestInfo.put("taskType","tests");    //source name
			requestInfo.put("score","20150202094845001"); //order number(if 0,this attribute is invalid)
			requestInfo.put("key","testuk2");   //primary key
			requestInfo.put("post_data","{\"currentVer\":\"4.0.4\",\"androidPath\":\"http://www.mangocity.com/3g/apk/upgrade/\",\"iosPath\":\"https://itunes.apple.com/cn/app/mang-guo-lu-you/id441029483?mt=8\",\"updateContent\":\"这是一个更新说明内容！\",\"uver\":\"4.0.0,4.0.1,4.0.2\"}");   //user data
			HttpResultInfo result=api.saveTDSPlusCacheQueue(requestInfo,validateInfo);
			LogUtil.info(String.valueOf("返回码："+result.getCode()));
			LogUtil.info(String.valueOf("返回消息："+result.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getTDSPlusCacheQueue(){
		Api api=new Api();
		HashMap<String,String> requestInfo=test.putBase();
		HashMap<String,String> validateInfo=new HashMap<String,String>();
		try {
			requestInfo.put("taskType","tests");    //source name
			requestInfo.put("key","testuk2&testuk1");   //primary key
			requestInfo.put("post_data","{\"currentVer\":\"4.0.4\",\"androidPath\":\"http://www.mangocity.com/3g/apk/upgrade/\",\"iosPath\":\"https://itunes.apple.com/cn/app/mang-guo-lu-you/id441029483?mt=8\",\"updateContent\":\"这是一个更新说明内容！\",\"uver\":\"4.0.0,4.0.1,4.0.2\"}");   //user data
			HttpResultInfo result=api.getTDSPlusCacheQueue(requestInfo,validateInfo);
			LogUtil.info(String.valueOf("返回码："+result.getCode()));
			if(result.getCode()==0){
				List<String> list=JSON.parseArray(result.getMessage().toString(),String.class);
				for(String object:list){
					LogUtil.info("返回消息："+object);
				}
			}
			else{
				LogUtil.info("返回消息："+result.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
//		test.saveTDS();
//		test.getTDS();
//		test.saveTDSPlusQueue();
//		test.getTDSPlusQueue();
//		test.saveTDSPlusCacheQueue();
//		test.getTDSPlusCacheQueue();
	}
}
