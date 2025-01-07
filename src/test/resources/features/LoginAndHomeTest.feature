Feature: Login and Verify all products details on home page then Log out.

  Scenario Outline: Login to SwagLabs application, verify home page all products details via different parameters, and log out.
    # Learn Java Playwright by this example, try to cover passing test data via different ways: cucumber step argument, data table, csv file; support dynamical page factory.
    Given User launches SwagLabs application
    Then User is directed to "Login" page
#    When User logs in the app using username "standard_user" and password "secret_sauce"
    When User logs in the app using username "<UserName>" and password "<Password>"
    Then User is directed to "Home" page
    When User verifies all products details on Home page
    When User verifies the product details of product 2 on Home page
    When User verifies the product name "<ProductName>"
    When User logs out
    Then User is directed to "Login" page

    Examples:
      | UserName      | Password     | ProductName             |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt |
