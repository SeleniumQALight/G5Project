
@CheckCurrencyPBTest @FullRegression
Feature: Exam


   @R012
  Scenario Outline: R012 Check curency with api and ui
    Given Check'<curency>'via API for PB and save value
    When User open 'Home' page '<curency>' privat bank and save value
    Then Check via API for currency on UI


     Examples:
       |curency|
       |EUR|
       |USD|