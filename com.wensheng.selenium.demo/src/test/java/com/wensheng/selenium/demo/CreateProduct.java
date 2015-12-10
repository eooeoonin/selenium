package com.wensheng.selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.longteng.qa.util.Logger;
import com.wensheng.selenium.page.ProductPage;
import com.wensheng.selenium.page.ServerHomePage;
import com.wensheng.selenium.util.TestBase;

public class CreateProduct extends TestBase {
	ServerHomePage serverHomePage=null;
	ProductPage addProductPage=null;

	@BeforeClass
	public void initPage(){
		serverHomePage = PageFactory.initElements(driver, ServerHomePage.class);
		addProductPage = PageFactory.initElements(driver, ProductPage.class);
	}
	
	@Test(dataProvider="csv")
    public void login(String user,String password) throws Exception {
		Logger.log("登录服务器");
	  	String  baseUrl = "http://124.205.230.55:10014";
	  	driver.get(baseUrl);
	  	delay(5);
	  	serverHomePage.userName.sendKeys(user);
	  	//input(serverHomePage.userName, user);
	  	serverHomePage.passwd.sendKeys(password);
	  	serverHomePage.logBtn.click();
	  	delay(2);
	  	if(serverHomePage.panel.isDisplayed()){
	  		 Logger.log("登录服务器成功！！！");
	  	}
	  	else
	  		{
	  		Logger.log("登录服务器失败！！！");
	  		}
	}
	
	@Test(dataProvider="csv")
    public void product(String type,String addProduct,String addLoanmoneyMin,String addLoanmoneyMax,
    		String addBeginrateScop,String addEndrateScope,String addRepayType,String loanProductsStatus,
    		String addTermtype,String addBegintermsScope,String addEndtermScope,String addPlatformFee,
    		String addRiskMargin) throws Exception {
		serverHomePage.productMgr.click();
		serverHomePage.addProduct.click();
		delay(2);
		serverHomePage.addBtn.click();
		delay(2);
		
		addProductPage.type.selectByVisibleText(type);
		addProductPage.addProduct.sendKeys(addProduct);
		addProductPage.addLoanmoneyMin.sendKeys(addLoanmoneyMin);
		addProductPage.addLoanmoneyMax.sendKeys(addLoanmoneyMax);
		addProductPage.addBeginrateScop.sendKeys(addBeginrateScop);
		addProductPage.addEndrateScope.sendKeys(addEndrateScope);
		addProductPage.addRepayType.selectByVisibleText(addRepayType);
		addProductPage.loanProductsStatus.selectByVisibleText(loanProductsStatus);
		addProductPage.addTermtype.selectByVisibleText(addTermtype);
		addProductPage.addBegintermsScope.sendKeys(addBegintermsScope);
		addProductPage.addEndtermScope.sendKeys(addEndtermScope);
		addProductPage.addPlatformFee.sendKeys(addPlatformFee);
		addProductPage.addRiskMargin.sendKeys(addRiskMargin);
		addProductPage.sureBtn.click();
		
	}
}
