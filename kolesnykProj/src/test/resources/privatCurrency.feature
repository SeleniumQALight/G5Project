Feature: Test Currency

  @R123
  @CallPrivatBankAPIToGetCurrency
  Scenario Outline: R123 Check currency from UI and API is the same
    Given Store buy value for '<currency>' currency
    When  Open PrivatBank site and get '<currency>' curs
    Then Check that currency value is the same on UI and API
    Examples:
      | currency |
      | USD      |
      | EUR      |
