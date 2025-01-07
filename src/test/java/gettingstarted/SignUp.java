package gettingstarted;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUp {
  @Test
  void setUp() {
    System.out.println("I am inside setup");
  }

  @Test
  void signUp()
  {
    try (Playwright playwright = Playwright.create())
    {
//      try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)))
      try (Browser browser = playwright.webkit().launch())
      {
        Page page = browser.newPage();

        page.navigate("https://freelance-learn-automation.vercel.app/login");

        String title = page.title();

//        PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
//        PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");
//        PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
        Assert.assertTrue(title.contains("Learn Automation Courses"));

        page.locator(".subLink").click();
        Assert.assertTrue(page.url().contains("signup"));
        Assert.assertTrue(page.locator("button[type='submit']").isDisabled());

        Name name = new Faker().name();
        page.locator("input[name='name']").fill(name.fullName());
        page.locator("input[name='email']").fill(name.firstName()+"_"+name.lastName()+"@gmail.com");
        page.locator("input[name='password']").fill("learning@1234");

        page.getByLabel("Playwright",  new Page.GetByLabelOptions().setExact(true)).click();
//        page.getByText("Playwright", new Page.GetByTextOptions().setExact(true)).click();
//        page.waitForTimeout(3*1000);
        Assert.assertTrue(page.locator("xpath=//label[text()='Playwright']//preceding::input[1]").isChecked());

        page.getByText("API Testing").click();
        Assert.assertTrue(page.locator("xpath=//label[text()='API Testing']//preceding::input[1]").isChecked());

        page.getByText("Selenium").click();
        Assert.assertTrue(page.locator("xpath=//label[text()='Selenium']//preceding::input[1]").isChecked());

        page.locator("input[value='Female']").click();
        Assert.assertTrue(page.locator("input[value='Female']").isChecked());

//        page.locator("#state").selectOption("Kerala");
        page.locator("select[name='state']").selectOption("Kerala");

        String hobbies[] = {"Playing","Swimming"};
        page.locator("select[name='hobbies']").selectOption(hobbies);

        Assert.assertTrue(page.locator("button[type='submit']").isEnabled());
//        page.pause();
        page.locator("button[type='submit']").click();

        // if not wait, still check in the previous page, how to fix it? using new page???
        page.waitForTimeout(1*1000);
        Assert.assertTrue(page.getByText("Signup successfully, Please").isVisible());
        Assert.assertTrue(page.url().contains("login"));

        System.out.println("I am inside Sign Up end");
//        page.waitForTimeout(10*1000);
      }
    }
  }

  @Test
  void tearDown() {
    System.out.println("I am inside tearDown");
  }
}
