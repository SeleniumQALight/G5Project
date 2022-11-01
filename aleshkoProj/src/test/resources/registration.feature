@RegistrationTest @FullRegression
  Feature: User register

    @R010
    Scenario Outline: User pass registration with errors
      Given User opens 'Login' page
      When User enters '<username>' into 'Username' input on 'Registration' form
      And User enters '<email>' into 'Email' input on 'Registration' form
      And User enters '<password>' into 'Password' input on 'Registration' form
      Then User sees alert message(s) with text '<Error message(s)>'

      Examples:
        | username | email          | password     | Error message(s)                        |
        | 1        | t12t@test.test | qwerty123456 | Username must be at least 3 characters. |
        | krill    | t12t@test.test | qwerty123456 | That username is already taken.         |
        | kirill   | test@test.test | qwerty123456 | That email is already being used.       |
        | kirill   | test           | qwerty123456 | You must provide a valid email address. |
        | kirill   | t12t@test.test | qwerty       | Password must be at least 12 characters.|