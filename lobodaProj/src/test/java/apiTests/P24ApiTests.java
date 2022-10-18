package apiTests;

import api.P24EndPoints;
import api.P24ExchangeRateDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class P24ApiTests {

    @Test
    public void getExchangeRates(){
        P24ExchangeRateDTO[] responseBody = given ()
                .contentType(ContentType.JSON)
                .log().all()
                .param("exchange")
                .param("json")
                .param("coursid","11")
        .when()
                .get(P24EndPoints.EXCHANGE_RATE)
        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(P24ExchangeRateDTO[].class);

        P24ExchangeRateDTO[] expectedResult = {
                new P24ExchangeRateDTO("USD", "UAH"),
                new P24ExchangeRateDTO("EUR", "UAH"),
                new P24ExchangeRateDTO("RUR", "UAH"),
                new P24ExchangeRateDTO("BTC", "USD")
        };

        Assert.assertEquals("Number of Exchange Rates:", expectedResult.length,responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i],"buy", "sale");
        }
        softAssertions.assertAll();
    }
}
