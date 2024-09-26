package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AsideMenuComponent  {

	WebDriver driver; 
	
	public AsideMenuComponent(WebDriver driver) {
		this.driver = driver;
		
	}
	
	@FindBy(xpath = "//*[@id='column-right']/div")
	WebElement menu;
	
	public BasePage Navigate(String page) {
		
		//TODO: Not implemented yet
		return new BasePage(driver);
		
	}
	
}
