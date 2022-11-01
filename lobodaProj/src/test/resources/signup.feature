@FullRegression
Feature: User login

  @R003
  Scenario Outline: R003 Registration with invalid data
    Given User opens 'Login' page
    When User enters '<login>' into 'Registration username' input on 'Login' page
    And User enters '<email>' into 'Registration email' input on 'Login' page
    And User enters '<password>' into 'Registration password' input on 'Login' page
    Then User sees error messages '<expectedErrors>' on 'Login' page

    Examples:
      | login       | email        |    password  |  expectedErrors                                                                                                           |
      |   vl        | lll          |      123     | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.  |
      |   vl        | test         | qwerty123456 | Username must be at least 3 characters.;You must provide a valid email address.                                           |
      |   vl        | tes@tes.tes  | qwerty123456 | Username must be at least 3 characters.                                                                                   |
