package apiTests;

import api.PrivatHW.EndPointsExchangeCours;
import api.PrivatHW.ExchangeCoursResponseDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestsPrivatHW {

    String coursId = "11";

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeCoursByPrivat24() {
        ExchangeCoursResponseDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsExchangeCours.EXCHANGE, coursId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(ExchangeCoursResponseDTO[].class);

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
