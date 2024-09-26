package testcases.pages.common;

import java.time.Duration;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import testcases.BaseTest;

public class Home extends BaseTest {

	LandingPage home;

//Template Method Pattern: Override this method in the subclass to provide specific behavior.
	@Override
	protected void afterConstruction() {
		// Custom behavior for the subclass
		System.out.println("Custom behavior in subclass after superclass constructor");
		home = new LandingPage(driver);
	}

	/*
	 * Why test methods start with test... Clarity: Starting with test makes it
	 * clear that the method is a test case. Framework Compatibility: Many testing
	 * frameworks recognize methods prefixed with test as test cases automatically.
	 * Consistent Structure: Following a consistent naming pattern improves
	 * readability and understanding. Alternative Naming Conventions: Consider
	 * behavior-driven naming (e.g., should<ExpectedBehavior>) or descriptive names
	 * for better clarity. Consistency is Key: Choose a naming convention that
	 * aligns with your team’s standards and apply it consistently across all test
	 * cases.
	 */

	@Test
	public void testPageLoaded(ITestContext context) throws InterruptedException {
		Thread.sleep(Duration.ofSeconds(2));
	}

	@Test
	public void testCarouselSection(ITestContext context) {
		Assert.assertTrue(home.Carousel.isDisplayed());
	}

	@Test
	public void testCarouselBannerSection(ITestContext context) {
		Assert.assertTrue(home.CarouselBanner.isDisplayed());
	}
}