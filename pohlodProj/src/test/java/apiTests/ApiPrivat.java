package apiTests;

import apiPrivat.EndPointsPrivat;
import apiPrivat.ExchangeDTOPrivat;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiPrivat {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCurs(){
        ExchangeDTOPrivat[] responseBodyPrivat = given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid",11)
                .log().all()
           .when()
                .get(EndPointsPrivat.EXCHANGE)
           .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(ExchangeDTOPrivat[].class)
        ;
        logger.info("Number of currency " + responseBodyPrivat.length);


    ExchangeDTOPrivat[] expectedResultPrivat = {
            new ExchangeDTOPrivat("USD", "UAH"),
            new ExchangeDTOPrivat("EUR", "UAH"),
            new ExchangeDTOPrivat("RUR", "UAH"),
            new ExchangeDTOPrivat("BTC", "USD")
    };

        Assert.assertEquals("Number of currency ", expectedResultPrivat.length, responseBodyPrivat.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResultPrivat.length; i++) {
            softAssertions.assertThat(responseBodyPrivat[i])
                    .isEqualToIgnoringGivenFields(expectedResultPrivat[i], "buy", "sale");
        }

        softAssertions.assertAll();

    }



}
