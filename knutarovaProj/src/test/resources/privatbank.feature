@PrivatbankTest @FullRegression
Feature: Currency Exchange Feature

  @R023
  Scenario Outline: R023 Currency comparison
    Given User save currency rate <Currency_to_exchange> to <Base_currency> from PrivatbankApi to TestData
    When User opens 'PrivatbankUi' Page and save currency rate <Currency_to_exchange> to <Base_currency> to TestData
    Then User compare currency rates from PrivatbankApi and 'PrivatbankUi' Page

    Examples:
      | Currency_to_exchange | Base_currency |
      | USD                  | UAH           |
      | EUR                  | UAH           |

