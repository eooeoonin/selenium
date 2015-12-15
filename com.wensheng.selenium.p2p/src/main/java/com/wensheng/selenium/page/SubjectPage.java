package com.wensheng.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubjectPage {
    @FindBy(name="loan.title")
    public WebElement title;  //标题
    
    @FindBy(id="loanpid")
    public WebElement loanpid;  //产品类型
    
    @FindBy(id="rate")
    public WebElement rate;  //借款年利率
    
    @FindBy(id="termCount")
    public WebElement termCount;  //借款期限
    
    @FindBy(id="loanAmount")
    public WebElement loanAmount;  //借款金额
    
    @FindBy(name="loan.contractNo")
    public WebElement contractNo;  //合同号
    
    @FindBy(id="openTime")
    public WebElement openTime;  //开始日期
    
    @FindBy(id="openEndTime")
    public WebElement openEndTime;  //结束日期
    
    @FindBy(id="beginAmount")
    public WebElement beginAmount;  //起投金额
    
    @FindBy(name="increaseAmount")
    public WebElement increaseAmount;  //递增金额
    
    @FindBy(name="description")
    public WebElement description;  //项目描述
    
    @FindBy(name="repaymentSource")
    public WebElement repaymentSource;  //还款来源
    
    @FindBy(name="fundsUse")
    public WebElement fundsUse;  //资金用途
    
    @FindBy(name="companyBackground")
    public WebElement companyBackground;  //企业背景
    
    @FindBy(name="businessScope")
    public WebElement businessScope;  //经营范围
    
    @FindBy(name="businessState")
    public WebElement businessState;  //经营状况
    
    @FindBy(name="assureOpinion")
    public WebElement assureOpinion;  //担保意见
    
    @FindBy(name="pawn")
    public WebElement pawn;  //抵押物
    
    @FindBy(name="riskMeasures")
    public WebElement riskMeasures;  //风控措施
    
    @FindBy(linkText="确定")
    public WebElement sureBtn;  //确定
    
}
