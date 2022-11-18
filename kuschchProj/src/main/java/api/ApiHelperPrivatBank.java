package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build().queryParam("exchange").queryParam("json");

    public CurrencyExchangePrivatBankDTO[] getExchangeRatesAPI(int courseId) {
            return given()
                    .spec(requestSpecification)
                    .queryParam("coursid", courseId)
                .when()
                        .get(EndPointsPrivatBank.EXCHANGE)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(CurrencyExchangePrivatBankDTO[].class);
    }
}
