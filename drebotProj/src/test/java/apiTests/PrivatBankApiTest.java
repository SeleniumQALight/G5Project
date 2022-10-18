package apiTests;

import api.ExchangeInfoDTO;
import api.PrivatBankEndPoints;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PrivatBankApiTest {
    @Test
    public void getInfoExchangeCurrency() {
        ExchangeInfoDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("exchange")
                        .queryParam("json")
                        .queryParam("coursid", 11)
                        .log().all()

                        .when()
                        .get(PrivatBankEndPoints.EXCHANGE_COURSE_INFO)

                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(ExchangeInfoDTO[].class);


        ExchangeInfoDTO[] expectedResult = {
                new ExchangeInfoDTO("USD", "UAH"),
                new ExchangeInfoDTO("EUR", "UAH"),
                new ExchangeInfoDTO("RUR", "UAH"),
                new ExchangeInfoDTO("BTC", "USD")
        };

        Assert.assertEquals("Count of currency exchange ", expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i], "buy", "sale");
        }

        softAssertions.assertAll();

    }
}
