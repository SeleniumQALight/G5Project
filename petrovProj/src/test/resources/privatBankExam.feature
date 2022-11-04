@CurrencyExchangeTest @FullRegression
Feature: Exchange currency

  @R020
  Scenario Outline: R020 Exchange currency
    Given User send API and save currency rates from API '<Currency from>' to '<Currency to>'
    When Open privat bank 'home' page
    And User save currency rates from UI for '<Currency from>' to '<Currency to>'
    Then User comparing saved rates for UI and Api for '<Currency from>' to '<Currency to>'


    Examples:
    | Currency from | Currency to    |
    | USD           | UAH            |
    | EUR           | UAH            |


