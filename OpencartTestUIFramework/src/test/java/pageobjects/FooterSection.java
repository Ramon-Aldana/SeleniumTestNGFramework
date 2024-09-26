package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterSection {
	
	public FooterSection(WebDriver driver) {
		//The initElements method of PageFactory is used to initialize all elements in a page object class.
		PageFactory.initElements(driver,this );
	};

	// Locating elements using Page Factory @FindBy annotation
	//Lazy Initialization: Web elements are initialized lazily
	@FindBy(xpath="//a[normalize-space()='Terms & Conditions']")
	WebElement TermsAndConditions ;
	
	@FindBy(xpath="//a[normalize-space()='My Account']")
	WebElement MyAccount ;
	
	@FindBy(xpath="//a[normalize-space()='Order List']")
	WebElement OrderList ;
	
	
}