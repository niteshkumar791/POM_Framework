Feature: Orange HRM

  @login
  Scenario: Login to admin
    Given user opens the HRM browser "chrome" and url "https://opensource-demo.orangehrmlive.com/index.php/"
    Then user send username "Admin" and password "admin123"
    And user click on login button
    Then validate the outcome
    And user click on logout

  Scenario Outline: Login to admin
    Given user opens the HRM browser "chrome" and url "https://opensource-demo.orangehrmlive.com/index.php/"
    Then user send username <username> and password <password>
    And user click on login button
    Then validate the outcome
    And user click on logout

    Examples: 
      | username | password |
      | Admin    | admin123 |
      | ADMIN    | admin111 |
