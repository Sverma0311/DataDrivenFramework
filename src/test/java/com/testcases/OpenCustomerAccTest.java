package com.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;

public class OpenCustomerAccTest extends TestBase {
	
	@Test (dataProvider="getData",description="openAcc.csv")
	public void openCustomer(String customer, String currency ){
		
		click("openAcc_xpath");
		type("customer_id",customer);
		type("currency_id",currency);
		
		click("process_xpath");
		
		
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			
			//Assert.assertTrue(alert.getText().contains(alertText));
			alert.accept();
		
		
	}
	
	

}
