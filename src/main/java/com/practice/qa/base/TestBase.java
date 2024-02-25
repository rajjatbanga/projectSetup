	package com.practice.qa.base;
	
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;
	
	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.testng.Assert;
	import org.testng.annotations.Test;
	
	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	
	public class TestBase {
	
		public static WebDriver driver;
		public static Properties prop;
		static ExtentReports extent;
	
		public TestBase() throws IOException {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\rajja\\eclipse-workspace\\AutomationE2E\\src\\main\\java\\com\\practice\\qa\\config\\config.properties");
			prop.load(ip);
		}
	
		public void launchBrowser() {
	
			String browserName = prop.getProperty("browser");
	
			if (browserName.equals("chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equals("ie")) {
				driver = new InternetExplorerDriver();
			} else {
				System.out.println("Browser name is not given");
			}
			driver.manage().window().maximize();
			driver.get(prop.getProperty("url"));
			//Assert.assertTrue(true);
		}
		
		public static ExtentReports extentReport() {//Make Method Static And Add ExtentReports text before method name
			String path = System.getProperty("user.dir")+"\\Reports\\index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path); 
			reporter.config().setReportName("Test Report");
			reporter.config().setDocumentTitle("Test Result");
			extent = new ExtentReports();  
			extent.attachReporter(reporter);  
			return extent;
		}
		
	   
	    
	    public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        File file = new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator + testCaseName + ".png");
	        FileUtils.copyFile(source, file);
	        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	    }
	
	
	
	}
