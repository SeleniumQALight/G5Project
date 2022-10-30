Feature: User Login
  @R003
  Scenario Outline: R003 Register with invalid data
    Given User opens 'Login' page
    When User enters '<username>' username into 'Username' register input on 'Login' page
    And User enter '<email>' email into 'Email' register input on 'Login' page
    And User enter '<password>' password into 'Password' register input on 'Login' page
    Then Validation errors '<error>' appears


    Examples:
      | username  | email              | password      | error                              |
      | fa        | email123@gmail.com | 1234567890123 | Username must be at least 3 characters.  |
      | fa        | email123           | 1234567890123 | Username must be at least 3 characters.;You must provide a valid email address.  |
