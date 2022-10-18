package apiTests;

import api.*;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;



public class ApiTestPrivat24 {


    @Test
    public void getALLPostsByUser() {
        Private24DTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid",11)
                .when()
                .get(EndPointsPrivat24.currency)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(Private24DTO[].class);
        Private24DTO[] expectedResult = {
                new Private24DTO("USD", "UAH"),
                new Private24DTO("EUR", "UAH"),
                new Private24DTO("RUR", "UAH"),
                new Private24DTO("BTC", "USD"),
        };
        Assert.assertEquals("Number of currency",expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i <expectedResult.length ; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i], "buy", "sale");

        }

        softAssertions.assertAll();
}}
