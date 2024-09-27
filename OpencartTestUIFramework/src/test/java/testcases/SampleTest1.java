package testcases;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;


public class SampleTest1 extends SampleBaseTest {



    @Test
    public void testMethod1_1() {
        System.out.println("Executing Test Method 1");
        System.out.println("Browser :" + ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase());
    }

    @Test
    public void testMethod1_2() {
        System.out.println("Executing Test Method 2");
        
    }

    @Test
    public void testMethod1_3() {
        System.out.println("Executing Test Method 3");
    }

	@Override
	protected void afterConstruction() {
		logger.info("in afterConstruction() for SampleTest1");
		driver.get("http://www.google.com");
		logger.info("Website 1 Title: " + driver.getTitle());
		
	}






}


