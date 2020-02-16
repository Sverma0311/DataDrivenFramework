package com.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.utils.FileUtil;
import com.base.TestBase;

public class TestUtil extends TestBase {
	public static String screeshotPath;
	public static String screenshotName;
	
	public static void captureScreenShot() throws Exception{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screeshotPath = "E:\\Selenium\\DataDrivenFramework\\Report\\extentReport.html";
		screenshotName = "error";
		File destFile = new File(screeshotPath+screenshotName+".jpg");
		
		FileUtils.copyFile(scrFile, destFile);
		
	}

}
