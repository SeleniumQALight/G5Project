@RegistrationTest @FullRegression
Feature: Registration form on login page

    @R003
    Scenario Outline: check errors message with invalid registration data
      Given User open 'Login' page
      When User enters '<userName>' username into 'Pick a username' input on 'Login' page
      And User enters '<email>' into 'email' input on 'Login' page
      And User enters '<pass>' password into 'password' input on 'Login' page
      Then Check '<errorMessage>' on 'Login' page

      Examples:
        | userName | email      |    pass        |errorMessage                                                                                                               |
        | tr       | tr.fd      |      wer3      |Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.   |
        | tr       | tr.fd      |qwer1234rewfdr43|Username must be at least 3 characters.;You must provide a valid email address.                                            |
        | tr       | tr@fd.tt   |      wer3      |Username must be at least 3 characters.;Password must be at least 12 characters.                                           |
        | tr54     | tr.fd      |      wer3      |You must provide a valid email address.;Password must be at least 12 characters.                                           |
