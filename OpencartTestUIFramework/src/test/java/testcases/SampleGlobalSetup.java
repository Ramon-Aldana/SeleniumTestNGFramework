package testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SampleGlobalSetup {

    @BeforeTest
    public void setUpBeforeTest() {
        // This method runs before any test method in the test suite is executed.
        System.out.println("Setting up before all tests in the Test1 group");
    }
    
    @AfterTest
    public void teardownAfterTest() {
        System.out.println("Teardown after all test methods in this <test> tag.");
    }
}
