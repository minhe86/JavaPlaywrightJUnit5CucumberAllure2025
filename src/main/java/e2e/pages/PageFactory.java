package e2e.pages;

import com.microsoft.playwright.Page;
import e2e.pages.components.Component;
import e2e.pages.components.SideNavigationMenu;

import java.util.HashMap;
import java.util.Map;

public class PageFactory {

  private static final Map<String, Class<? extends Component>> pageRegistry = new HashMap<>();

  static {
    // Register page objects here
    pageRegistry.put("Login", Login.class);
    pageRegistry.put("Home", Home.class);
    pageRegistry.put("SideNavigationMenu", SideNavigationMenu.class);
  }

//  // Method to get the page class based on the page name - removed by option3 only, option1 and option2 still require it.
//  public static Class<? extends Component> getPageClass(String pageName) {
//    return pageRegistry.get(pageName);
//  }

//  // Method to instantiate a page object dynamically and pass Playwright Page - option1
//  public static Component createPage(String pageName, Page page) throws Exception {
//    Class<? extends Component> pageClass = getPageClass(pageName);
//    return Component.create(pageClass, page);
//  }

//  // Method to instantiate a page dynamically using reflection - option2
//  public static Component createPage(String pageName, Page page) throws Exception {
//    Class<? extends Component> pageClass = getPageClass(pageName);
//    return pageClass.getDeclaredConstructor(Page.class).newInstance(page);
//  }

  // Generic method to create a page object of a specific type - option3
  public static <T extends Component> T createPage(String pageName, Page page) throws Exception {
    Class<? extends Component> pageClass = pageRegistry.get(pageName);

    if (pageClass == null) {
      throw new IllegalArgumentException("Page not registered: " + pageName);
    }

    // Use reflection to instantiate the page object and suppress unchecked cast warning
    @SuppressWarnings("unchecked")
    T component = (T) pageClass.getDeclaredConstructor(Page.class).newInstance(page);

    return component;
  }

}

