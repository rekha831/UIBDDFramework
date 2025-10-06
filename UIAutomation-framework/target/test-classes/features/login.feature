Feature: SpicejetLogin feature

  Scenario Outline: Successful login with valid credentials
    Given I am on the login page
    When I login with "<userType>" credentials
    Then I should see the "<expectedResult>" on dashboard

    Examples: 
      | userType    | expectedResult |
      | invalidUser | loginError     |
      
       Scenario Outline: Successful login with invalid credentials
    Given I am on the login page
    When I login with "<userType>" credentials
    Then I should see the "<expectedResult>" error on login page

    Examples: 
      | userType    | expectedResult |
      | invalidUser | loginError     |
