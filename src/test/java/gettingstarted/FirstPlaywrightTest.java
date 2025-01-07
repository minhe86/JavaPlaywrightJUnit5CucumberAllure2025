package gettingstarted;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public class FirstPlaywrightTest {

    public static void main(String[] args) {

      Playwright pw=Playwright.create();

//      BrowserType browserType=pw.chromium();
//      BrowserType browserType=pw.firefox();
//      BrowserType browserType=pw.webkit();
      BrowserType browserType=pw.chromium();

//      Browser browser=browserType.launch();
      Browser browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
//      Browser browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));

      Page page=browser.newPage();

      page.navigate("https://playwright.dev/java/docs/intro#first-script");

      String title=page.title();

      System.out.println("Title is " + title);

//      page.close();
//      browser.close();
//      pw.close();
    }
}
