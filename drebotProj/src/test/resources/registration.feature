@LoginTest @RegistrationTest @FullRegression
Feature: User Registration

  @R003
  Scenario Outline: R003 Registration with invalid Username <username> Email <email> Password <password>
    Given User opens 'Login' page
    When User enters '<username>' username into 'Username' input on 'Login' page registration section
    And User enters '<email>' email into 'Email' input on 'Login' page registration section
    And User enters '<password>' password into 'Password' input on 'Login' page registration section
    Then User sees alert message with '<errormessages>' text

    Examples:
      | username | email      | password | errormessages                                                                                                            |
      | tr       | test.com   | 123      | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | qaauto   | qqq        | 123      | That username is already taken.;You must provide a valid email address.;Password must be at least 12 characters.         |
      | tr       | test@q.com | 123      | Username must be at least 3 characters.;Password must be at least 12 characters.                                         |
