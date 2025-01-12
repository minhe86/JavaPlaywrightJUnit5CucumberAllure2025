package e2e.hooks;

import e2e.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import com.microsoft.playwright.*;
import utils.Helpers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Hooks {

  static Playwright playwright;
  static Browser browser;
  static BrowserContext context;
  static Page page;

  public DriverFactory driverFactory;


  public void captureScreenshotAndAttachToAllure(String testName) {
    if (page != null) {
      byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
      try (InputStream is = new ByteArrayInputStream(screenshotBytes)) {
        Allure.addAttachment(testName + " - Screenshot", "image/png", is, "png");
      } catch (Exception e) {
        throw new RuntimeException("Failed to attach screenshot to Allure report", e);
      }
    }
  }

  @Before
  public void launchBrowser() {
    String browserName = Helpers.getProperty("browser");
    driverFactory = new DriverFactory();
    page = driverFactory.initDriver(browserName);
  }

  // After each scenario, capture a screenshot if it fails and close browser resources
  @After
  public void tearDown(Scenario scenario) {
    if (scenario.isFailed()) {
      this.captureScreenshotAndAttachToAllure(scenario.getName());
    }
    System.out.println("Closing browser...");
    if (page != null) {
      page.close();
    }
    if (context != null) {
      context.close();
    }
    if (browser != null) {
      browser.close();
    }
    if (playwright != null) {
      playwright.close();
    }
  }
}

