package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

  //This is used to read from properties files and returns properties object.
  public Properties initProp() {
    Properties properties = new Properties();
    try {
      FileInputStream fileInputStream = new FileInputStream("./src/test/resources/configs/config.default.properties");
      properties.load(fileInputStream);
    } catch (Exception e) {
      System.out.println("Unable to read Properties file.");
    }
    return properties;
  }
}
