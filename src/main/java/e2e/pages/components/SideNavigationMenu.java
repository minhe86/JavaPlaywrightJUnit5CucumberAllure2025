package e2e.pages.components;

import com.microsoft.playwright.Page;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SideNavigationMenu extends Component {

  final String crossButton = "button# react-burger-cross-btn";
  final String allItemsLink = "a#inventory_sidebar_link";
  final String aboutLink = "a#about_sidebar_link";
  final String logoutLink = "a#logout_sidebar_link";

  public SideNavigationMenu(Page page) {
    super(page);
  }

  @Override
  @Test
  public void waitForDisplayed() {
    assertTrue(page.locator(this.crossButton).isVisible());
    assertTrue(page.locator(this.logoutLink).isVisible());
  }

  @Override
  public void verifyDetails() {
    assertTrue(page.locator(this.allItemsLink).isVisible());
    assertEquals("https://saucelabs.com/", page.locator(this.aboutLink).getAttribute("href"));
    assertTrue(page.locator(this.logoutLink).isVisible());
  }

  public void logout() {
    page.locator(this.logoutLink).click();
  }
}
