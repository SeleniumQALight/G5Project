Feature: User registration
  @R003
  Scenario Outline: R003  User registration with error messages
    Given User opens 'Login' page
    When User enters '<username>' username into 'Username' input on 'Registration' form on 'Login' page
    And User enters '<email>' email into 'Email' input on 'Registration' form on 'Login' page
    And User enters '<password>' password into 'Password' input on 'Registration' form on 'Login' page
    Then User sees error alert message with text '<Error message(s)>'

    Examples:
      | username   | email                        | password     | Error message(s)                                                                                                        |
      | k          | kostiushko.denis@gmail.com   | 123456789010 | Username must be at least 3 characters.;That email is already being used.                                               |
      | kostiushko1| kkk                          | 123456789010 | That username is already taken.;You must provide a valid email address.                                                 |
      | k          | kkk                          | 123          | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.|