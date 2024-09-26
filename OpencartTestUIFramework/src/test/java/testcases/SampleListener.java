package testcases;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/*
Observer Pattern:
Subject: The TestNG framework (the test execution engine) acts as the subject, which triggers events during test execution (start, pass, fail, skip, etc.).
Observers: The TestListener or any class that implements TestNG's listener interfaces (like ITestListener, ISuiteListener, etc.) observes these events.
Notifications: When certain test events occur (e.g., test starts, test finishes, test fails), the listener receives notifications and can execute custom logic based on these events.
 */

public class SampleListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("LISTENER(SampleListener) : Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("LISTENER(SampleListener) : Test succeeded: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("LISTENER(SampleListener) : Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("LISTENER(SampleListener) : Test skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("LISTENER(SampleListener) : All tests finished in context: " + context.getName());
    }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		 System.out.println("LISTENER(SampleListener) : Tests started in context: " + context.getName());
		
	}
}
