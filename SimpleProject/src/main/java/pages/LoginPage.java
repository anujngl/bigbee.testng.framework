package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import actions.Keywords;
import interfaces.ITestReporter;

public class LoginPage {
	
	private static Keywords Keywords;
	private static WebDriver driver;
	private static ITestReporter testReporter;

	public LoginPage(WebDriver browserInst, ITestReporter reporter) {
		driver = browserInst;
		testReporter = reporter;
		Keywords = new Keywords(driver,testReporter);
	}
	
	By uname = By.xpath("//*[@id='userid']");
	By pwd = By.xpath("//*[@id='password']");
	By LoginButton = By.xpath("//*[@id='id2']");
	
	public void login(String username,String password) {
		Keywords.enterData(uname, username,"Username is entered");
		Keywords.enterData(pwd, password,"Password is entered");
		Keywords.clickElement(LoginButton,"Login button is clicked");
	}
}
