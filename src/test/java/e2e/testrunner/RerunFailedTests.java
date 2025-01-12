package e2e.testrunner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("src/test/resources/")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "target/rerun.txt")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "e2e.stepdefinitions, e2e.hooks")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")

public class RerunFailedTests {

  public static void main(String[] args) throws Exception {
    // Debugging the rerun.txt contents
    List<String> rerunContent = Files.readAllLines(Paths.get("target/rerun.txt"));
    System.out.println("Reading rerun.txt file:");
    rerunContent.forEach(System.out::println);
  }

}
