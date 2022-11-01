@LoginTest @FullRegression
Feature: User Login

  @R001
  Scenario Outline: R001 Login with invalid login <login>
    Given User open 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / pasword'

    Examples:
    | login             | password    |
    | wrong login       | 123         |
    | wrong login 123   | 123 123     |


  @R002
  Scenario Outline: R002 login with valid data <login>
    Given User open 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page and redirect to Home page
    Then User sees avatar icon on 'Home' page

    Examples:
      | login  | password     |
      | qaauto | 123456qwerty |

