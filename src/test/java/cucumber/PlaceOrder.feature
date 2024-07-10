@tag
  Feature: Purchase the Order from Ecommerce Website

  Background:
    Given Land on Ecommerce Page

    @Regression
    Scenario Outline: Positive Test for Placing the Order
      Given Login with username <username> and password <password>
      When Add <productName> to Cart
      And Checkout <productName> and submit the order
      Then Verify " Thankyou for the order. " message is Displayed
      And Verify Valid Order ID is Displayed

      Examples:
      | username                    | password    | productName |
      |vishal.jadhav.2805@gmail.com | Vishalj2805 | ZARA COAT 3 |

