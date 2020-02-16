package com.testcases;

import org.openqa.selenium.Alert;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;

public class AddCustomerTest extends TestBase {
	
	@Test (dataProvider="getData", description="data.csv" )
	public void addCustomer(String firstName, String lastName, String postCode, String alertText, String runMode  ) throws InterruptedException{
		if(!runMode.equals("y")){
			throw new SkipException("skipping the test case as run mode");
		}
		//csvFileName = "data.csv";
		System.out.println(firstName+" "+lastName+" "+postCode+" "+alertText);
		click("addcust_xpath");
		type("firstname_xpath",firstName);
		type("lastname_xpath",lastName);
		type("postcode_xpath",postCode);
		click("addbtn_xpath");
		
		
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			
			Assert.assertTrue(alert.getText().contains(alertText));
			alert.accept();
			Thread.sleep(2000);
		
		
	}
	
	

}
