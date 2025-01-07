package e2e.testrunner;

import io.cucumber.junit.platform.engine.Cucumber;

// Junit4 Test Runner
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//  features = "src/test/resources/features/",
//  glue = {"e2e/stepdefinitions"},
//// Valid values for PLUGIN are: html, json, junit, message, pretty, progress, rerun, summary, teamcity, testng, timeline, unused, usage
//  plugin = {"pretty", "html:target/cucumber-reports" }
//)

  // Junit 5 Test Runner - cucumber-junit-platform-engine: This is the new Cucumber JUnit 5 integration engine.
import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("src/test/java/")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "src/test/resources/features/")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "e2e/stepdefinitions")
//@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")
//@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")

public class TestRunner {

}
