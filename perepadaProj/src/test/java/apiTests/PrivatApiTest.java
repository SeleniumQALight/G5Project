package apiTests;
import api.privatBank.PrivatDTO;
import api.privatBank.PrivatEndPoints;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PrivatApiTest {

    @Test
    public void getCurrencyCourses() {
        PrivatDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid", 11)
                .log().all()
                .when()
                .get(PrivatEndPoints.GET_COURSE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatDTO[].class);

        PrivatDTO[] expectedResult = {

                PrivatDTO.builder().ccy("USD").baseCcy("UAH").build(),
                PrivatDTO.builder().ccy("EUR").baseCcy("UAH").build(),
                PrivatDTO.builder().ccy("RUR").baseCcy("UAH").build(),
                PrivatDTO.builder().ccy("BTC").baseCcy("USD").build()
        };

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i], "buy", "sale");
        }

        softAssertions.assertAll();

    }
}
