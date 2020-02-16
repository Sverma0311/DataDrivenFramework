package com.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws Exception {
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
         config.load(fis);
        // System.out.println(System.getProperty("user.dir")); = E:\\Selenium\\DataDrivenFramework
         config.getProperty("browser");
         
         Properties OR = new Properties();
         fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
         OR.load(fis);
         
	}
	

}
