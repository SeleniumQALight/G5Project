package apiTests;

import api.CurrencyDTO;
import api.PrivatBankApiHelper;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

public class CurrencyExchangeApiTest {
    String courseId = "11";
    Logger logger = Logger.getLogger(getClass());
    PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();


    @Test

    public void getCurrencyExchange() {
        CurrencyDTO[] actualResponse = privatBankApiHelper.getCurrencyExchangeList(courseId);

        CurrencyDTO[] expectedResponse =

                {
                        new CurrencyDTO("USD", "UAH"),
                        new CurrencyDTO("EUR", "UAH"),
                        new CurrencyDTO("RUR", "UAH"),
                        new CurrencyDTO("BTC", "USD")

                };
        Assert.assertEquals(expectedResponse.length, actualResponse.length);
        logger.info("Length is " + actualResponse.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < actualResponse.length; i++) {
            softAssertions.assertThat(actualResponse[i])
                    .isEqualToIgnoringGivenFields(expectedResponse[i], "buy", "sale");
        }
        softAssertions.assertAll();

    }
}
