package utils;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class CSVUtils {

  public static List<Product> readProductCSV(String filePath) throws IOException {
    List<Product> products = new ArrayList<>();

    // Load the CSV file as a resource from the classpath
    InputStream inputStream = CSVUtils.class.getClassLoader().getResourceAsStream(filePath);
    if (inputStream == null) {
      throw new FileNotFoundException("CSV file is not found: " + filePath);
    }
    Reader in = new InputStreamReader(inputStream);

    CSVFormat format = CSVFormat.DEFAULT
      .builder()
      .setHeader("productNo", "productName", "productPrice", "productDescription")
      .setSkipHeaderRecord(true)
      .build();

    Iterable<CSVRecord> records = format.parse(in);

    // Parse each CSV record and map to Product object
    for (CSVRecord record : records) {
      int productNo = Integer.parseInt(record.get("productNo"));
      String productName = record.get("productName");
      double productPrice = Double.parseDouble(record.get("productPrice"));
      String productDescription = record.get("productDescription");

      products.add(new Product(productNo, productName, productPrice, productDescription));
    }

    return products;
  }

}
