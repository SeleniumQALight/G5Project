Feature: User registration

  @R003
  Scenario Outline: R003 Registration with invalid username
    Given User opens 'Login' page
    And User enters data '<username>' to 'Username' field
    And User enters data '<email>' to 'Email' field
    And User enters data '<password>' to 'Password' field
    Then User sees error message to 'Username' field 'Username must be at least 3 characters.'
    Examples:
      | username | email           | password     |
      | nam      | email@gmail.com | qwerty123456 |

  @R004
  Scenario Outline:
    Given User opens 'Login' page
    And User enters data '<username>' to 'Username' field
    And User enters data '<email>' to 'Email' field
    And User enters data '<password>' to 'Password' field
    Then User sees error message to 'Email' field 'You must provide a valid email address.'
    Then User sees error message to 'Password' field 'Password must be at least 12 characters.'
    Examples:
      | username | email     | password |
      | name     | gmail.com | qwerty   |

