package apiTests;

import api.EndPointsPrivatBank;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestsPrivatBank {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
        RatesDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid", 11)
                .log().all()
                .when()
                .get(EndPointsPrivatBank.GATE_RATES)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(RatesDTO[].class);

        logger.info("Counts of rates: " + responseBody.length);

        for (int i = 0; i < responseBody.length - 1; i++) {
            String base_ccy = "UAH";
            Assert.assertEquals("'base_ccy' is not matched ", base_ccy, responseBody[i].base_ccy);
        }

        RatesDTO[] expectedResult = {
                new RatesDTO("USD", "UAH"),
                new RatesDTO("EUR", "UAH"),
                new RatesDTO("RUR", "UAH"),
                new RatesDTO("BTC", "USD")
        };

        Assert.assertEquals("Counts of rates: ", expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }
}
