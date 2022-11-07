package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import libs.TestData;
import org.apache.log4j.Logger;
import pages.PrivatHomePage;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivat {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    public void getExchangeRateByCurrency(String currency) {
        ExchangeDTO[] responseBody = given()
                .spec(requestSpecification)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid",5)
             .when()
                .get(EndPointsP24.PUBINFO)
             .then()
                .statusCode(200)
                .log().all()
             .extract().response().as(ExchangeDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equalsIgnoreCase(currency)){
                TestData.RATE_BUY_API = PrivatHomePage.rounding(responseBody[i].getBuy());
                TestData.RATE_SALE_API = PrivatHomePage.rounding(responseBody[i].getSale());
                logger.info("From API: Currency " + currency + " buy rate is " + TestData.RATE_BUY_API +
                        " and sell rate is " + TestData.RATE_SALE_API);
            }
        }
    }
}
