@LoginTest @FullRegression
Feature: User Login

    @R002
    Scenario Outline: R002 Login with valid login
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<password>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User is logged in

      Examples:
        | login   | password |
        | rosko48 | 12345678912345  |
