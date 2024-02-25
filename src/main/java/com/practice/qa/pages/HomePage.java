package com.practice.qa.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.practice.qa.base.TestBase;

import net.bytebuddy.asm.Advice.Enter;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

public class HomePage extends TestBase {
	


	public HomePage() throws IOException {
		super();
		PageFactory.initElements(driver,this);
	}
	
	

	@FindBy(xpath = "//img[@title='Flipkart']")
	WebElement flipkartIcon;
	@FindBy(xpath = "//div[@class='_3NorZ0 _3jeYYh']")
	WebElement searchBox;
	@FindBy(xpath = "//button[@title='Search for Products, Brands and More'] //*[name()='svg']")
	WebElement searchIcon;
	@FindBy(xpath = "//input[@title='Search for Products, Brands and More']")
	WebElement searchBoxTitle;

	@FindBy(xpath = "//li[@class='_3D0G9a']")
	List<WebElement> SuggestionText;

	@FindBy(xpath = "//a[@href='/account/login?ret=/']")
	WebElement loginButton;
	@FindBy(xpath = "//img[@class='-dOa_b L_FVxe']")
	WebElement loginButtonIcon;
	@FindBy(xpath = "//img[@class='-dOa_b XdYXbi']")
	WebElement loginButtonArrowIcon;
	@FindBy(xpath = "//ul[@class='_3YjYK7 ecs1XG']")
	WebElement LoginButtonPopup;

	 @FindBy(xpath = "//ul[@class='_3YjYK7 ecs1XG']/a") List<WebElement>
	 loginPopupforBrockenLink;

	@FindBy(xpath = "//a[@class='_2Udqwk'] /span[1]")
	WebElement loginPopupTitle;
	@FindBy(xpath = "//span[@class='_1Mikcj']")
	WebElement loginPopupSignUp;

	@FindBy(xpath = "//a[@href='/account/?rd=0&link=home_account']/li")
	WebElement loginPopupMyProfileOption;
	@FindBy(xpath = "//a[@href='/account/?rd=0&link=home_account']//img")
	WebElement loginPopupMyProfileIcon;
	@FindBy(css = "a[title='Flipkart Plus Zone']")
	WebElement flipkartPlusOption;
	@FindBy(css = "a[title='Flipkart Plus Zone'] div")
	WebElement flipkartPlusOptionIcon;

	@FindBy(xpath = "//a[@title='Orders']")
	WebElement loginPopupOrderOption;
	@FindBy(xpath = "//a[@title='Orders']/li/div/img")
	WebElement loginPopupOrderOptionIcon;
	@FindBy(xpath = "//a[@title='Wishlist']")
	WebElement loginPopupWishlistOption;
	@FindBy(xpath = "//a[@title='Wishlist']/li/div/img")
	WebElement loginPopupWishlistOptionIcon;
	@FindBy(xpath = "//a[@title='Rewards']")
	WebElement loginPopupRewardOption;
	@FindBy(xpath = "//a[@title='Rewards']/li/div")
	WebElement loginPopupRewardOptionIcon;
	@FindBy(xpath = "//a[@title='Gift Cards']")
	WebElement loginPopupGiftCardOption;
	@FindBy(xpath = "//a[@title='Gift Cards']/li/div")
	WebElement loginPopupGiftCardOptionIcon;

	@FindBy(css = "div[class='_3skCyB']")
	WebElement loginPopup;
	@FindBy(css = "span[role='button']")
	WebElement loginPopupCrossIcon;

	public void validateFlipkartLogo() throws InterruptedException { 
		Thread.sleep(2000);
		Assert.assertTrue(flipkartIcon.isDisplayed(), "Flipkart logo is not present on the page");                                                     
	}
	
	public void ValidateSearchBox() {
		 
		Assert.assertTrue(searchBox.isDisplayed(), "Search Box is not displayed");
		Assert.assertTrue(searchBox.isEnabled(), "Search Box is not enabled");
	}

	public void validateSearchIcon() {
		Assert.assertTrue(searchIcon.isDisplayed(), "Search Icon is not displayed");
	}

	public void ValidateSearchBoxTitle() {
		Assert.assertEquals(searchBoxTitle.getAttribute("title"), "Search for Products, Brands and More");
	}

	public void SearchBoxSearchFunctionality() throws IOException, InterruptedException {

		searchBoxTitle.click();
		searchBoxTitle.sendKeys("Jeans");
		Thread.sleep(2000);

		boolean areUnrelated = true;

		for (int i = 0; i < SuggestionText.size(); i++) {
			WebElement suggestion = SuggestionText.get(i);
			if (suggestion.getText().contains("jeans")) {
				areUnrelated = false;
				break;
			}

		}
		searchBoxTitle.sendKeys(Keys.ENTER);
	}

	public void validateLoginButton() throws InterruptedException {
		Assert.assertTrue(loginButton.isDisplayed(), "Login Button is not Present");
		Assert.assertTrue(loginButtonIcon.isDisplayed(), "Login Button Icon is not Present");
		Assert.assertTrue(loginButtonArrowIcon.isDisplayed(), "Login Button Arrow Icon is not Present");

	}

