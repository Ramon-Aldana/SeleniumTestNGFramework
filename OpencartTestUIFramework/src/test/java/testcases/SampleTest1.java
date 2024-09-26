package testcases;

import org.testng.annotations.Test;


public class SampleTest1  {



    @Test
    public void testMethod1_1() {
        System.out.println("Executing Test Method 1");
    }

    @Test
    public void testMethod1_2() {
        System.out.println("Executing Test Method 2");
        assert false : "Test Method 2 failed"; // Example failure
    }

    @Test
    public void testMethod1_3() {
        System.out.println("Executing Test Method 3");
    }






}


