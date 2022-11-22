@LoginTest @FullRegression
Feature: User Registration

  @R003
  Scenario Outline: R003 Check alert messages with invalid creds on registration form
    Given User opens 'Login' page
    When User enters '<username>' into 'Username' input on 'Registration' form
    And User enters '<email>' into 'Email' input on 'Registration' form
    And User enters '<password>' into 'Password' input on 'Registration' form
    Then User sees alert message(s) with text '<Error message(s)>'

    Examples:
      | username   | email         | password     | Error message(s)                         |
      | ol         | test12@com.ua | 123456qwerty | Username must be at least 3 characters.  |
      | oleksandr  | test13@com.ua | 123456qwerty | That username is already taken.          |
      | oleksandr1 | test@com.ua   | 123456qwerty | That email is already being used.        |
      | oleksandr2 | com.ua        | 123456qwerty | You must provide a valid email address.  |
      | oleksandr3 | test14@com.ua | 12345678901  | Password must be at least 12 characters. |