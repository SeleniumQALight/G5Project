@RegistrationTest @Regression
  Feature: Registration Form

    @R003
    Scenario Outline: R003 Registration with invalid username <username> and email <email>
      Given User opens 'Login' page
      When User enters '<username>' username into 'Username' input on 'Login' page
      And User enters '<email>' email into 'Email' input on 'Login' page
      And User enters '<password>' password into 'Password' input on 'Login' page
      Then User sees error messages '<expectedErrors>' on 'Login' page

      Examples:
        | username   | email              | password      | expectedErrors                                                                         |
        | /mikjiujd  | 545646845dshgyusgz | gh45ztp89qaz  | Username can only contain letters and numbers.;You must provide a valid email address. |
        | ropowpii   | toirutoir@ukr.net  | dfjkhdfdu     | Password must be at least 12 characters.                                               |
