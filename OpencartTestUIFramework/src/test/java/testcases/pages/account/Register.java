package testcases.pages.account;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageobjects.HeaderSection;
import pageobjects.OCForm;
import pageobjects.RegisterAccountPage;
import testcases.BaseTest;

public class Register extends BaseTest {
	
	RegisterAccountPage register;
	
	//Template Method Pattern: Override this method in the subclass to provide specific behavior.
	@Override
	protected void afterConstruction() {
		// Custom behavior for the subclass
		System.out.println("Custom behavior in subclass after superclass constructor");
		register = new RegisterAccountPage(driver);
	}
	
	/*
	Clarity: Starting with test makes it clear that the method is a test case.
	Framework Compatibility: Many testing frameworks recognize methods prefixed with test as test cases automatically.
	Consistent Structure: Following a consistent naming pattern improves readability and understanding.
	Alternative Naming Conventions: Consider behavior-driven naming (e.g., should<ExpectedBehavior>) or descriptive names for better clarity.
	Consistency is Key: Choose a naming convention that aligns with your team’s standards and apply it consistently across all test cases.
	*/
	@Test
	public void testValidUserRegistration() {
		logger.info("Get Header Section to navigate to register page");
		HeaderSection header = register.getHeaderSection();
		
		// Here we get back WebElement. We could introduce Bot pattern used on another test.
		logger.info("Click on My Account");
		header.MyAccount.click();
		logger.info("Click on Register");
		header.Register.click();
		
		logger.info("Get generic OpenCart Bot form");
		OCForm form = register.getOCForm();
		
		/*
		 * Using com.github.javafaker allows 
		 * Generates realistic fake data such as names, addresses, and phone numbers for testing purposes.
		 * Enhances test case flexibility by avoiding hardcoded values and providing dynamic data.
		 * Supports multiple locales, making it useful for testing applications in different regions.
		 */
		
		logger.info("Get Faker instance to fake data");
		Faker fakeData = new Faker();
		
		//BOT pattern (calling .SetField to encapsulate the field's action).
		//Not recommended for maintainability of test cases, but it could unblock testers while the full POM component is implemented.
		form.SetField("First Name",fakeData.name().firstName());
		form.SetField("Last Name",fakeData.name().lastName());
		form.SetField("E-Mail",fakeData.internet().emailAddress(null));
		form.SetField("Password",fakeData.internet().password());
	}

}
