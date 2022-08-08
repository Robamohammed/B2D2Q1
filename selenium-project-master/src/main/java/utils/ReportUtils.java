package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtils {

	ExtentReports extent;

	ExtentSparkReporter spark;

	ExtentTest extentTest;

	public ReportUtils(String filename) {

		extent = new ExtentReports();

		spark = new ExtentSparkReporter(filename);

		spark.config().setDocumentTitle("Renew Buy Report");

		extent.attachReporter(spark);

	}

	public void createTestCase(String name, String description) {

		extentTest = extent.createTest(name, description);

	}

	public void addLogs(String status, String logMessage) {

		if (status.equalsIgnoreCase("warn")) {

			extentTest.log(Status.WARNING, logMessage);

		} else if (status.equalsIgnoreCase("pass")) {

			extentTest.log(Status.PASS, logMessage);

		} else if (status.equalsIgnoreCase("fail")) {

			extentTest.log(Status.FAIL, logMessage);

		} else if (status.equalsIgnoreCase("skip")) {

			extentTest.log(Status.SKIP, logMessage);

		} else {

			extentTest.log(Status.INFO, logMessage);
		}

	}
	
	public void addScreenshots(String filname) {
		
		filname = filname.trim();
		
		extentTest.addScreenCaptureFromPath(filname);
		
	}

	public void closeReport() {

		extent.flush();

	}

}
