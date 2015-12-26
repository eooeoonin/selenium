package com.wensheng.selenium.page;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    @FindBy(className = "page-top-icon")
    public WebElement phoneNum;  //手机号输入框

    @FindBy(linkText = "注册/登录")
    public WebElement getV; //注册登录
  
}
