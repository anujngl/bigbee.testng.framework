package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.LogStatus;
import interfaces.ITestReporter;
import utils.ScreenCapture;

public class Keywords {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static ITestReporter testReporter;
	private static ScreenCapture web;
	
	public Keywords(WebDriver driverInst,ITestReporter reporter) {
		driver = driverInst;
		testReporter = reporter;
		wait = new WebDriverWait(driver, 3);
		web = new ScreenCapture();
	}
	
	public void clickElement(By elementLoc,String printResult) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLoc));
		    driver.findElement(elementLoc).click();
		    testReporter.log(LogStatus.PASS, printResult);
		}catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to click on the web element, Reason: " + e.getMessage());
		}
	}

	public void enterData(By elementLoc, String dataval,String printResult) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLoc));
		    driver.findElement(elementLoc).sendKeys(dataval);
		    testReporter.log(LogStatus.PASS, printResult);
		    testReporter.log(LogStatus.PASS, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		}catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "System encountered a failure while trying to enter the data " + dataval + " into the web element, Reason:" + e.getMessage());
			testReporter.log(LogStatus.FAIL, testReporter.addScreenCapture(web.GetScreenshot(driver)));
		}
	}
}
