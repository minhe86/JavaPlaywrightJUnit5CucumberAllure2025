package e2e.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import e2e.pages.components.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Helpers;
import utils.Product;

import java.text.DecimalFormat;
import java.util.List;

public class Home extends Component {

  private final Locator productName1;
  private final Locator productDescription1;
  private final Locator productPrice1;
  private final Locator productAddRemoveButton1;

  private final Locator burgerMenu;

  private Locator getProductName(int productNo) {
    return page.locator("a#item_" + productNo + "_title_link");
  }

  private record ProductLocators(Locator productName, Locator productDescription, Locator productPrice,
                                 Locator productAddRemoveButton) {
  }

  private ProductLocators getProductLocators(int productNo) {
    Locator productName = page.locator("a#item_" + productNo + "_title_link");
    Locator selectedInventoryLocator = page.locator("div.inventory_item_description")
      .filter(new Locator.FilterOptions().setHas(productName));

    Locator productDescription = selectedInventoryLocator.locator("div.inventory_item_desc");
    Locator productPrice = selectedInventoryLocator.locator("div.inventory_item_price");
    Locator productAddRemoveButton = selectedInventoryLocator.locator("button");

    return new ProductLocators(
      productName,
      productDescription,
      productPrice,
      productAddRemoveButton
    );
  }
//  // Inner class to hold locators for product details --- recommended record is much clean now!!!
//  private static class ProductLocators {
//    private final Locator productName;
//    private final Locator productDescription;
//    private final Locator productPrice;
//    private final Locator productAddRemoveButton;
//
//    public ProductLocators(Locator productName, Locator productDescription, Locator productPrice, Locator productAddRemoveButton) {
//      this.productName = productName;
//      this.productDescription = productDescription;
//      this.productPrice = productPrice;
//      this.productAddRemoveButton = productAddRemoveButton;
//    }
//  }

  // Constructor to inject Page
  public Home(Page page) {
    super(page);
    // all these could be removed after implementing method - getProductLocators.
    this.productName1 = page.locator("a#item_1_title_link");
    Locator selectedInventoryLocator = page.locator("div.inventory_item_description").filter(new Locator.FilterOptions().setHas(page.locator("a#item_1_title_link")));
    this.productDescription1 = selectedInventoryLocator.locator("div.inventory_item_desc");
    this.productPrice1 = selectedInventoryLocator.locator("div.inventory_item_price");
    this.productAddRemoveButton1 = selectedInventoryLocator.locator("button");

    this.burgerMenu = page.locator("button#react-burger-menu-btn");
  }

  @Override
  @Test
  public void waitForDisplayed() {
    productName1.waitFor();
    productDescription1.waitFor();
    productPrice1.waitFor();
    productAddRemoveButton1.waitFor();
    Assert.assertTrue(Helpers.waitUntilElementDisplayed(burgerMenu, 3));
  }

  @Override
  @Test
  public void verifyDetails() {
    if (!productName1.isVisible()) {
      throw new RuntimeException("Product 1 is not available.");
    }
    Assert.assertTrue(productName1.isVisible());
    Assert.assertTrue(productDescription1.isVisible());
    Assert.assertTrue(productPrice1.isVisible());
    Assert.assertTrue(productAddRemoveButton1.isEnabled());
    Assert.assertEquals(page.title(), "Swag Labs");
  }

  @Test
  public void verifyProductName(String productName) {
    Assert.assertEquals(productName1.textContent(), productName);
  }

  @Test
  public void verifyProductDetails(Product product) {
    ProductLocators productLocators = getProductLocators(product.productNo());

    Assert.assertEquals(productLocators.productName.textContent(), product.productName());
    Assert.assertEquals(productLocators.productDescription.textContent(), product.productDescription());
    String expectedPrice = "$" + new DecimalFormat("#.##").format(product.productPrice());
    Assert.assertEquals(productLocators.productPrice.textContent().trim(), expectedPrice);
    Assert.assertTrue(productLocators.productAddRemoveButton.isEnabled());
  }

  @Test
  public void verifyProductsDetails(List<Product> products) {
    for (Product product : products) {
      this.verifyProductDetails(product);
    }
  }

  @Test
  public void navigateToSideMenu() {
    Assert.assertTrue(burgerMenu.isVisible());
    burgerMenu.click();
  }

}
