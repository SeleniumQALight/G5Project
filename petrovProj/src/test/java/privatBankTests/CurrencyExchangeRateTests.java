package privatBankTests;

import baseTest.BaseTestPrivatBank;
import org.junit.Test;

public class CurrencyExchangeRateTests extends BaseTestPrivatBank {

    @Test
    public void getCurrencyExchangeRate(){
        privatBankMainPage
                .openPrivateBankPage()
                .saveCurrencyExchangeRates();

    }





















}
