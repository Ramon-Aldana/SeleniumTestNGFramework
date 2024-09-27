package testcases;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public abstract class SampleBaseTest {

	// protected allows subclasses to access the fields directly, enabling them to
	// inherit and utilize those fields as needed.
	// Framework Design: Using protected fields can make it easier for developers to
	// create subclasses that require access to certain fields without needing
	// additional accessor methods
	protected WebDriver driver;
	protected Logger logger; // Make sure to import org.apache.logging.log4j.Logger;
	protected Properties properties;

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setupBeforeClass(String os, String browser) throws IOException, URISyntaxException {
		System.out.println("Setup before any methods in class.");
		logger = LogManager.getLogger(this.getClass()); // Make sure to import org.apache.logging.log4j.LogManager;

		properties = new Properties();
		FileReader propFile = new FileReader("./src//test//resources//config.properties");
		properties.load(propFile);

		logger.info("About to get Driver: " + browser);

		if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			switch (os.toLowerCase()) {
			case "windows":
				capabilities.setPlatform(Platform.WIN11);
				break;
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			case "linux":
				capabilities.setPlatform(Platform.LINUX);
				break;
			default:
				System.out.println("No matching os: " + os);
				return;
			}
			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName(Browser.CHROME.browserName());
				break;
			case "edge":
				capabilities.setBrowserName(Browser.EDGE.browserName());
				break;
			case "firefox":
				capabilities.setBrowserName(Browser.FIREFOX.browserName());
				break;
			default:
				System.out.println("No matching browser: " + browser);
				return;
			}

			driver = new RemoteWebDriver((new URI(properties.getProperty("hubURL")).toURL()), capabilities);
		} else {

			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				logger.info("Invalid Driver");
				return;
			}
		}
		
		logger.info("manage().deleteAllCookies()");
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("manage().window().maximize()");
		driver.manage().window().maximize();
		//// Template Method Pattern: Call the method that can be overridden by
		//// subclasses
		logger.info("call afterConstruction()");
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
