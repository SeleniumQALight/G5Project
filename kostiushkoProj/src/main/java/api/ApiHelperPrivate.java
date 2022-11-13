package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import libs.dataPrivate;
import org.apache.log4j.Logger;
import static io.restassured.RestAssured.given;



public class ApiHelperPrivate {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    public void getValuesViaAPI(String currency) {
        ExchangeDTO[] responseBody = given()
                .spec(requestSpecification)
                .queryParam("exchange")
                .queryParam("json")
                .queryParam("coursid",5)
                .when()
                .get(EndpointsPrivate.PUBINFO)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(ExchangeDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy().equalsIgnoreCase(currency)){
                dataPrivate.BUY_API = responseBody[i].getBuy();
                dataPrivate.SALE_API = responseBody[i].getSale();
                logger.info(currency + " API buy rate is " + dataPrivate.BUY_API +
                        " and sell rate is " + dataPrivate.SALE_API);
            }
        }
    }
}