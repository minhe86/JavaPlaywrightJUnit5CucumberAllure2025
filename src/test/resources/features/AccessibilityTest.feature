Feature: Accessibility Check on the page

  @chrome @firefox @webkit
  Scenario Outline: Check accessibility on the page using Axe via "<browserName>".
    When Open the "Google" homepage in "<browserName>"
    Then Current page should pass accessibility checks
    When Open the "playwright java" homepage in "<browserName>"
    Then Current page should pass accessibility checks

    Examples:
      | browserName |
      | firefox      |
      | chrome      |
      | webkit      |
