Feature: privat Feature

  @R0012
  Scenario Outline: R0012
    Given Getting values via API for '<currency>' currency and save
    When User open PrivatBank Home page
    Then Getting values via UI for '<currency>' currency and save
    And Check API and UI currency

    Examples:
      | currency  |
      | USD       |
      | EUR       |