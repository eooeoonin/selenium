package com.wensheng.selenium.demo;

import java.util.Map;
import com.longteng.qa.util.Logger;
import com.wensheng.selenium.util.TestBase;

public class OpenMutiWindows extends TestBase{
	public void initEnvironmentTest() {
		System.out.println("要打开窗口");
		String ous="lala"; //定义一个MAP接收返回值
 
 		Map<Integer,String> windowMap=null;
 		windowMap=openNewWindow(3);//打开3个窗口
     	Logger.log("打开窗口1");
   		 //跳转到指定的窗口
     	driver.switchTo().window((String)(windowMap.get(1)));
     	driver.get("http://agent.test.24money.com/loan/");
     	delay(10);
     	Logger.log("打开窗口2");
     	driver.switchTo().window((String)(windowMap.get(2)));
     	driver.get("http://agent.test.24money.com/loan/");
     	Logger.log("打开3");
     	driver.switchTo().window((String)(windowMap.get(3)));
     	driver.get("http://ishop.test.24money.com/");
     	Logger.log("打开0");
     	driver.switchTo().window((String)(windowMap.get(0)));
     	driver.get("http://agent.test.24money.com/loan/");
   		 }

}