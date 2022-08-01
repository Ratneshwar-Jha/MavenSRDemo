package com.hcl.smart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSRTest {

	//public static WebDriver driver;
	//public static Properties prop;

	/*
	 * public void setupDrivers(String browserName) {
	 * browserName=prop.getProperty("Browser");
	 * if(browserName.equalsIgnoreCase("chrome")) {
	 * WebDriverManager.chromedriver().setup(); driver= new ChromeDriver(); } else
	 * if (browserName.equalsIgnoreCase("firefox")) {
	 * WebDriverManager.firefoxdriver().setup(); driver= new FirefoxDriver(); } else
	 * if (browserName.equalsIgnoreCase("Edge")) {
	 * WebDriverManager.edgedriver().setup(); driver= new EdgeDriver(); } else {
	 * WebDriverManager.chromedriver().setup(); driver= new ChromeDriver(); } }
	 */
	@BeforeTest
	public void beforeTest() throws Exception
	{
		BaseTest.prop= new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\ratneshwar.jha\\eclipse-workspace\\MavenSRDemo\\Config\\global.properties");
		BaseTest.prop.load(fis);
		System.out.println("Running on Browser: " +BaseTest.prop.getProperty("Browser"));
		BaseTest.setupDrivers("Browser");

		//prop.getProperty("BrowserName")



	}



	@Test
	public void launchSR() throws Exception
	{
		BaseTest.driver.get(BaseTest.prop.getProperty("Url"));
		String ExpectedTitle= "Smart Recruit - Ver3.0 (HTML5)";
		String ActualTitle= BaseTest.driver.getTitle();
		System.out.println(ActualTitle);
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		Thread.sleep(3000);
	}

	@Test
	public void loginSR() throws Exception
	{
		BaseTest.driver.findElement(By.id("EmployeeCode")).sendKeys(BaseTest.prop.getProperty("EmployeeCode"));
		Thread.sleep(2000);
		BaseTest.driver.findElement(By.id("Password")).sendKeys(BaseTest.prop.getProperty("Password"));
		BaseTest.driver.findElement(By.id("EmployeeCode")).click();
		BaseTest.driver.findElement(By.id("SubmitButton")).click();
		String ExpectedTitle= "Smart Recruit - Ver3.0 (HTML5)";
		String ActualTitle= BaseTest.driver.getTitle();
		System.out.println(ActualTitle);
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		Thread.sleep(3000);

	}

	@AfterTest
	public void AfterTest()
	{
		BaseTest.driver.quit();
	}
}