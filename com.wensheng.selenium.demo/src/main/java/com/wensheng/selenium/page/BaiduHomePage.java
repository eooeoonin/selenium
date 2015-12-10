package com.wensheng.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaiduHomePage{

    @FindBy(id="TANGRAM__PSP_8__userName")
    public WebElement userName_input;  //用户名输入框

    @FindBy(id="TANGRAM__PSP_8__password")
    public WebElement password_input; //密码输入框
    
    
    @FindBy(id="TANGRAM__PSP_8__memberPass")
    public WebElement memberPass_cbox; //自动登录复选框

    @FindBy(id="TANGRAM__PSP_8__submit")
    public WebElement login_btn; //登录按钮
    
    @FindBy(id = "TANGRAM__PSP_8__error")
    public WebElement error_label; //登录异常提示信息
    
    @FindBy(id = "TANGRAM__PSP_2__closeBtn")
    public WebElement close_btn; //关闭按钮
    
    
    
}


