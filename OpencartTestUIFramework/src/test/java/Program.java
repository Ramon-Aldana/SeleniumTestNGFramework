import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Program {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException, InterruptedException {
		String hubUrl = "http://192.168.4.184:4444/wd/hub";
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setPlatform(Platform.WIN11);
		capabilities.setBrowserName("chrome");
		
		WebDriver driver ;
		for (int i=1;i<=8;i++) {
			 driver = new RemoteWebDriver((new URI(hubUrl)).toURL(),capabilities);
			driver.get("http://www.google.com");
			Thread.sleep(Duration.ofSeconds(10));
		}
	}

}
