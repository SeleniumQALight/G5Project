
Feature: User Login

  Scenario Outline: R001 Login with invalid login //номер з джири
    Given User opens 'Login' page

    Examples:
    | login       | password |
    | wrong login | 1234     |