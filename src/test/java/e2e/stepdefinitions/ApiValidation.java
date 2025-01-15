package e2e.stepdefinitions;

import com.google.gson.JsonObject;
import e2e.ApiClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import static org.junit.jupiter.api.Assertions.*;

public class ApiValidation {
  private JsonObject response;

  @When("Send a GET request to URL")
  public void iSendAGETRequestTo() {
    response = ApiClient.sendGetRequest();
  }

  @When("Send a GET request to endpoint {string}")
  public void iSendAGETRequestTo(String endpoint) {
    response = ApiClient.sendGetRequest(endpoint);
  }

  @Then("The response contains the key {string} with value {string}")
  public void theResponseContainsTheKeyWithValue(String key, String expectedValue) {
    try {
      assertEquals(expectedValue, response.get(key).getAsString(), "API response validation failed");
      Allure.addAttachment("Expected Value", expectedValue);
      Allure.addAttachment("Actual Value", response.get(key).getAsString());
    } catch (AssertionError e) {
      Allure.addAttachment("Expected Value", expectedValue);
      Allure.addAttachment("Actual Value", response.get(key).getAsString());
      throw e;
    }
  }
}
