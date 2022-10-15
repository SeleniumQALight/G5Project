package apiTests;

import api.EndPointsP24;
import api.ExchangeDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestsP24 {
    Logger logger = Logger.getLogger(getClass());
    String baseCcyForNotCrypto = "UAH";

    @Test
    public void getAllCurrencies(){
        ExchangeDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid",11)
                .log().all()
           .when()
                .get(EndPointsP24.PUBINFO)
           .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ExchangeDTO[].class)
                ;
        logger.info("Number of exchange currencies = " + responseBody.length);

        ExchangeDTO[] expectedResult = {
                new ExchangeDTO("USD", baseCcyForNotCrypto),
                new ExchangeDTO("EUR", baseCcyForNotCrypto),
                new ExchangeDTO("RUR", baseCcyForNotCrypto),
                new ExchangeDTO("BTC", "USD")
        };
        Assert.assertEquals("Number of exchange currencies ", expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(expectedResult[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }
}
