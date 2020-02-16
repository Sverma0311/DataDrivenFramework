package com.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.TestBase;
import com.utilities.TestUtil;

public class CustomListener implements ITestListener, ISuiteListener {
	
	public static ExtentHtmlReporter report;
	public static ExtentReports extent;
	public static ExtentTest test;

	@Override
	public void onFinish(ITestContext ctx) {
		test.log(Status.INFO, ctx.getName()+" has been completed successfully");
		
	}

	@Override
	public void onStart(ITestContext ctx) {
		
		System.out.println(ctx.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			TestUtil.captureScreenShot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.FAIL, "test case fail"+result.getName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(Status.SKIP, arg0.getName()+"test case skipped");
		try {
			TestUtil.captureScreenShot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		TestBase.setExtendTest(test);
		
		test.log(Status.INFO, "test case start execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getName()+" Pass" );		
		Reporter.log("pass");
				
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		report = new ExtentHtmlReporter("./Report/extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(report);
		System.out.println(suite.getName()+" test start");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		extent.flush();
		extent.close();
	}

}
