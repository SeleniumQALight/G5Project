package apiTests;

import api.privat.CurrencyDTO;
import api.privat.EndpointsPrivat;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestPrivatCurrency {
    String Usd="USD";
    String Eur="EUR";
    String rur="RUR";
    String Btc="BTC";
    int parameterOfEndpoint=11;

    Logger logger= Logger.getLogger(getClass());
    @Test
    public void getAllCurrency(){
        CurrencyDTO[] responseBody= given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .get(EndpointsPrivat.POST_BY_USER_CURRENCY,parameterOfEndpoint)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);
        // зробить фор
        logger.info("Number of currency = "+responseBody.length);
        for (int i = 0; i < responseBody.length; i++) {
            logger.info("Код валюти = "+responseBody[i].getCcy());
            logger.info("Код національної валюти = "+responseBody[i].getBase_ccy());

        }

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("currency doesn't match  with",Usd,responseBody[0].getCcy());
            Assert.assertEquals("currency doesn't match  with",Eur,responseBody[1].getCcy());
            Assert.assertEquals("currency doesn't match  with",rur,responseBody[2].getCcy());
            Assert.assertEquals("currency doesn't match  with",Btc,responseBody[3].getCcy());
        }
        CurrencyDTO[] expectedResult = {
                new CurrencyDTO("USD", "UAH"),
                new CurrencyDTO("EUR", "UAH"),
                new CurrencyDTO("RUR", "UAH"),
                new CurrencyDTO("BTC", "USD")

        };
        Assert.assertEquals("Number of currency",expectedResult.length,responseBody.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i],"buy","sale");
        }
        softAssertions.assertAll();
    }
}
