package com.wensheng.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    @FindBy(id="type")
    public Select type;  //借款类型
    
    @FindBy(id="addProduct")
    public WebElement addProduct;  //产品类型
    
    @FindBy(id="add-loanmoneymin")
    public WebElement addLoanmoneyMin;  //金额范围-最小
    
    @FindBy(id="add-loanmoneymax")
    public WebElement addLoanmoneyMax;  //金额范围-最大
    
    @FindBy(id="add-beginratescope")
    public WebElement addBeginrateScop;  //利率范围-最小
    
    @FindBy(id="add-endratescope")
    public WebElement addEndrateScope;  //利率范围-最大
    
    @FindBy(id="add-repaytype")
    public Select addRepayType;  //还款方式
    
    @FindBy(id="loanProducts.status")
    public Select loanProductsStatus;  //前台显示
    
    @FindBy(id="add-termtype")
    public Select addTermtype;  //期限类型
    
    @FindBy(id="add-begintermscope")
    public WebElement addBegintermsScope;  //期限范围-最小
    
    @FindBy(id="add-endtermscope")
    public WebElement addEndtermScope;  //期限范围-最大
    
    @FindBy(id="add-platformfee")
    public WebElement addPlatformFee;  //平台手续费
    
    @FindBy(id="add-riskmargin")
    public WebElement addRiskMargin;  //平台保证金
    
    @FindBy(id="sure")
    public WebElement sureBtn;  //确定按钮
    
}
