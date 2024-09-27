package utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcases.BaseTest;

public class ExtentReportManager implements ITestListener {


	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	String repName;

	public void onStart(ITestContext testContext) {

		//Create unique report name
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "UIReport" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		
		
		sparkReporter.config().setDocumentTitle("Opencart UI Test Automation-DocumentTitle"); 
		sparkReporter.config().setReportName("Opencart UI Test Automation-ReportName"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart-Application");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "DEV");
		extent.setSystemInfo("user", "REAB");
		
		//Get more context specific settings.
		//Object x = testContext.getCurrentXmlTest().?;
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		
		logInfo("FLUSH : " + sparkReporter.getFile());
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		logInfo("START :" + result.getName());
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " Succeeded.");
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " Failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " Failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());

		
		try{
			String imPath = BaseTest.captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(imPath);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void logInfo(String log) {
		String logline = "----------------- " + log + "---------------------";
		System.out.println(logline);
		System.out.flush();		
	}
}
