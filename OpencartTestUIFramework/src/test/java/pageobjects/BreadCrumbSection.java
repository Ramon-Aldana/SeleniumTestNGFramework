package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class BreadCrumbSection {

	WebDriver driver;
	
	public BreadCrumbSection(WebDriver driver) {
		// The initElements method of PageFactory is used to initialize all elements in
		// a page object class.
		PageFactory.initElements(driver, this);
	}

	// Locating elements using Page Factory @FindBy annotation
	// Lazy Initialization: Web elements are initialized lazily
	@FindBy(xpath = "//*[@id=\"product-category\"]/ul")
	WebElement breadcrumb;
	
	public BreadCrumb Crumb(String crumb) {
		
		return new BreadCrumb(breadcrumb);
	
	}


}
