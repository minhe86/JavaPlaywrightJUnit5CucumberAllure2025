package gettingstarted;

import com.microsoft.playwright.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Paths;

public class Utility {

//  public static byte[] capturePageScreenshot(Page page)
//  {
//    SimpleDateFormat customFormat=new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
//    Date date=new Date();
//    String newDate=customFormat.format(date);
//    return page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get("test-output/Screenshots/"+newDate+".png")));
//  }
//
//  public static byte[] captureLocatorScreenshot(Locator locator)
//  {
//    SimpleDateFormat customFormat=new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
//    Date date=new Date();
//    String newDate=customFormat.format(date);
//    return locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("test-output/Screenshots/"+newDate+"Locator.png")));
//  }

  // Generic method to capture a screenshot for both Page and Locator
  public static void captureScreenshot(Object element) {
    SimpleDateFormat customFormat = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
    Date date = new Date();
    String newDate = customFormat.format(date);

    // Determine the file name based on the element type
    String fileName = "test-output/Screenshots/" + newDate + (element instanceof Locator ? "-Locator.png" : "-Page.png");

    try {
      Files.createDirectories(Paths.get("test-output/Screenshots"));

      Path path = Paths.get(fileName);

      if (element instanceof Page page) {
        // Handle Page screenshot
        page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(path));
        System.out.println("Page screenshot saved: " + fileName);
      } else if (element instanceof Locator locator) {
        // Handle Locator screenshot
        locator.screenshot(new Locator.ScreenshotOptions().setPath(path));
        System.out.println("Locator screenshot saved: " + fileName);
      } else {
        System.err.println("Unsupported element type: " + element.getClass().getName());
      }

    } catch (IOException e) {
      System.err.println("Error saving screenshot: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
