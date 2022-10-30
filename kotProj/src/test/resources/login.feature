Feature: User Login
  Scenario Outline: R001 Login with invalid Login
    Given  User opens 'Login' page

    Examples:
    |login|password|
    |wrong login|1234|


