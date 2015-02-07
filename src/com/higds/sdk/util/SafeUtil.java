package com.higds.sdk.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class SafeUtil {
		public final static String[] hexDigits =
			 
	    { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
	            "e", "f" };
	
	    public static String md5(String inputString) {
	        return encodeByMD5(inputString);
	    }
	     
	    public static String md5(File file){
	        FileInputStream fis = null;
	        byte[] b = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            fis = new FileInputStream(file);
	            byte[] buffer = new byte[2048];
	            int length = -1;
	            while((length = fis.read(buffer)) != -1){
	                md.update(buffer,0,length);
	            }
	            b = md.digest();
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return byteArrayToHexString(b);
	    }
	 
	    public static boolean compare(String md5Str, String inputString) {
	        if (md5Str.equals(encodeByMD5(inputString))) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	     
	    private static String encodeByMD5(String originString) {
	        if (originString != null) {
	            try {
	                MessageDigest md = MessageDigest.getInstance("MD5");
	                byte[] results = md.digest(originString.getBytes("UTF-8"));
	                String resultString = byteArrayToHexString(results);
	                return resultString.toUpperCase();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	        return null;
	    }
	 
	    public static String byteArrayToHexString(byte[] b) {
	        StringBuffer resultSb = new StringBuffer();
	        for (int i = 0; i < b.length; i++) {
	            resultSb.append(byteToHexString(b[i]));
	        }
	        return resultSb.toString();
	    }
	 
	    public static String byteToHexString(byte b) {
	        int n = b;
	        if (n < 0)
	            n = 256 + n;
	        int d1 = n / 16;
	        int d2 = n % 16;
	        return hexDigits[d1] + hexDigits[d2];
	    }
	    
	    public static String doBase64(byte[] input){
	    	BASE64Encoder encode = new BASE64Encoder();
	    	return encode.encode(input);
	    }
	    
	    public static String doSHAR1(String key,String data) throws NoSuchAlgorithmException, InvalidKeyException {
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");  
	        Mac mac = Mac.getInstance("HmacSHA1");  
	        mac.init(signingKey);  
	        byte[] rawHmac = mac.doFinal(data.getBytes());  
	        return SafeUtil.doBase64(rawHmac);  
		}
}
