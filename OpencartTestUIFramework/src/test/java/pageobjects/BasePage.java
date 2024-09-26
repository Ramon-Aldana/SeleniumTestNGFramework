package pageobjects;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utilities.PageUtils;

public class BasePage {

	WebDriver driver;
	public PageUtils Utilities;

	public BasePage(WebDriver driver) {
		// The initElements method of PageFactory is used to initialize all elements in
		// a page object class.
		PageFactory.initElements(driver, this);
		this.Utilities = new PageUtils(driver);

		// Wait for page to load.
		Utilities.waitForPageToLoad();
		this.driver = driver;
	}

	// Single Responsibility Principle: the class is only responsible for managing
	// the HeaderSection and FooterSection instances
	private HeaderSection headerSection;
	private FooterSection footerSection;

	
	
	// Thread-Safe Singleton Pattern
	public FooterSection getFooterSection() {
		// double-checked locking pattern, which avoids the overhead of synchronization
		// after the object is initialized
		if (footerSection == null) {
			synchronized (this) {
				if (footerSection == null) {
					// TODO: Lacks null safety
					footerSection = new FooterSection(driver);
				}
			}
		}
		return footerSection;
	}

	// Thread-Safe Singleton Pattern
	public HeaderSection getHeaderSection() {
		// double-checked locking pattern, which avoids the overhead of synchronization
		// after the object is initialized
		if (headerSection == null) {
			synchronized (this) {
				if (headerSection == null) {
					// TODO: Lacks null safety
					headerSection = new HeaderSection(driver);
				}
			}
		}
		return headerSection;
	}

	public Map<String, String> QueryParametters() throws URISyntaxException {

		Map<String, String> qparams = extractQueryParameters(driver.getCurrentUrl());

		return qparams;
	}

	// Exception Handling: indicating that it can throw this specific checked
	// exception, which requires callers to handle the exception or declare it
	// further up the call stack.
	private Map<String, String> extractQueryParameters(String url) throws URISyntaxException {
		// The Map and HashMap use generics (<String, String>), allowing type-safe
		// storage of key-value pairs, which enhances code readability and reduces
		// runtime errors.
		Map<String, String> queryPairs = new HashMap<>();

		URI uri = new URI(url);
		String query = uri.getQuery(); // Get the query string

		// Conditional statements
		if (query != null) {
			// String manipulation:split(), and array indexing to manipulate and process
			// strings
			String[] pairs = query.split("&"); // Split the query string by '&'
			for (String pair : pairs) {
				String[] keyValue = pair.split("="); // Split each pair by '='
				String key = keyValue[0];
				String value = keyValue.length > 1 ? keyValue[1] : ""; // Handle cases with no value
				queryPairs.put(key, value); // Store in the HashMap. Demonstrating how to insert data into collections.
			}
		}

		return queryPairs; // Return the populated HashMap
	}
}
