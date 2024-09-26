package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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
}
