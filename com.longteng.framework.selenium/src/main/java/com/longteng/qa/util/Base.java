package com.longteng.qa.util;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.longteng.qa.dataprovider.CsvDataProvider;
import com.longteng.qa.dataprovider.ExcelDataProvider;
import com.longteng.qa.dataprovider.XmlDataProvider;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class Base{
	
	//初始化
	static{
    	Logger.setLog();
    	new InitProperties();
	}
	
	
	/**
	 * xml数据驱动调用
	 */
	@DataProvider(name="xml")
	protected Object[][] xmlData(Method m) {
			return new XmlDataProvider().getData(m.getName(),m.getDeclaringClass().getSimpleName()+".xml");
	}
	
	/**
	 * csv数据驱动调用
	 */
	@DataProvider(name="csv")
	protected Object[][] csvData(Method m) {
		System.out.println(m.getName()+"-----"+m.getDeclaringClass().getSimpleName());
			return new CsvDataProvider().getData(m.getName()+".csv",m.getDeclaringClass().getSimpleName());
	}
	/**
	 * 测试数据提供者 - 方法
	 * */
	@DataProvider(name = "xls")
	public Iterator<Object[]> dataFortestMethod(Method m) throws IOException {
//		String moduleName = null; // 模块的名字
//		String caseNum = null; // 用例编号
//		String className = this.getClass().getName();
//		int dotIndexNum = className.indexOf("."); // 取得第一个.的index
//		System.out.println(className+"-----"+dotIndexNum);
//		int underlineIndexNum = className.indexOf("_"); // 取得第一个_的index
//
//		if (dotIndexNum > 0) {	
//			moduleName = className.substring(24, className.lastIndexOf(".")); // 取到模块的名称
//			System.out.println(moduleName+"?????");
//		}
//
//		if (underlineIndexNum > 0) {
//			caseNum = className.substring(underlineIndexNum + 1, underlineIndexNum + 4); // 取到用例编号
//		}
		//将模块名称和用例的编号传给 ExcelDataProvider ，然后进行读取excel数据
		String moduleName=m.getDeclaringClass().getSimpleName();
		return new ExcelDataProvider(moduleName, m.getName());
	}
	/**
	 * 模拟键盘按键操作
	 * @param key of KeyEvent 
	 * 		  e.g. pressKey(KeyEvent.VK_ENTER) 点击回车键 
	 *  t 84  j 74  enter 100  alt 18  tab 9 control 17
	 */
	public static void pressKey(int key) {
		try {
			Robot rob = new Robot();
			rob.keyPress(key);
			rob.keyRelease(key);
		} catch (AWTException e) {
		}
	}
	
	/**
	 * 模拟键盘按键组合操作
	 * @param key of KeyEvent 
	 * 		  e.g. pressKey(KeyEvent.VK_ENTER) 点击回车键 
	 *  t 84  j 74  enter 100  alt 18  tab 9 control 17
	 */
	public static void pressKeysum(int key,int keys) {
		try {
			Robot rob = new Robot();
			rob.keyPress(key);
			rob.keyPress(keys);
			rob.keyRelease(keys);
			rob.keyRelease(key);
		} catch (AWTException e) {
		}
	}
	
	/**
	 * 设置等待时间 单位为秒
	 */
	public static void delay(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
/**
 * @param type 获取当前日期
 */
	public static String timeNow(){
		 Date date = new Date();
		 SimpleDateFormat dates= new SimpleDateFormat("yyyy-MM-dd");
		return dates.format(date).toString();
	}
	/**
	 * @param type 获取根据当前时间的任意时间
	 */
	public static String timeNeed(int a){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str=timeNow();
		Date dt;
		try {
			dt = sdf.parse(str);
		
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DAY_OF_YEAR, a);// 日期加10天
        return sdf.format(rightNow.getTime()).toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * @param type 所期望返回的随机数的类型，包括int，nint（负整数），double，ndouble（负double），char，uchar（特殊字符）, china（中文）
	 * @param length 所期望返回的随机数的长度
	 * @return 随机数
	 * @throws Exception
	 */
	public static String getRandomData(String type, int length) {
		String data = "";
		String negativeData = "";
		String charData[] = { "!", "@", "#", "$", "%", "^", "&", "*" };
		if (type.equals("int")) {
			for (int i = 0; i < length - 1; i++) {
				data += (int) (10 * Math.random());
			}
			data = (int) (9 * Math.random() + 1) + data;
		} else if (type.equals("nint")) {
			for (int i = 0; i < length - 1; i++) {
				data += (int) (10 * Math.random());
			}
			data = "-" + (int) (9 * Math.random() + 1) + data;
		} else if (type.equals("double")) {
			for (int i = 0; i < length - 3; i++) {
				data += (int) (10 * Math.random());
			}
			for (int i = 0; i < 2; i++) {
				negativeData += (int) (10 * Math.random());
			}
			data = (int) (9 * Math.random() + 1) + data + "." + negativeData;
		} else if (type.equals("ndouble")) {
			for (int i = 0; i < length - 3; i++) {
				data += (int) (10 * Math.random());
			}
			for (int i = 0; i < 2; i++) {
				negativeData += (int) (10 * Math.random());
			}
			data = "-" + (int) (9 * Math.random() + 1) + data + "."+ negativeData;
		} else if (type.equals("char")) {
			for (int i = 0; i < length; i++) {
				data += String.valueOf((char) ('a' + (int) (Math.random() * 26)));
			}
		} else if (type.equals("uchar")) {
			for (int i = 0; i < length; i++) {
				Random rnd = new Random();
				data += charData[rnd.nextInt(8)];
			}
		}else if (type.equals("china")) {
			for (int i = 0; i < length; i++) {
				data += "中";
			}
		}
		return data;
	}
	
	/**
	 * 模拟粘贴文本
	 * 
	 * @param text 
	 */
	public static void paste(String text) {
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(new StringSelection(text), null);
		try {
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_V);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_V);
		} catch (AWTException e) {
		}
	}
	/**
	 * 返回一个随机的电话
	 */
public static String suijiMob(){
	
		double d=Math.random();
		int x=(int)(d*1000000000)+1;
		String mf=x+"";
		String[] tel={"13","15","17","18"};
		int y=(int)(d*(tel.length));
		String rel=tel[y];
		String mobile=rel+mf;
		if(mobile.length()==11){
			return mobile;
		}
	return suijiMob();
	
}
/**
 * 返回随机的名字
 */
public static String suijiName(int len){
	 String ret="";
     for(int i=0;i<len;i++){
         String str = null;
         int hightPos, lowPos; // 定义高低位
         Random random = new Random();
         hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
         lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
         byte[] b = new byte[2];
         b[0] = (new Integer(hightPos).byteValue());
         b[1] = (new Integer(lowPos).byteValue());
         try
         {
             str = new String(b, "GBk"); //转成中文
         }
         catch (UnsupportedEncodingException ex)
         {
             ex.printStackTrace();
         }
          ret+=str;
     }
 return ret;
}
/**
 * @pram 每个参数项为空时检查结果
 * 
 */
public static void canshuEmpty(String url,String id,String mes,String[] expect){
	List list=jiekouCheck(url);
	for(int x=0;x<list.size();x++){
		Logger.log("检查信息内容是"+jsonStr(list.get(x).toString(), mes).toString());
		String actu=jsonStr(list.get(x).toString(), id).toString();
		Assert.assertEquals(actu, expect[x]);
	}
	
	
}
/**
 * 接口传递异常参数检测
 */
public static List jiekouCheck(String url){
	List<String>list=new ArrayList<String>();
	//获取完整的url
	//String url4=URLEncoder.encode(url,"utf-8");

	String url1=url.substring(0, url.indexOf("?")+1);
	//截取参数
	String url2=url.substring(url.indexOf("?")+1, url.length());
	String[] splita=url2.split("&");
	//把参数拆分为数组
	String[] value={""};
	//String[] value={"183038757601","1834","中国的，世界的","中国界的","null","asd34&564*&$#"," ","empty"};
	//对每个参数开始取值
	for(int x=0;x<splita.length;x++){
		String canshu=splita[x].substring(0, splita[x].indexOf("=")+1);
		for(int y=0;y<value.length;y++){
			String bf=canshu+value[y]+"&";
			if(splita.length==1){
				
		}else{
			//对每个参数取值
			for(int m=0;m<splita.length;m++){
				if(m==x){
					continue;
				}
				bf+=splita[m]+"&";
			}
		}
			String url3=url1+bf;
			String geturl=url3.substring(0, url3.lastIndexOf("&"));
			//jiekou(geturl).bodyText();
		list.add(geturl);
//			try {
//				list.add(URLEncoder.encode(geturl,"utf-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	return list;
}
/**
 * @param 接口测试并判断结果
 * @param url 所测试的接口地址
 * @param actu 需要取得对应值得参数
 * @param expects 期望的结果
 */
public static void jiekouceshi(String url,String actu,String[] expects){
	//发送接口请求
	try {
		Logger.log("地址是"+url);
	HttpResponse ss=jiekou(url);
	//根据请求结果取对应的值
	String cc=jsonStr(ss.toString(), actu).toString();
	System.out.println("地址是"+url+"结果是---"+ss.bodyText());
	System.out.println("实际的结果是"+cc+"----期望的结果是"+expects[0]);
			Assert.assertEquals(cc, expects[0]);
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("接口异常请排查原因");
	}
}
/**
 *  @param 接口测试，支持GET,post
 *  @param 可以获得状态码等等
 *  @return  返回 reponse
 */

public static HttpResponse jiekou(String b) {
	Logger.log("地址是"+b);
	HttpRequest httpRequest = HttpRequest.get(b);
    HttpResponse response = httpRequest.charset("utf-8").send();
	return response;


}
/**
 * @param json :字符串  获取指定值
 *@param lei: 希望获得的对应值
 */
public static Object jsonStr(String name,String lei){
	String f=(jiekou(name).bodyText()).toString();
	Logger.log("地址是"+name);
	JSONObject json =  new JSONObject(f.substring(f.indexOf("{"),f.lastIndexOf("}")+1));
	Object sss= json.get(lei);
	return sss;
	
}
/**
 * @param jsonshuzu 获取指定字符数组值
 */
public static JSONArray jsonArr(String name,String lei){
	Logger.log("地址是"+name);
	JSONObject json =  new JSONObject(name.substring(name.indexOf("{"),name.lastIndexOf("}")+1));
	JSONArray sss= (JSONArray) json.get(lei);
	return sss;
	
}

}
