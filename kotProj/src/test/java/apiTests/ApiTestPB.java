package apiTests;

import apiPB.EndPoints;
import apiPB.PB_DTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class ApiTestPB {
    Logger logger = Logger.getLogger(getClass());

    @Test
public void getTodayCurrencyValues (){
        PB_DTO [] response_body = given()
                        .contentType(ContentType.JSON)
                        .queryParam("exchange")
                        .queryParam("json")
                        .queryParam("coursid","11")
                        .log().all()
                .when()
                        .get(EndPoints.exchangeUrl)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(PB_DTO[].class);


        PB_DTO [] expectedResult = {
                new PB_DTO("USD","UAH" ),
                new PB_DTO("EUR", "UAH"),
                new PB_DTO("RUR","UAH"),
                new PB_DTO("BTC","USD")
        };
Assert.assertEquals(expectedResult.length,response_body.length);
logger.info("Number of currencies is "+response_body.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(response_body[i]).
                    isEqualToIgnoringGivenFields(expectedResult[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }
}
