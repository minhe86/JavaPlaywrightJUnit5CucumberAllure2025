Feature: GET request to API endpoint and validate response

  Scenario: Send a GET request and validate key value in response.
    When Send a GET request to URL
    Then The response contains the key "word" with value "playwright"
    When Send a GET request to endpoint "https://api.dictionaryapi.dev/api/v2/entries/en/Cucumber"
    Then The response contains the key "word" with value "cucumber"
