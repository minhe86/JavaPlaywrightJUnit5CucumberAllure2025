package e2e.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import e2e.pages.components.Component;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
  @Test
  public void waitForDisplayed() {
    username.waitFor();
    password.waitFor();
    loginButton.waitFor();
  }

  @Override
  @Test
  public void verifyDetails() {
    assertTrue(username.isEditable());
    assertTrue(password.isEditable());
    if (!loginButton.isEnabled()) {
      throw new RuntimeException("Login button is not clickable.");
    }
    assertEquals("Swag Labs", page.title());
  }

  @Test
  public void loginApplication(String user, String passwd) {
    username.fill(user);
    password.fill(passwd);
    loginButton.click();
  }

}
