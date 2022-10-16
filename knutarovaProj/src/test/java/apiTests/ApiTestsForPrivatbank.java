package apiTests;

import api.EndPointsForPrivatbank;
import api.PrivatbankCourseExchangeDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestsForPrivatbank {
    Logger logger = Logger.getLogger(getClass());

        @Test
        public void getCourseExchangeFromPrivatbank(){
            PrivatbankCourseExchangeDTO[] responseBody = given()
                    .contentType(ContentType.JSON)
                    .queryParam("exchange")
                    .queryParam("json")
                    .queryParam("coursid", 11)
                    .log().all()
                .when()
                    .get(EndPointsForPrivatbank.COURSE_EXCHANGE)
                .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .response().as(PrivatbankCourseExchangeDTO[].class)
            ;
            logger.info("Number of currency = " + responseBody.length);
            logger.info("ccy: " + responseBody[0].getCcy() + "; " + "base_ccy: " + responseBody[0].getBase_ccy());
            logger.info("ccy: " + responseBody[1].getCcy() + "; " + "base_ccy: " + responseBody[1].getBase_ccy());
            logger.info("ccy: " + responseBody[2].getCcy() + "; " + "base_ccy: " + responseBody[2].getBase_ccy());
            logger.info("ccy: " + responseBody[3].getCcy() + "; " + "base_ccy: " + responseBody[3].getBase_ccy());

            PrivatbankCourseExchangeDTO[] expectedResult = {
                  new PrivatbankCourseExchangeDTO("USD", "UAH"),
                  new PrivatbankCourseExchangeDTO("EUR", "UAH"),
                  new PrivatbankCourseExchangeDTO("RUR", "UAH"),
                  new PrivatbankCourseExchangeDTO("BTC", "USD")
            };

            Assert.assertEquals("Number of currency exchange ", expectedResult.length, responseBody.length);
            SoftAssertions softAssertions = new SoftAssertions();

            for (int i = 0; i < expectedResult.length; i++) {
                softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(expectedResult[i]
                        , "buy", "sale");
            }
            softAssertions.assertAll();
        }
}

