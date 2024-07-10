Feature: Login with Invalid credentials

  @ErrorValidation
  Scenario Outline: Negative Test For Login
    Given Land on Ecommerce Page
    When Login with username <username> and password <password>
    Then Verify Error message " Incorrect email or password. " is Displayed
    Examples:
      | username                    | password    |
      |vishal.jadhav.2805@gmail.com | Vishal2805  |
      |vishal@gmail.com             | vishal      |