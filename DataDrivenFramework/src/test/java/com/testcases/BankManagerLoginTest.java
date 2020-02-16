package com.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	public void loginAsBankManager() throws InterruptedException{
		log.debug("inside login test");
		click("bmlogin_xpath");
		
		Assert.assertTrue(isElementPrasent(By.xpath(OR.getProperty("addcust_xpath"))), "login not successful");
		log.debug("login successful executed");
		Reporter.log("Bank Manager login successfully executed");
		
	}
	
	@Test
	public void testLogin(){
		System.out.println("inside 2nd method");
	}

}
