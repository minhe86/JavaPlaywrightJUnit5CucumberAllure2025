package utils;

//public class Product {
//  private final int productNo;
//  private final String productName;
//  private final double productPrice;
//  private final String productDescription;
//
//  public Product(int productNo, String productName, double productPrice, String productDescription) {
//    this.productNo = productNo;
//    this.productName = productName;
//    this.productPrice = productPrice;
//    this.productDescription = productDescription;
//  }
//
//  // Getters and setters
//  public int getProductNo() {
//    return productNo;
//  }
//
//  public String getProductName() {
//    return productName;
//  }
//
//  public double getProductPrice() {
//    return productPrice;
//  }
//
//  public String getProductDescription() {
//    return productDescription;
//  }
//}

// option 2 - it is better.
public record Product(int productNo, String productName, double productPrice, String productDescription) {
}
