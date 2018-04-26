package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import actions.ConnectExcel;
import pages.LoginPage;

public class Test01<Workbook, Sheet> extends EnvironmentSetup {

	@Test(testName = "Launch Google & Search some text", description = "This is a mock test to verify the framework")
	public void LaunchGoogle() throws InterruptedException {
		browser.get("https://mtf.mea.customer.mastercard.com/mpts/eaq/app/");
		String getdataval = ConnectExcel.retrievedata("TS1");
		String[] splitval = getdataval.split("@@");
		LoginPage LoginPage = new LoginPage(browser,testReporter);
		LoginPage.login(splitval[208], splitval[209]);	
	}
	
		
	
	 			   
	
	
}
