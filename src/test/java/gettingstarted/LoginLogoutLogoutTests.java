package gettingstarted;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginLogoutLogoutTests {
  @Test
  void setUp() {
    System.out.println("I am inside setup");
  }

  @Test
  void loginTest()
  {
    try (Playwright playwright = Playwright.create())
    {
      try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)))
      {
        Page page = browser.newPage();

        page.navigate("https://freelance-learn-automation.vercel.app/login");

        String title = page.title();

//        PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
        Assert.assertTrue(page.title().contains("Learn Automation Courses"));


//        page.locator("#email1").fill("admin@email.com");
//        page.locator("css=input[name='email1']").fill("admin@email.com");
        page.getByPlaceholder("Enter Email").fill("admin@email.com");
        page.getByPlaceholder("Enter Password").fill("admin@123");
//        page.getByText("Sign in").click();
//        page.getByText("Sign in").nth(1).click();
        page.getByText("Sign in").last().click();
        PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");
        page.getByAltText("menu").click();
        page.getByText("sign out").click();
//        PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
        Assert.assertTrue(page.url().contains("login --- wrong"));
//        page.waitForTimeout(3*1000);
        System.out.println("I am inside loginTest end");
      }
    }
  }

  @Test
  void tearDown() {
    System.out.println("I am inside tearDown");
  }
}
