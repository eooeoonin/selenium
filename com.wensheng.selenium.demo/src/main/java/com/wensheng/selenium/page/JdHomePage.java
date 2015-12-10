package com.wensheng.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JdHomePage{

    @FindBy(id = "key")
    public WebElement serachGood;  //搜索输入框

    @FindBy(xpath = "//div[@id='search-2014']/div/button")
    public WebElement serachButton; //搜索按钮

//    public void search(String key){
//    	serachGood.clear();
//        serachGood.sendKeys(key);
//        serachButton.click();
// }
    
}


