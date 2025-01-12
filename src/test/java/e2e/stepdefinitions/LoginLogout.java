package e2e.stepdefinitions;

import com.microsoft.playwright.Page;
import e2e.DriverFactory;
import e2e.pages.Home;
import e2e.pages.Login;
import e2e.pages.PageFactory;
import e2e.pages.components.Component;
import e2e.pages.components.SideNavigationMenu;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CSVUtils;
import utils.Product;

import java.util.List;

public class LoginLogout {

  private Page page;
  private List<Product> products;

  public LoginLogout() {
    // Initialize Playwright and Browser
//    Playwright pw = Playwright.create();
//    BrowserType chrome = pw.chromium();
//    Browser browser = chrome.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200));
//    this.page = browser.newPage();

    this.page = DriverFactory.getPage();
  }

  @Given("User launches SwagLabs application")
  public void launchApp() throws Exception {
    this.page.navigate("https://www.saucedemo.com/");
    // Create page dynamically based on pageName
    Component component = PageFactory.createPage("Login", this.page);
    component.verifyDetails();
  }

  @When("User logs in the app using username {string} and password {string}")
  public void loginApp(String username, String password) {
    Login login = new Login(this.page);
    login.loginApplication(username, password);
  }

  @Then("User is directed to {string} page")
  public void verifyDirectedPage(String pageName) throws Exception {
    Component component = PageFactory.createPage(pageName, this.page);
    component.verifyDetails();
  }

  @When("User verifies all products details on Home page")
  public void verifyProductsDetails() throws Exception {
    String csvFilePath = "data/products.csv";
    products = CSVUtils.readProductCSV(csvFilePath);

    Home home = new Home(this.page);
    home.verifyProductsDetails(products);
  }

  @When("User verifies the product details of product {int} on Home page")
  public void verifyProductDetails(int productNo) throws Exception {
    String csvFilePath = "data/products.csv";
    products = CSVUtils.readProductCSV(csvFilePath);

    Product product = products.stream()
      .filter(p -> p.productNo() == productNo)
      .findFirst()
      .orElseThrow(() -> new RuntimeException("Product with productNo " + productNo + " not found"));

    Home home = new Home(this.page);
    home.verifyProductDetails(product);
  }

  @When("User verifies the product name {string}")
  public void verifyProduct(String productName) throws Exception {
//    Home home = new Home(this.page);
//    home.verifyProductName(productName);

//    //option2
//    Component component = PageFactory.createPage("Home", this.page);
//    Home home = (Home) component;
//    home.verifyProductName(productName);

    //option3
    Home home = PageFactory.createPage("Home", this.page);
    home.verifyProductName(productName);
  }

  @When("User logs out")
  public void userLogsOut() {
    Home home = new Home(this.page);
    home.navigateToSideMenu();

    SideNavigationMenu sideNavigationMenu = new SideNavigationMenu(this.page);
    sideNavigationMenu.logout();
  }
}
