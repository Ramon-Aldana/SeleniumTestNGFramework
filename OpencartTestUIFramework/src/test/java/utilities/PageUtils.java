package utilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageUtils {

    private WebDriver driver;

    public PageUtils(WebDriver driver) {
        this.driver = driver;
    }

    //This method uses JavascriptExecutor to execute a JavaScript command that retrieves the document.readyState.
    public void waitForPageToLoad() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // Wait until the readyState of the document is 'complete'
        for (int i = 0; i < 30; i++) { // Try for 30 seconds
            String readyState = jsExecutor.executeScript("return document.readyState").toString();
            if (readyState.equals("complete")) {
                break; // Exit the loop if the page is fully loaded
            }
            try {
                Thread.sleep(1000); // Wait for 1 second before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }
    }
    
    //This method uses JavascriptExecutor to Flash elements in page.
    public void flashElement(WebElement ele, Duration still) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        scrollToView(ele);
        jsExecutor.executeScript("f=0;i=setInterval(()=>f++===10?clearInterval(i):arguments[0].style.backgroundColor=arguments[0].style.backgroundColor?'':'yellow',300);", ele);
        try {
            Thread.sleep(still); // Wait for 1 second before checking again
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }

    }
    
    public void scrollToView(WebElement ele) {
    	// element.scrollIntoView({ behavior: 'smooth' }); 
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth' });", ele);
    }
}
