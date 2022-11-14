package api;

import io.restassured.http.ContentType;
import libs.TestData;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiHelperForPrivatbank {
    Logger logger = Logger.getLogger(getClass());

    public PrivatbankCourseExchangeForCompareDTO[] getAndSaveCourseExchangeForCompareFromPrivatbank(String currency_to_exchange, String base_currency) {
        PrivatbankCourseExchangeForCompareDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid", 5)
                .log().all()
                .when()
                .get(EndPointsForPrivatbank.COURSE_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PrivatbankCourseExchangeForCompareDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equalsIgnoreCase(currency_to_exchange) && responseBody[i].getBase_ccy()
                    .equalsIgnoreCase(base_currency)) {
                TestData.RATE_TO_BUY_API = Float.valueOf(responseBody[i].getBuy());
                TestData.RATE_TO_SALE_API = Float.valueOf(responseBody[i].getSale());
                logger.info("From API: rate to buy: " + responseBody[i].getBuy() + "; " + "rate to sale: " + responseBody[i].getSale());
            }
        }
        return responseBody;
    }
}
