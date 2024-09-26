package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SampleTest2 {


    @BeforeClass
    public void setupBeforeClass() {
        System.out.println("Setup before any methods in this class.");
    }

    @BeforeMethod
    public void setupBeforeMethod() {
        System.out.println("Setup before each test method.");
    }

    @Test
    public void testMethod2_1() {
        System.out.println("Executing Test Method 1");
    }



    @Test
    public void testMethod2_3() {
        System.out.println("Executing Test Method 3");
    }

    @AfterMethod
    public void teardownAfterMethod() {
        System.out.println("Teardown after each test method.");
    }

    @AfterClass
    public void teardownAfterClass() {
        System.out.println("Teardown after all methods in this class.");
    }


}


