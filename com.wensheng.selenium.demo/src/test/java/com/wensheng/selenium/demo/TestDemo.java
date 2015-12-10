package com.wensheng.selenium.demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



//import com.wensheng.qa.util.Logger;
import com.longteng.qa.util.Logger;
import com.longteng.qa.database.DBUtil;
import com.wensheng.selenium.page.JdHomePage;
import com.wensheng.selenium.page.BaiduHomePage;
import com.wensheng.selenium.util.TestBase;

public class TestDemo extends TestBase {
	DBUtil dbUtil = new DBUtil("M");
	JdHomePage jdHomePage = null;
	BaiduHomePage baiduHomePage = null;

	@BeforeClass
	public void initPage() {
		//dbUtil.connection();
		// jdHomePage = PageFactory.initElements(driver, JdHomePage.class);
		baiduHomePage = PageFactory.initElements(driver, BaiduHomePage.class);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		// 断开数据库链接
		//dbUtil.close();
	}

	// @Test(dataProvider="csv")
	// public void goJdHomePage(String a) throws Exception {
	// System.out.println(a);
	// driver.get("http://www.jd.com");
	// Thread.sleep(2000);
	// Assert.assertEquals(a, "2700-2799");
	// input(jdHomePage.serachGood, "iphone6");
	// jdHomePage.serachGood.clear();
	// jdHomePage.serachGood.sendKeys("iphone6");
	// Thread.sleep(2000);
	// jdHomePage.serachButton.click();
	// Thread.sleep(2000);
	// }
	// @Test(dataProvider="xml")
	
	//处理表格
//	 @Test
//	    public void test_logins(){
//	    	String  baseUrl = "http://issues.wenzhixin.net.cn/bootstrap-table/";
//	    	 driver.get(baseUrl);
//	    	driver.switchTo().frame(driver.findElement(By.xpath("//html/body/div[3]/iframe")));
//	    	int i =0;
//	    	  List<WebElement> webElementList = driver.findElements(By.xpath("//table[@id='table']/tbody/tr["+i+"]"));
//	      	  for(WebElement element : webElementList){
//	      	   String[] values=element.getText().split(" ");
//	      	   delay(2);
//	      	  }
//	    }
	
	@Test(dataProvider = "csv")
	public void test_login(String user, String password, String message)
			throws Exception {
		//Object name = dbUtil.selectForFirstColumn("select name from user");
		//dbUtil.execute("update ");

		Logger.log("进入百度首页");
		String baseUrl = "https://www.baidu.com/";
		driver.get(baseUrl);
		delay(2);
		Logger.log("点击登录超链接");
		driver.findElement(By.xpath("(//a[contains(text(),'登录')])[2]")).click();
		delay(2);
		Logger.log("输入用户名" + user);
		input(baiduHomePage.userName_input, user);
		delay(2);
		Logger.log("输入密码" + password);
		input(baiduHomePage.password_input, password);
		delay(2);
		Logger.log("验证记住密码复选框初始状态为选中");
		toBeSelected(baiduHomePage.memberPass_cbox);
		Logger.log("点击记住密码复选框");
		click(baiduHomePage.memberPass_cbox);
		Logger.log("验证记住密码复选框未被选中");
		toBeNotSelected(baiduHomePage.memberPass_cbox);
		delay(2);
		Logger.log("点击登录按钮");
		baiduHomePage.login_btn.click();
		Logger.log("验证是否登录成功");
		if (user.isEmpty() || password.isEmpty()) {// 用户名密码有空值
			textToBe(baiduHomePage.error_label, message);
			;
			delay(2);
			Logger.log("点击关闭按钮");
			baiduHomePage.close_btn.click();
			Logger.log("点击登录框关闭");
			toBeInvisible(baiduHomePage.userName_input);
		} else {// 用户名密码都不为空
			if (user.equals("谷歌亦人身") && password.equals("nihuaiqing2237")) {// 用户名密码正确
				toBeNotPresent(baiduHomePage.userName_input);
				toBePresent(driver.findElement(By
						.xpath("//a[@id='s_usersetting_top']/span")));
			} else {// 用户或名密码错误
				textToBe(baiduHomePage.error_label, message);
				delay(2);
				Logger.log("点击关闭按钮");
				baiduHomePage.close_btn.click();
				Logger.log("点击登录框关闭");
				toBeInvisible(baiduHomePage.userName_input);
			}
		}
	}
}
