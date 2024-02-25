package com.qa.practice.Util;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.practice.qa.base.TestBase;

//Extends to Base Test also
public class TestNGLitners extends TestBase implements ITestListener {
//	ExtentReports extent;
//	ExtentTest test;

	private ExtentReports extent = TestBase.extentReport();
	private ExtentTest test;
	WebDriver driver;

	public TestNGLitners() throws IOException {
		super();
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));

	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		test.fail(result.getThrowable());

		// Attach screenshot to the report
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String screenshotPath = null;
		try {
			screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
			test.addScreenCaptureFromPath(screenshotPath); // Add this line to attach the screenshot
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.YELLOW));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		onTestFailure(result);
	}

}
