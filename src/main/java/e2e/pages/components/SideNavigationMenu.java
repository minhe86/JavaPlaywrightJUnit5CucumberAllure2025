package e2e.pages.components;

import com.microsoft.playwright.Page;
import org.testng.Assert;

public class SideNavigationMenu extends Component {

  final String crossButton = "button# react-burger-cross-btn";
  final String allItemsLink = "a#inventory_sidebar_link";
  final String aboutLink = "a#about_sidebar_link";
  final String logoutLink = "a#logout_sidebar_link";

  public SideNavigationMenu(Page page) {
    super(page);
  }

  @Override
  public void waitForDisplayed() {
    Assert.assertTrue(page.locator(this.crossButton).isVisible());
    Assert.assertTrue(page.locator(this.logoutLink).isVisible());
  }

  @Override
  public void verifyDetails() {
    Assert.assertTrue(page.locator(this.allItemsLink).isVisible());
    Assert.assertEquals(page.locator(this.aboutLink).getAttribute("href"), "https://saucelabs.com/");
    Assert.assertTrue(page.locator(this.logoutLink).isVisible());
  }

  public void logout() {
    page.locator(this.logoutLink).click();
  }
}
