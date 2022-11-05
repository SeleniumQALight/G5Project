package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import libs.TestData;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
            BigDecimal bigDecimalBuy = BigDecimal.valueOf(responseBody[i].getBuy());
            BigDecimal bigDecimalSale = BigDecimal.valueOf(responseBody[i].getSale());
            if (responseBody[i].getCcy().equalsIgnoreCase(currency)){
                TestData.RATE_BUY_API = Float.parseFloat(String.valueOf(bigDecimalBuy.setScale(2, RoundingMode.HALF_UP)));
                TestData.RATE_SALE_API = Float.parseFloat(String.valueOf(bigDecimalSale.setScale(2, RoundingMode.HALF_UP)));
                logger.info("From API: Currency " + currency + " buy rate is " + TestData.RATE_BUY_API +
                        " and sell rate is " + TestData.RATE_SALE_API);
            }
        }
    }
}
