package testcases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public abstract class BaseTest {

	// protected allows subclasses to access the fields directly, enabling them to
	// inherit and utilize those fields as needed.
	// Framework Design: Using protected fields can make it easier for developers to
	// create subclasses that require access to certain fields without needing
	// additional accessor methods
	protected WebDriver driver;
	protected Logger logger; // Make sure to import org.apache.logging.log4j.Logger;
	
	
	//Multiple Declarations: The syntax used allows for the declaration of multiple variables of the same type in a single line, which helps in keeping the code concise
	String os, browser;

	
	@BeforeClass
	public void setupBeforeClass() {
		System.out.println("Setup before any methods in class.");

		logger = LogManager.getLogger(this.getClass()); // Make sure to import org.apache.logging.log4j.LogManager;
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://localhost/oc/index.php");

		//// Template Method Pattern: Call the method that can be overridden by
		//// subclasses
		afterConstruction();

	}

	// Template Method Pattern: Define a protected method in the superclass that
	// will be called at the end of the superclass constructor.
	//// Abstract method to force subclass implementation
	protected abstract void afterConstruction();

	@BeforeMethod
	public void setupBeforeMethod() {
		System.out.println("Setup before each test method.");
	}

	@AfterMethod
	public void teardownAfterMethod() {
		System.out.println("Teardown after each test method.");
	}

	@AfterClass
	public void teardownAfterClass() {
		System.out.println("Teardown after all methods in class.");
		if (driver != null) {
			driver.quit();
		}
	}
}
