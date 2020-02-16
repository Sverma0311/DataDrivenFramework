package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.utilities.TestUtil;

import au.com.bytecode.opencsv.CSVReader;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(TestBase.class);
	public static WebDriverWait wait;
	public static ExtentTest test;
	public String csvFileName;
	public String browser;
	
	public static void setExtendTest(ExtentTest test){
		TestBase.test = test;
	}
	
	@BeforeSuite
	public void setUp() throws Exception{
		System.out.println("logger changes");
		if(driver==null){
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
	         config.load(fis);
	         log.debug("config file loaded");
	         
	         fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
	         OR.load(fis);
	         log.debug("OR file loaded");
		}
		
		if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
			browser = System.getenv("browser");
		}
		else{
			browser = System.getProperty("browser");
		}
		config.getProperty("browser", browser);
		
		if(config.getProperty("browser").equals("Firefox")){
			driver = new FirefoxDriver();
			log.debug("Firefox launch");
		}
		else if(config.getProperty("browser").equals("chrome")){
			System.setProperty("webdriver.chrome.driver","E:\\Selenium\\DataDrivenFramework\\src\\test\\resources\\executable\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Chrome launch");
		}
		
		driver.get(config.getProperty("burl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 15);
	}
	
	

	
	public boolean isElementPrasent(By by){
		try{
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
				
	}
	
	public void click(String locator){
		if(locator.endsWith("_css")){
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		else if(locator.endsWith("_id")){
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		else if(locator.endsWith("_xpath")){
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		
		test.log(Status.INFO, "clicking on: "+locator);
	}
	
	public void type(String locator, String values){
		
        if(locator.endsWith("_css")){
        	driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(values);
		}
        else if(locator.endsWith("_xpath")){
        	driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(values);
		}
        else if(locator.endsWith("_id")){
        	driver.findElement(By.id(OR.getProperty(locator))).sendKeys(values);
        }
        
		
		test.log(Status.INFO, "Typing in "+ locator+" entered values : "+values);
	}
	 static WebElement dropdown;
	public void select(String locator, String values){
		 if(locator.endsWith("_css")){
	        	 dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			}
	        else if(locator.endsWith("_xpath")){
	        	dropdown =driver.findElement(By.xpath(OR.getProperty(locator)));
			}
	        else if(locator.endsWith("_id")){
	        	dropdown =driver.findElement(By.id(OR.getProperty(locator)));
	        }
		 Select s = new Select(dropdown);
		 s.selectByVisibleText(values);
		 
		 test.log(Status.INFO, "selecting from dropdown : "+locator+"values as "+values);
	}
	
	public static void verifyEquals(String exp, String act) throws Throwable{
		try{
		Assert.assertEquals(act, exp);
		}
		catch(Exception e){
			TestUtil.captureScreenShot();
		}
		
		}
	
	@DataProvider
	public Iterator<String[]>getData(Method m){
		String csvFileName = m.getAnnotation(Test.class).description();
		
		System.out.println("desc:"+csvFileName);
		
		if(csvFileName==null)
			csvFileName="error.csv";
		
		
		
		List<String[]> addCustomerData = new ArrayList<String[]>();
		try{
			CSVReader csvReader = new CSVReader(new FileReader("E:\\Selenium\\DataDrivenFramework\\src\\test\\resources\\CustomerDetails\\"+csvFileName));
			String[] values = csvReader.readNext();
			while(values!=null){
				values = csvReader.readNext();
				addCustomerData.add(values);
			}
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return addCustomerData.iterator();
		
	}
	@AfterSuite
	public void tearDown(){
		driver.quit();
		log.debug("test execution completed");
		
	}
	

}
