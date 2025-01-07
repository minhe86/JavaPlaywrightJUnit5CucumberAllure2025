package e2e.pages.components;

import com.microsoft.playwright.Page;

public abstract class Component {

  protected Page page;

  // Constructor to inject the Playwright Page
  public Component(Page page) {
    this.page = page;
  }

  // Static method to create an instance of the component dynamically
  public static <T extends Component> T create(Class<T> clazz, Page page) throws Exception {
    T component = clazz.getDeclaredConstructor(Page.class).newInstance(page);
    component.waitForDisplayed();
    return component;
  }

  // Abstract method that must be implemented by child classes
  public abstract void waitForDisplayed();

  // Abstract method for verification
  public abstract void verifyDetails();
}


