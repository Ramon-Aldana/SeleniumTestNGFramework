package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class BreadCrumb {

	WebElement parent;
	
	public BreadCrumb(WebElement parent) {
		this.parent = parent;
	}


	public BreadCrumb Crumb(String crumb) {
		//When parent is an 1 that means that I am not the container.
		if (parent.getTagName()=="a") {
			//By.cssSelector to get the next bread crumb precisely after the parent bread crumb
			return new BreadCrumb(parent.findElement(By.xpath(String.format("./ancestor::li/following-sibling::li/a[text()='%s']", crumb))));
		} else {
			//By.xpath locator to get exactly the very first element of bread crumb
			return new BreadCrumb(parent.findElement(By.cssSelector(String.format("#product-category > ul > li:first-child > a:contains('%s')", crumb))));
		}
		
	}
	
	
}
