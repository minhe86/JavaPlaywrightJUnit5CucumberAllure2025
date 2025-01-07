package e2e;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import utils.Helpers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DriverFactory {
  private static Browser browser;
  private static BrowserContext context;
  private static Page page;

  public static void multipleBrowserSetup(String browserName) {
    Playwright playwright = Playwright.create();
    boolean headless = Boolean.parseBoolean(Helpers.getProperty("headless"));

    switch (browserName.toLowerCase()) {
      case "chrome":
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
          .setHeadless(headless)
          .setSlowMo(100));
        break;
      case "firefox":
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
          .setHeadless(headless)
          .setSlowMo(100));
        break;
      case "webkit":
        browser = playwright.webkit().launch(new BrowserType.LaunchOptions()
          .setHeadless(headless)
          .setSlowMo(100));
        break;
      default:
        throw new IllegalArgumentException("Unsupported browser: " + browserName);
    }

    context = browser.newContext();
    page = context.newPage();
  }

  public static void tearDown() {
    System.out.println("Closing context and browser...");
    if (page != null) {
      page.close();
    }
    if (context != null) {
      context.close();
    }
    if (browser != null) {
      browser.close();
    }
  }

  public static Page getPage() {
    return page;
  }

  public static void captureScreenshot(String name) {
    byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    try (InputStream is = new ByteArrayInputStream(screenshotBytes)) {
      Allure.addAttachment(name, "image/png", is, "png");
    } catch (Exception e) {
      throw new RuntimeException("Failed to attach screenshot to Allure report", e);
    }
  }

}
