Feature: Privat bank exchange currency

  @R100
  Scenario Outline: R100 Privat bank exchange currency
    Given User received buying and selling rates of currencies through '<currency>' API
    When User opened privatBank Home page
    And User received buying and selling rates of currencies through '<currency>' UI
    Then Checking for equality of buying and selling rates of currencies: API and UI

    Examples:
      | currency |
      | USD      |
      | EUR      |