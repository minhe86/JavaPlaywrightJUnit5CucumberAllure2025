package gettingstarted;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class RecordLoginTest {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();
      page.navigate("https://freelance-learn-automation.vercel.app/login");
      page.getByPlaceholder("Enter Email").click();
      page.getByPlaceholder("Enter Email").fill("admin@email.com");
      page.getByPlaceholder("Enter Password").click();
      page.getByPlaceholder("Enter Password").fill("admin@123");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();

//      context.storageState(new BrowserContext.StorageStateOptions().setPath("login.json"));


      // use token instead of login
//      BrowserContext context = browser.newContext(new Browser.NewContextOptions()
//        .setStorageStatePath(Paths.get("login.json")));
//      Page page = context.newPage();
//      page.navigate("https://freelance-learn-automation.vercel.app/");

    }
  }
}
