package gettingstarted;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.util.regex.Pattern;

public class LoginLogout {
  public static void main(String[] args)
  {
    try (Playwright playwright = Playwright.create())
    {
      try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)))
      {
        Page page = browser.newPage();

        page.navigate("https://freelance-learn-automation.vercel.app/login");

        String title = page.title();

        PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");

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
        PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
        page.waitForTimeout(3*1000);
      }



    }
  }
}
