Feature: Privat Feature

  @R050
  Scenario Outline: R050 Compare exchange rates API vs UI
    Given User gets buy and sell exchange rates via API for '<default>' currency and save
    When User opens privatbank Home page
    Then User gets buy and sell exchange rates from UI for '<default>' currency and save
    And Check if buy and sell exchange rates for currency pair from API and UI are equal

    Examples:
      | default |
      | USD     |
      | EUR     |