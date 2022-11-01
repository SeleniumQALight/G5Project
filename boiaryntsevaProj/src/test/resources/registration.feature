@RegistrationTest @FullRegression
Feature: User Registration

  @R003
  Scenario Outline: R003 Register with invalid parameters
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page register section
    And User enter '<email>' email into 'Email' input
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page register section
    And User click on 'SingUp' button on 'Login' page
    Then User sees error message with '<errorMessageText>' text

    Examples:
      | login  | email         | password     | errorMessageText                                                                 |
      | qw     | qwerty@gm.com | 12           | Username must be at least 3 characters.;Password must be at least 12 characters. |
      | 123098 | 123           | qwerty098765 | You must provide a valid email address.                                          |