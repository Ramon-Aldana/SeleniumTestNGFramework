//Page Object Model (POM) pattern.
package testcases.pages.common;

import org.openqa.selenium.WebDriver;

import pageobjects.BasePage;



//Inheritance: LandingPage is inheriting from BasePage.
public class LandingPage extends BasePage  {
	
	
	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	

	
}
