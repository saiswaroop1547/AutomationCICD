
@tag
Feature: Place an Order form the Ecommerce site
  I want to use this template for my feature file

	Background:
	Given Launch the Application

  @Regression
  Scenario Outline: Place an Order from Site
    Given Login with Username <email> and Password <password>
    When Added the Product <productName> to Cart
    And Submit the Order and Checkeout <productName> Country <country>
    Then "THANKYOU FOR THE ORDER." message is Displayed in ConfirmationPage

    Examples: 
      | email    							 | password 	| productName  	| country  |
      | saiswaroop@gmail.com   | Test@123 	| IPHONE 13 PRO | India    | 
      
