@CurrencyExchangeTest @FullRegression
Feature: Exchange currency

  @R020
  Scenario Outline: R020 Exchange currency
    Given Get and save currency rates from API '<Currency from>' to '<Currency to>'
    When




    Examples:
    | Currency from | Currency to    |
    | EUR           | UAH            |
    | USD           | UAH            |