	public void validateLoginPopup()
			throws InterruptedException, MalformedURLException, IOException, URISyntaxException {
		Actions a = new Actions(driver);
		a.moveToElement(loginButton).build().perform();
		Thread.sleep(2000);
		Assert.assertTrue(LoginButtonPopup.isDisplayed(), "Login Button Popup is not present");
			
		Assert.assertEquals(loginPopupTitle.getText(), "New customer?");
		
		Assert.assertTrue(loginPopupSignUp.isDisplayed(), "SignU Option Not Displayed");

		
		loginPopupSignUp.click();
		String signUpPageUrl = "https://www.flipkart.com/account/login?signup=true&ret=/";
		Assert.assertEquals(driver.getCurrentUrl(), signUpPageUrl, "SignUp Url does not match");
		driver.navigate().back();

		try {

			if (loginPopup.isDisplayed()) {
				loginPopupCrossIcon.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		a.moveToElement(loginButton).build().perform();
		loginPopupMyProfileOption.click();
		String myProfile = "https://www.flipkart.com/account/login?ret=%2Faccount%2F%3Frd%3D0%26link%3Dhome_account";
		Assert.assertEquals(driver.getCurrentUrl(), myProfile, "My Profile Url does not match");
		driver.navigate().back();

		try {

			if (loginPopup.isDisplayed()) {
				loginPopupCrossIcon.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		a.moveToElement(loginButton).build().perform();
		Assert.assertTrue(flipkartPlusOptionIcon.isDisplayed(), "Flipkart Plus option Icon is not displayed");
		flipkartPlusOption.click();
		String flipkartPlusUrl = "https://www.flipkart.com/plus";
		Assert.assertEquals(driver.getCurrentUrl(), flipkartPlusUrl, "Flipkart Plus Url does not match");
		driver.navigate().back();

		try {

			if (loginPopup.isDisplayed()) {
				loginPopupCrossIcon.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		a.moveToElement(loginButton).build().perform();
		Assert.assertTrue(loginPopupOrderOptionIcon.isDisplayed(), "Order option Icon is not displayed");
		loginPopupOrderOption.click();
		String orderUrl = "https://www.flipkart.com/account/login?ret=%2Faccount%2Forders%3Flink%3Dhome_orders&fromMyOrdersPage=true";
		Assert.assertEquals(driver.getCurrentUrl(), orderUrl, "Order Url does not match");
		driver.navigate().back();

		try {

			if (loginPopup.isDisplayed()) {
				loginPopupCrossIcon.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		a.moveToElement(loginButton).build().perform();
		Assert.assertTrue(loginPopupWishlistOptionIcon.isDisplayed(), "Wishlist option Icon is not displayed");
		loginPopupWishlistOption.click();
		String wishlistUrl = "https://www.flipkart.com/account/login?ret=%2Fwishlist%3Flink%3Dhome_wishlist";
		Assert.assertEquals(driver.getCurrentUrl(), wishlistUrl, "Wishlist Url does not match");
		driver.navigate().back();

		try {

			if (loginPopup.isDisplayed()) {
				loginPopupCrossIcon.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		a.moveToElement(loginButton).build().perform();
		Assert.assertTrue(loginPopupRewardOptionIcon.isDisplayed(), "Reward option Icon is not displayed");
		loginPopupRewardOption.click();
		String rewardUrl = "https://www.flipkart.com/account/login?ret=%2Faccount%2Frewards%3Flink%3Dhome_rewards";
		Assert.assertEquals(driver.getCurrentUrl(), rewardUrl, "Reward Url does not match");
		driver.navigate().back();

		try {

			if (loginPopup.isDisplayed()) {
				loginPopupCrossIcon.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		a.moveToElement(loginButton).build().perform();
		Assert.assertTrue(loginPopupGiftCardOptionIcon.isDisplayed(), "GiftCard option Icon is not displayed");
		loginPopupGiftCardOption.click();
		String giftCardUrl = "https://www.flipkart.com/the-gift-card-store?link=home_giftcard";
		Assert.assertEquals(driver.getCurrentUrl(), giftCardUrl, "GiftCard Url does not match");
		driver.navigate().back();

		// Aditya se puchna hai

//		SoftAssert a1 = new SoftAssert();
//		
//		for (WebElement link : loginPopupforBrockenLink) {
//			String url = link.getAttribute("href"); 
//			HttpURLConnection conn = (HttpURLConnection) new URI(url).toURL().openConnection();
//			conn.setRequestMethod("HEAD");
//			conn.connect(); 
//			int respCode = conn.getResponseCode();
//			System.out.println(respCode);
//			Thread.sleep(2000);
//			a1.assertFalse(respCode>400, ("The link with text (" + link.getText() + ")is brocken  with code -" + " "+ respCode));
//
//			}
//			a1.assertAll();

		a.moveToElement(loginButton).build().perform();
		Assert.assertTrue(loginPopupMyProfileOption.isDisplayed(), "My Profile option is not displayed");
		Assert.assertTrue(loginPopupMyProfileIcon.isDisplayed(), "My Profile Icon is not displayed");
		System.out.println(loginPopupMyProfileOption.getText());
		Assert.assertEquals(loginPopupMyProfileOption.getText(), "My Profile");

	}

}
