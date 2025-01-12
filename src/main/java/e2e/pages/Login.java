package e2e.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import e2e.pages.components.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends Component {

  private final Locator username;
  private final Locator password;
  private final Locator loginButton;

  // Constructor to inject Page
  public Login(Page page) {
    super(page);
    this.username = page.locator("input#user-name");
    this.password = page.locator("input#password");
    this.loginButton = page.locator("input#login-button");
  }

  @Override
  public void waitForDisplayed() {
    username.waitFor();
    password.waitFor();
    loginButton.waitFor();
  }

  @Override
  public void verifyDetails() {
    Assert.assertTrue(username.isEditable());
    Assert.assertTrue(password.isEditable());
    if (!loginButton.isEnabled()) {
      throw new RuntimeException("Login button is not clickable.");
    }
    Assert.assertEquals(page.title(), "Swag Labs");
  }

  @Test
  public void loginApplication(String user, String passwd) {
    username.fill(user);
    password.fill(passwd);
    loginButton.click();
  }

}