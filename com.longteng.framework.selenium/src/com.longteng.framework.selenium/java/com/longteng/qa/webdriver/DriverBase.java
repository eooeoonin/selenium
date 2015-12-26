package com.longteng.qa.webdriver;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.longteng.qa.util.Base;
import com.longteng.qa.util.Logger;

public class DriverBase extends Base {
	private WebDriver dr = null;
	public static WebDriverPlus driver = null;
	public static int implicitlyWait = 30;
	public static int webDriverWait = 30;

	/**
	 * 启动浏览器
	 */
	public void launch() {
		String browser = System.getProperty("WebDriver.Browser") ;
		String browserLocation = System.getProperty("WebDriver.Browser.Location") ;
		//launch browser
		if(browser.equalsIgnoreCase("Firefox")){
			Logger.log("打开Firefox浏览器");
			if(browserLocation!=null&&!browserLocation.isEmpty()){
				System.setProperty("webdriver.firefox.bin",browserLocation);
			}
            dr = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("Chrome")){
			Logger.log("打开Chrome浏览器");
			if(browserLocation!=null&&!browserLocation.isEmpty()){
				System.setProperty("webdriver.chrome.driver",browserLocation);
			}
            dr = new ChromeDriver();
		}else{
			Logger.log("打开IE浏览器");
			if(browserLocation!=null&&!browserLocation.isEmpty()){
				System.setProperty("webdriver.ie.driver",browserLocation);
			}
			dr = new InternetExplorerDriver();	
		}
		driver =  new WebDriverPlus(dr);
		implicitlyWait = Integer.parseInt(System.getProperty("WebDriver.ImplicitlyWait").trim());
		webDriverWait=Integer.parseInt(System.getProperty("WebDriver.WebDriverWait").trim());
		Logger.log("设置全局显示等待:"+implicitlyWait);
		driver.manage().timeouts().implicitlyWait(implicitlyWait ,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	/**
	 * 输入值并验证输入正确
	 * @param element
	 * @param text
	 */
	public void input(WebElement element,String text) {
		element.clear();
		element.sendKeys(text);
		valueToBe(element, text);
	}
	
	/**
	 * 通过Js注入输入值并验证输入正确，当给日期等控件输入值时，推荐使用此方法
	 * @param element
	 * 
	 * @param text
	 */
	public void inputByJs(WebElement element,String text) {
		jsExecutor(element, String.format("arguments[0].value=\"%s\"",text));
		valueToBe(element, text);
	}
	
	/**
	 * 选择下拉列表值但不验证是否选择成功
	 * @param element
	 * 
	 * @param text
	 */	
	public void selectWithoutCheck(WebElement element,String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	/**
	 * 将鼠标焦点移至本元素
	 * @param element
	 */
	public void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/**
	 * 使用action单击当前元素
	 * @param element
	 */
	public void click(WebElement element) {
		Actions action = new Actions(driver);
		action.click(element).perform();
	}
	
	/**
	 * 双击当前元素
	 * @param element
	 */	
	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	/**
	 * 在当前元素右击
	 * @param element
     */
	public void rightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	/**
	 * 拖拽当前页面元素至指定元素位置
	 * @param source 起始位置元素
	 * @param target 指定位置元素
     */
	public void dragAndDropTo(WebElement source, WebElement target) {
		(new Actions(driver)).dragAndDrop(source,target).perform();
	}
	
	/**
	 * 获取元素对齐方式
	 * @param element
	 * @return left or right
	 */	
	public String getTextAlign(WebElement element){
		return element.getCssValue("text-align");
	}
	
	/**
	 *判断当前元素是否存在
	 * @param element
	 * 
	 * @param timeout
	 *            设置寻找元素超时时间
	 * @return true or false
	 */
	public boolean isElementPresent(WebElement element,int timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
			element.getLocation();
			driver.manage().timeouts().implicitlyWait(implicitlyWait,TimeUnit.SECONDS);
			return true;
		} catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(implicitlyWait,TimeUnit.SECONDS);
			return false;
		}
	}
	
	/**
	 *判断当前元素是否存在
	 * @param element
	 * 
	 * @param timeout
	 *            设置寻找元素超时时间
	 * @return true or false
	 */
	public boolean isElementPresent(WebElement element) {
		try {
			driver.manage().timeouts().implicitlyWait(implicitlyWait,TimeUnit.SECONDS);
			element.getLocation();
			driver.manage().timeouts().implicitlyWait(implicitlyWait,TimeUnit.SECONDS);
			return true;
		} catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(implicitlyWait,TimeUnit.SECONDS);
			return false;
		}
	}
	
