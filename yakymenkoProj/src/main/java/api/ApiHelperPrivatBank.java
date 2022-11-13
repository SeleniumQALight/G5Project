package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import libs.TestData;
import org.apache.log4j.Logger;
import pages.PrivatBankHomePage;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public void getExchangeRateByCurrency(String currency) {
        ExchangeCourseResponseFromPrivatBankDTO[] responseBody = given()
                .spec(requestSpecification)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid", 5)
                .when()
                .get(EndPointsForPrivatBank.EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(ExchangeCourseResponseFromPrivatBankDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equalsIgnoreCase(currency)) {
                TestData.COURSE_BUY_API = PrivatBankHomePage.roundingValue(responseBody[i].getBuy());
                TestData.COURSE_SALE_API = PrivatBankHomePage.roundingValue(responseBody[i].getSale());
                logger.info("PrivatBank API currency " + currency + " have purchase course is: " + TestData.COURSE_BUY_API + " and sale course is: " + TestData.COURSE_SALE_API);
            }
        }
    }
}
