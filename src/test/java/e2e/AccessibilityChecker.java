package e2e;

import com.microsoft.playwright.Page;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import utils.Helpers;

public class AccessibilityChecker {

  private static final String AXE_SCRIPT_PATH = "src/test/resources/axe.min.js";

  /**
   * Injects the Axe-core script into the current page.
   *
   * @param page The Playwright Page instance.
   */
  private static void injectAxe(Page page) {
    String axeScript = Helpers.readFile(AXE_SCRIPT_PATH);
    page.evaluate(axeScript);
  }

  public static void logAccessibilityViolations(Page page) {
    injectAxe(page);
    String violationsJson = (String) page.evaluate("async () => { " +
      "const results = await axe.run(); " +
      "return JSON.stringify(results.violations); " +
      "}");
    logViolationsToAllure(violationsJson);
  }

  private static void logViolationsToAllure(String violationsJson) {
    String formattedViolations = formatViolations(violationsJson);
    Allure.addAttachment("Accessibility Violations", "text/plain", formattedViolations);
  }

  private static String formatViolations(String violationsJson) {
    try {
      // Parse and pretty-print JSON
      ObjectMapper mapper = new ObjectMapper();
      Object json = mapper.readValue(violationsJson, Object.class);
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    } catch (JsonProcessingException e) {
      // Handle JSON parsing errors
      return "Error parsing violations JSON: " + e.getMessage();
    }
  }
}
