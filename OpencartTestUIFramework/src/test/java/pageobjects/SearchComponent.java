

package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class SearchComponent {
	
	WebDriver driver;
	
	public SearchComponent(WebDriver driver) {
		
		this.driver = driver;
		
		//The initElements method of PageFactory is used to initialize all elements in a page object class.
		PageFactory.initElements(driver,this );
	};
	
	

	// Locating elements using Page Factory @FindBy annotation
	//Lazy Initialization: Web elements are initialized lazily
	@FindBy(xpath="\"//*[@id='search']/input")
	WebElement Text ;
	
	@FindBy(xpath="//*[@id='search']/button")
	WebElement Search ;

	//Factory Method Pattern in the Find method to create instances of SearchResultsPage
	public SearchResultsPage Find(String query) {
		//find something first
		Text.sendKeys(query);
		Search.click();
		
		return new SearchResultsPage(driver);
	}
	
}