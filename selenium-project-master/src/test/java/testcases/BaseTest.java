package testcases;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import commonLibs.CommonDriver;
import commonLibs.ScreenshotControl;
import samplePages.HomePage;
import utils.ConfigUtils;
import utils.DateTimeUtils;
import utils.ReportUtils;

public class BaseTest {
	
	
	CommonDriver cmnDriver;

	WebDriver driver;
	
	HomePage homepage;

	ReportUtils reportUtils;

	private static String projectDirectory;

	private String reportName;

	ScreenshotControl screenshotControl;


	static Properties configProperties;

	private static String configFilename;


	static {
		
		projectDirectory = System.getProperty("user.dir");

		
		configFilename = String.format("%s/config/%s", projectDirectory, "config.properties");

		

		try {

			configProperties = ConfigUtils.readconfig(configFilename);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeSuite(alwaysRun = true)
	public void initialise() {

		reportName = String.format("%s/reports/test-report.html", projectDirectory);

		reportUtils = new ReportUtils(reportName);

		reportUtils.createTestCase("Initialise during Before Suite", "Created report instances");

		reportUtils.addLogs("info", "Initialised the report successfully");
	}

	@BeforeClass(alwaysRun = true)
	public void preSetup() {

		System.out.println("First method executed in a class");

	}

	@BeforeMethod(alwaysRun = true)
	public void setup() throws Exception {

		reportUtils.createTestCase("Initialise Before Every testcase", "This section inialise browser and page");

		openBrowserAndNavigateToAUrl();

		initializeDriverInstance();

		pageInitialization();

	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp(ITestResult testResult) throws Exception {

		if (testResult.getStatus() == ITestResult.SUCCESS) {
			reportUtils.addLogs("pass", "All test steps passed ");
		} else if (testResult.getStatus() == ITestResult.FAILURE) {

			takeScreenshotAndaddToReport(testResult);

			reportUtils.addLogs("fail", "One or more test step failed");
		} else {
			reportUtils.addLogs("skip", "Test steps skipped");
		}

		closeBrowerInstance();

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {

		System.out.println("last method executed in a class");

	}

	@AfterSuite(alwaysRun = true)
	public void cleanupAfterSuite() {

		reportUtils.closeReport();
	}

	private void openBrowserAndNavigateToAUrl() throws Exception {
		cmnDriver = new CommonDriver(configProperties.getProperty("browserType"));

		cmnDriver.navigateToUrl(configProperties.getProperty("baseUrl"));

		reportUtils.addLogs("info", "Initialised the browser successfully");

	}

	private void pageInitialization() {
		homepage = new HomePage(driver);

		reportUtils.addLogs("info", "Initialised the pages successfully");

	}

	private void initializeDriverInstance() {
		driver = cmnDriver.getDriver();

		screenshotControl = new ScreenshotControl(driver);

		reportUtils.addLogs("info", "Initialised the driver instance successfully");
	}

	private void closeBrowerInstance() throws Exception {

		cmnDriver.closeAllBrowser();

		reportUtils.addLogs("info", "Closed the browser instance successfully");
	}

	private void takeScreenshotAndaddToReport(ITestResult testResult) throws Exception {

		String testcasename = testResult.getName();

		String testFailureTime = DateTimeUtils.getCurrentDateAndTime();

		String screenshotFilename = String.format("%s/screenshots/%s-%s.png", projectDirectory, testcasename,
				testFailureTime);

		screenshotControl.captureAndSaveScreenshot(screenshotFilename);

		reportUtils.addScreenshots(screenshotFilename);

	}

}
