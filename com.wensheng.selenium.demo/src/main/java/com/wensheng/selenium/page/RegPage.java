package com.wensheng.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegPage {
    @FindBy(id = "phone")
    public WebElement phoneNum;  //手机号输入框

    @FindBy(id = "getv")
    public WebElement getV; //获取验证码
    
    @FindBy(id = "verifycode")
    public WebElement verifyCode; //验证码输入框
    
    @FindBy(id = "regpassword")
    public WebElement password; //密码输入框
    
    @FindBy(id = "js-button")
    public WebElement finish; //完成按钮
}
