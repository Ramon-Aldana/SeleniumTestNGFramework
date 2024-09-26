package testcases.pages.account;

import org.testng.annotations.Test;

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
		
		HeaderSection header = register.getHeaderSection();
		
		// Here we get back WebElement. We could introduce Bot pattern used on another test.
		header.MyAccount.click();
		header.Register.click();
		
		//BOT pattern (calling .SetField to encapsualte the field's action).
		//Not recommended for maintainability of test cases, but it could unblock testers while the full POM component is implemented.
		OCForm form = register.getOCForm();
		form.SetField("First Name","Ramon");
		form.SetField("Last Name","Aldana");
		form.SetField("E-Mail","ramon.aldana@opencart.com");
		form.SetField("Password","secretword");
	}

}
