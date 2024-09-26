//Page Object Model (POM) pattern.
package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Inheritance: LandingPage is inheriting from BasePage.
public class LandingPage extends BasePage {

	public LandingPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"carousel-banner-0\"]")
	public WebElement Carousel;
	@FindBy(xpath = "//*[@id=\"carousel-banner-1\"]")
	public WebElement CarouselBanner;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[2]")
	public WebElement Featured;
}
