
Feature: User Login

  Scenario Outline: R001 Login with invalid login
    Given User open 'Login' page


    Examples:
    | login       | password |
    | wrong login | 123      |