package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAccountPage extends BasePage {

	public RegisterAccountPage(WebDriver driver) {
		super(driver);
	}
	
	AsideMenuComponent asidemenu;
	
	//Thread-Safe Singleton Pattern
	public AsideMenuComponent getAsideMenu()
	{
		//double-checked locking pattern, which avoids the overhead of synchronization after the object is initialized
		if (asidemenu == null) {
	        synchronized (this) {
	            if (asidemenu == null) {
	            	//TODO: Lacks null safety
	            	asidemenu = new AsideMenuComponent(driver);
	            }
	        }
	    }
	    return asidemenu;
	}
	
	public OCForm getOCForm() {
		return new OCForm(driver.findElement(By.xpath("//*[@id='form-register']")));
	}
}
