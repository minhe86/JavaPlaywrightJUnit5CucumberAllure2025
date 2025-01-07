package utils;

import com.microsoft.playwright.Locator;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public abstract class Helpers {

  public static String getProperty(String key) {
    ConfigReader configReader = new ConfigReader();
    Properties properties = configReader.initProp();    // Reading from config properties file
    return properties.getProperty(key);
  }

  public static String readFile(String filePath) {
    try {
      return Files.readString(Path.of(filePath));
    } catch (Exception e) {
      throw new RuntimeException("Failed to read file: " + filePath, e);
    }
  }

  public static boolean waitUntilElementDisplayed(Locator locator, int timeoutSec) {
    boolean elementVisible = locator.isVisible();
    int timer = 0;
    while (!elementVisible && timer < timeoutSec) {
      try {
        Thread.sleep(1000);
        elementVisible = locator.isVisible();
        timer++;
      } catch (Exception e) {
        System.out.println(locator + "was not visible.");
      }
    }
    return elementVisible;
  }

}
