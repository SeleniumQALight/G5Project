@LoginTest @FullRegression
Feature: User Login

  @R001
  Scenario Outline: R001 Login with invalid login <login> and wrong password <password>
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / pasword'

    Examples:
      | login         | password |
      | wrong login   | 1234     |
      | wrong login12 | 1234sad  |

  @R002
  Scenario Outline: R002 Login with valid data
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees avatar icon on header

    Examples:
      | login     | password     |
      | qaauto    | 123456qwerty |
      | oleksandr | Password0000 |