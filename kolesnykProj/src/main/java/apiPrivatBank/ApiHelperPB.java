package apiPrivatBank;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiHelperPB {

    public static String currencyApi;

    private static CurrencyDTO[] responseCurrencyBody;

    Logger logger = Logger.getLogger(getClass());

    public void getCurrency(){
        responseCurrencyBody =  given()
                .contentType(ContentType.JSON)
                .queryParam("exchange").queryParam("json").queryParam("coursid", 5)
                .log().all()
                .when()
                .get(EndPointPB.baseUrl)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);
    }

    public void storeCurrencyValue(String currency){
        for (int i = 0; i < responseCurrencyBody.length; i++) {
            if (responseCurrencyBody[i].ccy.equals(currency)){
                currencyApi = responseCurrencyBody[i].getBuy();
                logger.info("Currency value from API : "+currencyApi);

            }
        }
    }
}
