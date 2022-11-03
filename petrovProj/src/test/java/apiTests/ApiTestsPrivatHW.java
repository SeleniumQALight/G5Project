package apiTests;

import api.ApiHelper;
import api.PrivatHW.EndPointsExchangeCours;
import api.PrivatHW.ExchangeCoursResponseDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestsPrivatHW {

    ApiHelper apiHelper = new ApiHelper();
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeCoursByPrivat24() {

        ExchangeCoursResponseDTO[] responseBody = apiHelper.getExchangeRateByPrivat24();

        logger.info("number of posts = " + responseBody.length);

        ExchangeCoursResponseDTO[] expectedResult = {
                new ExchangeCoursResponseDTO("USD", "UAH"),
                new ExchangeCoursResponseDTO("EUR", "UAH"),
                new ExchangeCoursResponseDTO("RUR", "UAH"),
                new ExchangeCoursResponseDTO("BTC", "USD")
        };

        Assert.assertEquals("number of posts ", expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(expectedResult[i], "buy", "sale");
        }

        softAssertions.assertAll();

    }
}
