package gettingstarted;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoogleSearchAutoSuggestion {
  @Test
  void setUp() {
    System.out.println("I am inside setup");
  }

  @Test
  void googleSearchAutoSuggestion()
  {
    try (Playwright playwright = Playwright.create())
    {
      try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200)))
      {
        Page page = browser.newPage();

        page.navigate("https://www.google.com/");

        String title = page.title();
        assertTrue(title.contains("Google"));

        page.locator("textarea[name='q']").fill("Playwright Java");
//        page.locator("xpath=//textarea[@title='Search']").fill("Playwright Java");
        Locator locator=page.locator("xpath=//ul[@role='listbox']//li");

        System.out.println("Listed searched results count = " + locator.count());

        String keyword="tutorial";
        String selectedText = "";

        for(int i=0; i<locator.count(); i++)
        {
          String text=locator.nth(i).innerText();
          System.out.println("Listed searched result" + i + " = " + text);
        }

        for(int i=0; i<locator.count(); i++)
        {
          String text=locator.nth(i).innerText();
          if(text.contains(keyword))
          {
            selectedText=text;
            System.out.println("Selected searched result is No" + (i+1) + " = " + selectedText);
            locator.nth(i).click();
            break;
          }
        }
       assertTrue(page.title().contains(selectedText));
       assertTrue(page.locator("textarea[name='q']").textContent().contains(selectedText));

//        Utility.captureLocatorScreenshot(page.locator("textarea[name='q']"));
//        Utility.capturePageScreenshot(page);
        Utility.captureScreenshot(page.locator("textarea[name='q']"));
        // depending on network status, could be different images, how to handle this case?
        Utility.captureScreenshot(page);

        System.out.println("I am inside Google Search end");
      }
    }
  }

  @Test
  void tearDown() {
    System.out.println("I am inside tearDown");
  }
}
