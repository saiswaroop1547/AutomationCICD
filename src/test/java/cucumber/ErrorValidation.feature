@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario
    Given Launch the Application
    When Login with Username <email> and Password <password>
    Then "Incorrect email or password." message is displayed
    
    Examples: 
      | email    							 | password 	|
      | saiswaroop@gmail.com   | Test@12  	|