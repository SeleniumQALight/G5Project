package apiTests;

import api.EndPointsForPrivatBank;
import api.ExchangeCourseResponseFromPrivatBankDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestsForPrivatBankHW1 {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeJsonCourseFromPrivatBank() {
        ExchangeCourseResponseFromPrivatBankDTO[] responseBodyForPrivatBank =
        given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid", 11)
                .log().all()
        .when()
                .get(EndPointsForPrivatBank.EXCHANGE)
        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ExchangeCourseResponseFromPrivatBankDTO[].class);
        logger.info("Number of exchange courses " + responseBodyForPrivatBank.length);

        ExchangeCourseResponseFromPrivatBankDTO[] expectedResultForPrivatBank = {
                new ExchangeCourseResponseFromPrivatBankDTO("USD", "UAH"),
                new ExchangeCourseResponseFromPrivatBankDTO("EUR", "UAH"),
                new ExchangeCourseResponseFromPrivatBankDTO("RUR", "UAH"),
                new ExchangeCourseResponseFromPrivatBankDTO("BTC", "USD")
        };

        Assert.assertEquals("Number of exchange courses ", expectedResultForPrivatBank.length, responseBodyForPrivatBank.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResultForPrivatBank.length; i++) {
            softAssertions.assertThat(responseBodyForPrivatBank[i]).isEqualToIgnoringGivenFields(expectedResultForPrivatBank[i], "buy", "sale");
        }

        softAssertions.assertAll();

    }
}