package apiTests;

import api.PrivatApiDTO;
import api.PrivatEndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Privat24ApiTest {
    Logger logger = Logger.getLogger(getClass());
    int coursid = 11;

    @Test
    public void getTicketByApi() {
        PrivatApiDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("exchange")
                        .queryParam("json")
                        .queryParam("coursid", coursid)
                .when()
                        .get(PrivatEndPoints.currentCurse)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(PrivatApiDTO[].class);

        PrivatApiDTO[] expectedResult = {
                new PrivatApiDTO("USD", "UAH"),
                new PrivatApiDTO("EUR", "UAH"),
                new PrivatApiDTO("RUR", "UAH"),
                new PrivatApiDTO("BTC", "USD")
        };

        Assert.assertEquals("Number of curses ", expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(expectedResult[i], "buy", "sale");
        }

        softAssertions.assertAll();
    }
}
