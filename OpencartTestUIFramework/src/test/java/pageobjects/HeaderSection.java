package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class HeaderSection {
	
	public HeaderSection(WebDriver driver) {
		//The initElements method of PageFactory is used to initialize all elements in a page object class.
		PageFactory.initElements(driver,this );
	};

	// Locating elements using Page Factory @FindBy annotation
	//Lazy Initialization: Web elements are initialized lazily
	@FindBy(xpath="//*[@id=\"top\"]/div/div[2]/ul/li[1]/span")
	WebElement TelephoneNumber ;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement MyAccount ;
	
		@FindBy(linkText="Register")
		WebElement Register;
		
		@FindBy(linkText="Login")
		WebElement Login;
	
	@FindBy(xpath="//*[@id=\"wishlist-total\"]/span")
	WebElement WishList;
	
	@FindBy(xpath = "//span[normalize-space()=\"Checkout\"]")
	WebElement CheckOut;
	
}
