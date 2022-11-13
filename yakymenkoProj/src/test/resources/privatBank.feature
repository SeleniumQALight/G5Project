@FullRegression
Feature: PrivatBank Feature

  @R005
  Scenario Outline: R005 Compare API and UI exchange rates
    Given User receives the purchase and sale rates through the PrivatBank API for the currency '<default>' and saves them
    When User opens Home page on site PrivatBank
    Then User receives the purchase and sale rates through the PrivatBank UI for the currency '<default>' and saves them
    And Compare purchase and sale exchange rates for a currency pair via API and UI

    Examples:
      | default |
      | EUR     |
      | USD     |