package com.wensheng.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServerHomePage {   
	@FindBy(xpath = "//input")
	public WebElement userName;  //登录框

    @FindBy(xpath = "//div[2]/input")
    public WebElement passwd; //登录密码框
    
    @FindBy(xpath ="//div[2]/button")
    public WebElement logBtn; //登录确定按钮
    
    @FindBy(linkText="工作面板")
    public WebElement panel;  //工作面板
    
    @FindBy(linkText="产品管理")
    public WebElement productMgr;  //产品管理
    
    @FindBy(linkText="借款产品")
    public WebElement addProduct;  //借款产品
   
    @FindBy(id="myadd")
    public WebElement addBtn;  //借款产品-添加按钮
}
