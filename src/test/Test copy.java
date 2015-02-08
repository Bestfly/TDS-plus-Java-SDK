/**
 * 
 */
package com.mangocity.airticket.task.vo.dropcabinseat.resonse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.mangocity.airticket.task.common.SymbolConstant;
import com.mangocity.airticket.task.util.httphelp.HttpClientSender;
import com.mangocity.airticket.task.util.md5tool.SignUtil;
import com.mangocity.flights.commons.http.ObjectMapper;

/**
 * @author wenxia
 *
 */
public class Test {
	private static final String commadURL ="http://www.skyecho.com/cgishell/module/xml/air_scan.pl";
//                                        	http://www.skyecho.com/cgishell/module/xml/air_scan.pl
	private static final String Corp_ID ="Corp_ID";
	private static final String Corp_ID_data ="SZX899";//暂时定为

	private static final String User_ID = "User_ID";
	private static final String User_ID_data = "szx899001";//暂时定为
	private static final String air_xml ="air_xml";
	private static final String Sign ="Sign";
	private static final String private_key="D%d3L8#F";//测试密钥为： 12345678
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//
//		
//		StringBuilder xml = new StringBuilder("<INFO><time>2015.01.31 18:18:05</time><scaninfo><scan_resid>103941</scan_resid><passage>LI/SHA</passage><booktime>2015.01.31 22:00:00</booktime><airline><airdate>2015.04.29</airdate><trip>SZXKMG</trip><flight>MU5764</flight><depart_time>0705</depart_time></airline><old><resid>154020000009</resid><pnr>JVX07S</pnr><cabin>N</cabin><price>0.00</price></old><new><resid>15222222222</resid><pnr>333333</pnr><cabin>B</cabin><price>0.00</price></new></scaninfo><scaninfo><scan_resid>104234</scan_resid><passage>wen/xia</passage><booktime>2015.01.31 22:00:00</booktime><airline><airdate>2015.04.30</airdate><trip>SZXKMG</trip><flight>MU5764</flight><depart_time>0705</depart_time></airline><old><resid>154020000011</resid><pnr>HECCM5</pnr><cabin>N</cabin><price>0.00</price></old><new><resid>151111111111</resid><pnr>111111</pnr><cabin>Y</cabin><price>0.00</price></new></scaninfo><scaninfo><scan_resid>103789</scan_resid><passage>huangyiping</passage><booktime>2015.01.31 22:00:00</booktime><airline><airdate>2015.03.27</airdate><trip>CANHAK</trip><flight>HU7042</flight><depart_time>0715</depart_time></airline><old><resid>154020000008</resid><pnr>KV9KQ0</pnr><cabin>Q1</cabin><price>0.00</price></old><new><resid>15333333333</resid><pnr>222222</pnr><cabin>H</cabin><price>0.00</price></new></scaninfo></INFO>");
//	
/**
 * //提交参数顺序
		//Corp_ID=412101&User_ID=admin&air_xml=<info>...</info>&Sign=553BF307E2CA2897DD65C1E554DA3248
		params.append(Corp_ID).append("=").append(Corp_ID_data);
		params.append(SymbolConstant.S_MUSIC);
		params.append(User_ID).append("=").append(User_ID_data);
		params.append(SymbolConstant.S_MUSIC);
		params.append(air_xml).append("=");
		String dropCabinseatXml = buildXML(order,pnr);
		params.append(dropCabinseatXml.trim());
		String sign = SignUtil.buildSign(params.toString(), private_key);
		String signBig = sign.toUpperCase();
		logger.info("signBig："+signBig);
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put(air_xml, dropCabinseatXml);
		paramMap.put(Corp_ID, Corp_ID_data);
		paramMap.put(User_ID, User_ID_data);
		paramMap.put(Sign, signBig);
 */
		
		StringBuffer params = new StringBuffer();
		
		//提交参数顺序
		//Corp_ID=412101&User_ID=admin&air_xml=<info>...</info>&Sign=553BF307E2CA2897DD65C1E554DA3248
		params.append(Corp_ID).append("=").append(Corp_ID_data);
		params.append(SymbolConstant.S_MUSIC);
		params.append(User_ID).append("=").append(User_ID_data);
		params.append(SymbolConstant.S_MUSIC);
		params.append(air_xml).append("=");
//		String dropCabinseatXml = buildXML(order,pnr);
	    String psgName = "huangyi";
	    String gb = psgName;
//	    try {
//			 gb = URLEncoder.encode(psgName,"gb2312");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 

		StringBuilder dropCabinseatXml = new StringBuilder("<info><session_body><scantype>S</scantype><out_resid>15020410T1987</out_resid><ticket_time>2015.02.04 10:00:00</ticket_time><pnr>KGY09T</pnr><airlines><airline><air_code>MU</air_code><flight_no>5332</flight_no><air_date>2015.04.29</air_date><depart>SZX</depart><arrive>PVG</arrive><depart_time>0730</depart_time><arrive_time>0955</arrive_time><class_code>R</class_code><scan_class>YHM</scan_class><class_discount></class_discount><passengers>");
		dropCabinseatXml.append("<passenger><p_name>")
		.append(gb)
		.append("</p_name><p_type>A</p_type><card_type>NI</card_type><card_id>123</card_id><out_price>840</out_price><tax_fee></tax_fee><yq_fee></yq_fee></passenger></passengers></airline></airlines></session_body></info>");
		params.append(dropCabinseatXml.toString().trim());
		System.out.println("params.toString()==>"+params.toString());
		String sign = SignUtil.buildSign(params.toString(), private_key);
		String signBig = sign.toUpperCase();
		System.out.println("signBig:"+signBig);
//		logger.info("signBig："+signBig);
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put(air_xml, dropCabinseatXml.toString().trim());
		paramMap.put(Corp_ID, Corp_ID_data);
		paramMap.put(User_ID, User_ID_data);
		paramMap.put(Sign, signBig);
		String response="";
		try {
			
			response = HttpClientSender.send(commadURL, paramMap);
			System.out.println("response:"+response);
//			logger.info("resonse:"+response);
		} catch (Exception e) {
//			logger.equals("发送请求错误");
//			logger.error(e);
		}
//		xml2obj = (Persons) stream.fromXML(obj2xml);
//		
//		 for (Iterator iterator = xml2obj.getListPerson().iterator(); iterator.hasNext();) {   
//			 Person person = (Person) iterator.next();   
//			           
//			         System. out .println(person.getIdCard()+ "--" +person.getName()+ "--" +person.getAge());   
//			     }   

		 
//		Test t = new Test();
//		MonitorInfoResponse res = t.parseXML(xml.toString());
//		System.out.println(res.toString());
	}
	
	
	public MonitorInfoResponse parseXML(String xml){
		MonitorInfoResponse response = ObjectMapper.readXmlValue(xml, MonitorInfoResponse.class);
		
		return response;
	}

}
