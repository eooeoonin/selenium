package com.wensheng.selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.wensheng.qa.util.Logger;
import com.longteng.qa.util.Logger;
import com.wensheng.selenium.page.MainPage;
import com.wensheng.selenium.page.RegPage;
import com.wensheng.selenium.util.TestBase;


public class Reg extends TestBase {
	MainPage mainPage=null;
	RegPage regPage=null;
	
	@BeforeClass
	public void initPage(){
		mainPage = PageFactory.initElements(driver, MainPage.class);
		regPage = PageFactory.initElements(driver, RegPage.class);
	}
	
	@Test(dataProvider="xml")
	 public void test_login(String phoneNum, String password) throws Exception {
	      Logger.log("进入注册页");
	  	  String  baseUrl = "http://124.205.230.55:10017/";
	  	  driver.get(baseUrl);
	  	  mainPage.getV.click();
	  	  delay(2);
	  	  Logger.log("输入手机号");
	  	  regPage.phoneNum.sendKeys(phoneNum);
	  	  delay(2);
	  	  Logger.log("输入密码");
	  	  regPage.verifyCode.sendKeys(password);
	  	  delay(2);
	  	  Logger.log("点击确定按钮");
	      regPage.finish.click();
	  	  Logger.log("验证是否登录成功");
	}

}
