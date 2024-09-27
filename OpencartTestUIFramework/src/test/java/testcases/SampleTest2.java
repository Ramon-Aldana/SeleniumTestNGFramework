package testcases;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;


public class SampleTest2 extends SampleBaseTest {


    
    @Test
    public void testMethod2_1() {
        System.out.println("Executing Test testMethod2_1");
        System.out.println("Browser :" + ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase());
    }



    @Test
    public void testMethod2_2() {
        System.out.println("Executing TesttestMethod2_1");
   
    }



	@Override
	protected void afterConstruction() {
		// TODO Auto-generated method stub
		logger.info("in afterConstruction() for SampleTest2");
		driver.get("http://www.amazon.com");
	}






}


