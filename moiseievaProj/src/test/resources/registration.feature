@RegistrationTest @FullRegression
Feature: User registration

  @R003
  Scenario Outline: R003 Registration with invalid username
    Given User opens 'Login' page
    And User enters data '<username>' to 'Username' field on 'Registration' form
    And User enters data '<email>' to 'Email' field on 'Registration' form
    And User enters data '<password>' to 'Password' field on 'Registration' form
    And User click submit button 'Sign up for OurApp' on 'Registration' form
    Then User sees error message to 'Username' field 'Username must be at least 3 characters.'
    Examples:
      | username | email           | password     |
      | na       | mail1@gmail.com | qwerty123456 |

  @R004
  Scenario Outline: R004 Registration with invalid email
    Given User opens 'Login' page
    And User enters data '<username>' to 'Username' field on 'Registration' form
    And User enters data '<email>' to 'Email' field on 'Registration' form
    And User enters data '<password>' to 'Password' field on 'Registration' form
    And User click submit button 'Sign up for OurApp' on 'Registration' form
    Then User sees error message to 'Email' field 'You must provide a valid email address.'
    Then User sees error message to 'Password' field 'Password must be at least 12 characters.'
    Examples:
      | username | email     | password |
      | naming   | gmail.com | qwerty   |

  @R005
  Scenario Outline: R005 Registration with invalid parameters
    Given User opens 'Login' page
    And User enters data '<username>' to 'Username' field on 'Registration' form
    And User enters data '<email>' to 'Email' field on 'Registration' form
    And User enters data '<password>' to 'Password' field on 'Registration' form
    And User click submit button 'Sign up for OurApp' on 'Registration' form
    Then User sees error message with text '<error message>'
    Examples:
      | username | email           | password     | error message                                                                    |
      | na       | mail1@gmail.com | qwerty123456 | Username must be at least 3 characters.                                          |
      | naming   | mail@gmail.com  | qwerty123456 | That email is already being used.                                                |
      | naming   | gmail.com       | qwerty123456 | You must provide a valid email address.                                          |
      | naming   | mail1@gmail.com | qwerty       | Password must be at least 12 characters.                                         |
      | na       | mail1@gmail.com | qwerty       | Username must be at least 3 characters.;Password must be at least 12 characters. |

