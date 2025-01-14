package e2e.stepdefinitions;

import com.microsoft.playwright.Page;
import e2e.AccessibilityChecker;
import e2e.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccessibilityCheck {
  private Page page;

  public AccessibilityCheck() {
    this.page = DriverFactory.getPage();
  }

  @Then("Current page should pass accessibility checks")
  public void thePageShouldPassAccessibilityChecks() {
    if (page == null) {
      throw new IllegalStateException("Page object is not initialized. Ensure the browser and page are set up.");
    }
    AccessibilityChecker.logAccessibilityViolations(page);
  }

  @When("Open the {string} homepage in {string}")
  public void iOpenTheGoogleHomepageIn(String homePageName, String browserName) {
    if (this.page == null) {
      throw new IllegalStateException("Page object is not initialized. Ensure the browser and page are set up.");
    }
    switch (homePageName.toLowerCase()) {
      case "google":
        page.navigate("https://www.google.com/");
        break;
      case "playwright java":
        page.navigate("https://playwright.dev/java/");
        break;
      case "playwright python":
        page.navigate("https://playwright.dev/python/");
        break;
      default:
        throw new IllegalArgumentException("Unsupported homepage: " + homePageName);
    }
  }
}
