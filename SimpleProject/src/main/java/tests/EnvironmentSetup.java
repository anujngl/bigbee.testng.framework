package tests;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import interfaces.ITestReporter;
import reporting.Reporter;
import utils.ConfigurationReader;
import utils.ScreenCapture;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EnvironmentSetup {
	
	protected ITestReporter testReporter;
	WebDriver browser;
	ConfigurationReader Config = new ConfigurationReader();
	ScreenCapture SS = new ScreenCapture();
	
	@Parameters({"browser"})
	@BeforeTest
	public void classSetUp(String browserName)
	{
		testReporter = Reporter.getTestReporter();
		
		if (browserName.equalsIgnoreCase("firefox")){
			System.out.println(System.getProperty("user.dir")+"/"+Config.getFirefoxDriver());
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/"+Config.getFirefoxDriver());
			browser = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/"+Config.getChromeDriver());
			browser = new ChromeDriver();			
		}else if (browserName.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/"+Config.getIEDriver());
			browser = new InternetExplorerDriver();			
		}
		
		browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		
	}
	
	
	
	@Parameters({"testId"})
	@BeforeMethod
	public synchronized void methodSetup(Method caller,String testId)
	{
		
		testReporter.startTest(getTestName(caller), getTestDescription(caller));
	}
	
	@AfterMethod
	public synchronized void afterMethod()
	{
		testReporter.endTest();
		Reporter.flushReport();
	}
	
	@AfterTest
	public void closeDriver()
	{
		browser.quit();
	}

	private String getTestName(Method caller)
	{
		Test testAnnotation = caller.getAnnotation(Test.class);
		if(testAnnotation != null)
		{
			return testAnnotation.testName();
		}
		return "";
	}
	
	private String getTestDescription(Method caller)
	{
		Test testAnnotation = caller.getAnnotation(Test.class);
		if(testAnnotation != null)
		{
			return testAnnotation.description();
		}
		return "";
	}
}
