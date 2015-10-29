package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class control {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("C:\\Users\\adminw\\Desktop\\自动化\\培训实例\\各种组件操作\\control.html");
        WebElement e=driver.findElement(By.id("accountID"));
        e.sendKeys("username!");
        Thread.sleep(2000);
        
        WebElement e2=driver.findElement(By.id("passwordID"));
        e2.sendKeys("password!");
        Thread.sleep(2000);
        
        Select select=new Select(driver.findElement(By.id("areaID")));
        select.selectByVisibleText("shanxi");
        Thread.sleep(2000);
        
        WebElement choice=driver.findElement(By.id("sexID2"));
        choice.click();
        Thread.sleep(2000);
        
        WebElement ch1=driver.findElement(By.id("u1"));
        ch1.click();
        Thread.sleep(2000);
        
       // WebElement ch2=driver.findElement(By.id("u2"));
       // ch1.click();
       // Thread.sleep(2000);
       // ch2.clear();
       // Thread.sleep(2000);
        
        WebElement ch3=driver.findElement(By.id("u3"));
        ch3.click();
        Thread.sleep(2000);
        
        WebElement cho4=driver.findElement(By.id("u4"));
        cho4.click();
        Thread.sleep(5000);
        
        WebElement submit=driver.findElement(By.id("buttonID"));
        submit.click();
	}

}
