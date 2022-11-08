@FullRegression
Feature: User login

  @R003
  Scenario Outline: R003 User registration with invalid data
    Given User opens 'Login' page
    When User enters '<login>' into 'Input username' input on 'Login' page
    And User enters '<email>' into 'Input email' input on 'Login' page
    And User enters '<password>' into 'Input password' input on 'Login' page
    Then User sees error messages '<expectedErrors>' on 'Login' page

    Examples:
      | login | email          |   password          |  expectedErrors                                                                                                           |
      | nL    | test           |      11             | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.  |
      | nL    | test           | zuma13298@gmail.com | Username must be at least 3 characters.;You must provide a valid email address.                                           |
      | nL    | valid@test.com | zuma13298@gmail.com | Username must be at least 3 characters.                                                                                   |
