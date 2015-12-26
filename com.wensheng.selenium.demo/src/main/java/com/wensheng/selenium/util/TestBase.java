package com.wensheng.selenium.util;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

//import com.wensheng.qa.webdriver.DriverBase;
import com.longteng.qa.webdriver.DriverBase;


public class TestBase extends DriverBase{
	
	@BeforeTest
	public void beforeTestBase() {
		//launch();
	}

	@AfterTest(alwaysRun=true)
	public void afterTest() {
		quit();
	}
	
//	@BeforeClass
//	public void beforeBaseClass() {
//	}
//
//	@AfterClass(alwaysRun=true)
//	public void afterBaseClass() {
//	}
	
	/**
	 * quit browser
	 */
	private void quit() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
