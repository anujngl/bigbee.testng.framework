package tests;

import org.testng.annotations.Test;
import pages.LoginPage;

public class Test01 extends EnvironmentSetup {

	@Test(testName = "Launch Google & Search some text", description = "This is a mock test to verify the framework")
	public void LaunchGoogle() throws InterruptedException {
	
		browser.get("https://mtf.mea.customer.mastercard.com/mpts/eaq/app/");
		LoginPage LoginPage = new LoginPage(browser,testReporter);
		LoginPage.login("raktest1", "1234");
		
	}
}
