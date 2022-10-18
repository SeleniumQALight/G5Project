package apiTests;

import api.CurrencyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.BankEndPoints.exchangeURL;
import static io.restassured.RestAssured.given;

public class CurrencyExchangeApiTest {
    String courseId="11";
    Logger logger = Logger.getLogger(getClass());


    @Test

    public void getCurrencyExchange() {
        CurrencyDTO [] actualResponse = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("exchange")
                .queryParam("json")
                .queryParams("coursid","11")
                .when()
                .get(exchangeURL)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(CurrencyDTO[].class);

        CurrencyDTO[] expectedResponse = {
                new CurrencyDTO("USD","UAH"),
                new CurrencyDTO("EUR","UAH"),
                new CurrencyDTO("RUR","UAH"),
                new CurrencyDTO("BTC","USD")

        };
        Assert.assertEquals(expectedResponse.length, actualResponse.length);
        logger.info("Length is " + actualResponse.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < actualResponse.length; i++) {
            softAssertions.assertThat(actualResponse[i])
                    .isEqualToIgnoringGivenFields(expectedResponse[i],"buy","sale");
        }
            softAssertions.assertAll();

    }
}
