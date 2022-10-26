package apiTests;

import api.PrivatBankDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import api.EndPointsPrivatBank;


import static io.restassured.RestAssured.given;

public class ApiTestForPrivatBank {
    Logger logger = Logger.getLogger(getClass());
    int coursid = 11;

    @Test
    public void getCyrrency(){
        PrivatBankDTO[] responseBodyCYrrency = given()
                .contentType(ContentType.JSON)
                .queryParam("exchange").queryParam("json").queryParam("coursid", coursid)
                .log().all()
        .when()
                .get(EndPointsPrivatBank.EXCHANGE)
        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatBankDTO[].class);

        PrivatBankDTO[] expectedResultresponseBodyCYrrency = {
                PrivatBankDTO.builder().currency("USD").base_currency("UAH").build(),
                PrivatBankDTO.builder().currency("EUR").base_currency("UAH").build(),
                PrivatBankDTO.builder().currency("RUR").base_currency("UAH").build(),
                PrivatBankDTO.builder().currency("BTC").base_currency("USD").build()
        };

        Assert.assertEquals("Number of cyrrency", expectedResultresponseBodyCYrrency.length, responseBodyCYrrency.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResultresponseBodyCYrrency.length; i++) {
        softAssertions.assertThat(responseBodyCYrrency[i]).isEqualToIgnoringGivenFields(expectedResultresponseBodyCYrrency[i], "buy", "sale");
        }

        softAssertions.assertAll();
    }
}
