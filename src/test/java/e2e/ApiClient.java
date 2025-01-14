package e2e;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Allure;
import utils.Helpers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
static String endpoint = Helpers.getProperty("apiEndpoint");

  private static final HttpClient client = HttpClient.newHttpClient();

  public static JsonObject sendGetRequest() {
    Allure.attachment("URL",endpoint);
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).GET().build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      Allure.addAttachment("API Response", response.body());
      JsonElement jsonElement = JsonParser.parseString(response.body());
      if (jsonElement.isJsonArray()) {
        return jsonElement.getAsJsonArray().get(0).getAsJsonObject();
      }
      return jsonElement.getAsJsonObject();
    } catch (Exception e) {
      throw new RuntimeException("API Request failed", e);
    }
  }

  public static JsonObject sendGetRequest(String endpoint) {
    Allure.attachment("URL",endpoint);
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).GET().build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      Allure.addAttachment("API Response", response.body());
      JsonElement jsonElement = JsonParser.parseString(response.body());
      if (jsonElement.isJsonArray()) {
        return jsonElement.getAsJsonArray().get(0).getAsJsonObject();
      }
      return jsonElement.getAsJsonObject();
    } catch (Exception e) {
      throw new RuntimeException("API Request failed", e);
    }
  }
}