	/**
	 * 验证元素加载完成，直到超时
	 * @param element
	 */
	public void toBePresent(final WebElement element) {
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return isElementPresent(element,implicitlyWait);
			  }
			});
	}
	
	/**
	 * 验证元素不加载，直到超时
	 * @param element
	 */
	public void toBeNotPresent(final WebElement element) {
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !isElementPresent(element,3);
			  }
			});
	}
	
	/**
	 * 验证元素可见，直到超时
	 * @param element
	 */
	public void toBeVisible(WebElement element) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(ExpectedConditions.visibilityOf(element));
		obumbrateElement(element);
	}
	
	/**
	 * 验证元素不可见，直到超时
	 * @param element
	 */
	public void toBeInvisible(final WebElement element) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !element.isDisplayed();
			  }
		});
		obumbrateElement(element);
	}
	
	/**
	 * 验证元素可用，直到超时
	 * @param element
	 */
	public void toBeEnable(WebElement element) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(ExpectedConditions.elementToBeClickable(element));
		obumbrateElement(element);
	}
	
	/**
	 * 验证元素不可用，直到超时
	 * @param element
	 */
	public void toBeDisable(final WebElement element) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !element.isEnabled();
			  }
			});
		obumbrateElement(element);
	}
	
	/**
	 * 验证元素text与期望text相同，直到超时
	 * @param element
	 * 
	 * @param text
	 *            期望 text
	 */
	public void textToBe(final WebElement element,final String text) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return element.getText().equals(text);
			  }
			});
		obumbrateElement(element);
	}
	
	 /**
     * 验证元素text为空，直到超时
	 * @param element
     */
    public void textToBeEmpty(final WebElement element) {
		lightElement(element);
        new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return element.getText().isEmpty();
            }
        });
        obumbrateElement(element);
    }
	
	 /**
     * 验证元素text不为空，直到超时
	 * @param element
     */
    public void textToBeNotEmpty(final WebElement element) {
		lightElement(element);
        new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return !element.getText().isEmpty();
            }
        });
        obumbrateElement(element);
    }
    
	/**
	 * 验证元素text包含期望text，直到超时
	 * @param element
	 * 
	 * @param text
	 *            期望被包含的 text
	 */
	public void textContains(final WebElement element,final String text) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return element.getText().contains(text);
			  }
			});
		obumbrateElement(element);
	}
	
	/**
	 * 验证元素value与期望value相同，直到超时
	 * @param element
	 * 
	 * @param value
	 *           期望 value
	 */
	public void valueToBe(final WebElement element,final String value) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return element.getAttribute("value").equals(value);
			  }
			});
		obumbrateElement(element);
	}

    /**
     * 验证元素value为空，直到超时
	 * @param element
     */
    public void valueToBeEmpty(final WebElement element) {
		lightElement(element);
        new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return element.getAttribute("value").isEmpty();
            }
        });
        obumbrateElement(element);
    }
	
    /**
     * 验证元素value不为空，直到超时
	 * @param element
     */
    public void valueToBeNotEmpty(final WebElement element) {
		lightElement(element);
        new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return !element.getAttribute("value").isEmpty();
            }
        });
        obumbrateElement(element);
    }
	/**
	 * 验证元素value包含期望value，直到超时
	 * @param element
	 * 
	 * @param value
	 *           期望被包含的 value
	 */
	public void valueContains(final WebElement element,final String value) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return element.getAttribute("value").contains(value);
			  }
			});
		obumbrateElement(element);
	}
		
	/**
	 * 验证单选框、复选框元素被选中，直到超时
	 * @param element
	 */
	public void toBeSelected(WebElement element) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(ExpectedConditions.elementToBeSelected(element));
		obumbrateElement(element);
	}
	
	/**
	 * 验证单选框、复选框元素不被选中，直到超时
	 * @param element
	 */
	public void toBeNotSelected(WebElement element) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(ExpectedConditions.elementSelectionStateToBe(element, false));
		obumbrateElement(element);
	}
	
	/**
	 * 验证当前元素获取焦点，直到超时
	 * @param element
	 */
	public void toBeActive(final WebElement element) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.switchTo().activeElement().getLocation().equals(element.getLocation());
			  }
			});
		obumbrateElement(element);
	}
	
	/**
	 * 验证当前元素没有获取焦点，直到超时
	 * @param element
	 */
	public void toBeNotActive(final WebElement element) {
		lightElement(element);
		new WebDriverWait(driver, DriverBase.webDriverWait).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !driver.switchTo().activeElement().getLocation().equals(element.getLocation());
			  }
			});
		obumbrateElement(element);
	}
	
	/**
	 * 拖拽滚动条使当前元素至视野内
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		jsExecutor(element,"arguments[0].scrollIntoView();");
	}
	
	/**
	 * 执行 javascript
	 * @param element
	 * 
	 * @param script
	 * 		e.g. arguments[0].scrollIntoView();
	 */
	public void jsExecutor(WebElement element,String script) {
		try {
			((JavascriptExecutor) driver).executeScript(script, element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 高亮当前元素
	 * 
	 * @param element
	 */
	public void lightElement(WebElement element) {
		try {
			jsExecutor(element,"arguments[0].style.border = '4px solid red'");
		}catch (NoSuchElementException e) {
			return;
		}
	}
	
	/**
	 * 去掉当前元素高亮效果
	 * 
	 * @param element
	 */
	private void obumbrateElement(WebElement element) {
		try {
			Thread.sleep(100);
			jsExecutor(element,"arguments[0].style.border = 'none'");
		}catch (NoSuchElementException e) {
			return;
		}catch (Exception e) {
		}
	}
	
	/**
	 * 通过元素标签和标签内内容返回一个新元素, 新元素通过xpath定位，xpath值为：//tag[contains(text(),'name')]
	 * @param tag 
	 * 		标签值
	 * @param name
	 * 		name值
	 * @return Element
	 */
	public WebElement getElementByTagAndName(String tag,String name){
		return driver.findElement(By.xpath("//"+tag+"[contains(text(),'"+name+"')]"));
	}
	
	/*
	 * 输入需要打开网址的个数
	 * @param x 
	 * 返回打开所有的页面的句柄
	 */
	public Map openNewWindow(int x){
	for(int g=0;g<x;g++) {
	((JavascriptExecutor)driver).executeScript("window.open('http://www.baidu.com')");
	}
	Map<Integer,String> windowMap=new LinkedHashMap<Integer, String>();
	Set<String>handles=driver.getWindowHandles();
	 Iterator<String>it=handles.iterator();
	 int m=0;
	 while(it.hasNext()){
	 windowMap.put(m, it.next());
	 System.out.println(m);
	 m++;
	 }	
	return windowMap;

	}
	
}
