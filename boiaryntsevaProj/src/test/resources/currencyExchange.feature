@CurrencyExchangeTest @FullRegression
Feature: Currency Exchange

  @R088
  Scenario Outline: R088 Currency exchange E2E test
    Given '<currency>' exchange rate is received through API
    When User opens Privat bank landing page
    Then '<currency>' exchange rates on UI matches exchange from API

    Examples:
      | currency |
      | USD      |
      | EUR      |

