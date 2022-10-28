Feature: User Registration

  @R003
  Scenario Outline: R003 Error messages check
    Given User opens 'Login' page
    When User enters '<username>' username into 'Username' input on 'Registration' form on 'Login' page
    And User enters '<email>' email into 'Email' input on 'Registration' form on 'Login' page
    And User enters '<password>' password into 'Password' input on 'Registration' form on 'Login' page
    Then User sees alert message(s) with text '<Error message(s)>'

    Examples:
    | username | email          | password     | Error message(s)                        |
    | 1        | test111@com.ua | 123456789101 | Username must be at least 3 characters. |
    | snik     | test111@com.ua | 123456789101 | That username is already taken.         |
    | test15   | test@com.ua    | 123456789101 | That email is already being used.       |
    | test15   | ttt            | 123456789101 | You must provide a valid email address. |
    | test15   | test111@com.ua | 123          | Password must be at least 12 characters.|