package com.practice.qa.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.qa.base.TestBase;
import com.practice.qa.pages.HomePage;

public class LoginPageTest extends TestBase{
	public static HomePage lp;

	public LoginPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void browserlaunching() throws IOException {
		launchBrowser();
	}

	@Test(priority = 1)
	public void VerifyHeader() throws InterruptedException, IOException {
		lp = new HomePage();
		lp.validateFlipkartLogo();
		lp.ValidateSearchBox();
		lp.ValidateSearchBoxTitle();
		lp.validateSearchIcon();
	}

	@Test(priority = 2)
	public void ValidateSearchFeature() throws IOException, InterruptedException {
		lp = new HomePage();
		lp.SearchBoxSearchFunctionality();
	}

	@Test(priority = 3)
	public void validateLoginButton() throws IOException, InterruptedException {
		lp = new HomePage();
		lp.validateLoginButton();
	}
	
	@Test(priority = 4)
	public void loginPopup() throws IOException, InterruptedException, URISyntaxException {
		lp = new HomePage();
		lp.validateLoginPopup();
	}

	@AfterMethod
	public void browserQuit() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
