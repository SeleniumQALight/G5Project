@LoginTest @FullRegression
Feature: User Login
@R001
  Scenario Outline: R001  Login with invalid login
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / pasword'


    Examples:
    | login       | password |
    | wrong login | 1234     |


  @R002
  Scenario Outline: R002 Login with valid login
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees profile avatar

    Examples:
      | login        | password         |
      | kostiushko1  | Filinoertii1991a |