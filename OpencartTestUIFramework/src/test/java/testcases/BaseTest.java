package testcases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	protected static WebDriver driver;
	protected Logger logger; // Make sure to import org.apache.logging.log4j.Logger;
	protected Properties properties;
	
	//Multiple Declarations: The syntax used allows for the declaration of multiple variables of the same type in a single line, which helps in keeping the code concise
	String os, browser;

	
	@BeforeClass
	public void setupBeforeClass() throws IOException {
		System.out.println("Setup before any methods in class.");

		logger = LogManager.getLogger(this.getClass()); // Make sure to import org.apache.logging.log4j.LogManager;
		driver = new ChromeDriver();
		properties = new Properties();
		
		FileReader propFile = new FileReader("./src//test//resources//config.properties");
		properties.load(propFile);
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(properties.getProperty("AppURL")); //Read from properties file

		//// Template Method Pattern: Call the method that can be overridden by
		//// subclasses
		afterConstruction();

	}

	// Template Method Pattern: Define a protected method in the superclass that
	// will be called at the end of the superclass constructor.
	//// Abstract method to force subclass implementation
	protected abstract void afterConstruction();

	public static String captureScreenshot(String name) {
		String timeStamp = new SimpleDateFormat("yyyMMddhhmmss").format(new Date());
		TakesScreenshot camera = (TakesScreenshot)driver;
		File sourceFile = camera.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		
		return targetFilePath;
	}
	
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
