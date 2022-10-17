package apiTestPrivatBank;

import apiPrivatBank.CurrencyDTO;
import apiPrivatBank.EndPointPB;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTestPrivatBank {


    @Test
    public void getCurrency(){

       CurrencyDTO[] responseCurrencyBody =  given()
                .contentType(ContentType.JSON)
                .queryParam("exchange").queryParam("json").queryParam("coursid", 11)
                .log().all()
                .when()
                .get(EndPointPB.baseUrl)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        CurrencyDTO[] expectedCurrencyResult = {
                new CurrencyDTO("USD", "UAH"),
                new CurrencyDTO("EUR", "UAH"),
                new CurrencyDTO("RUR", "UAH"),
                new CurrencyDTO("BTC", "USD")
        };

        Assert.assertEquals("Returned number of currency is different :", expectedCurrencyResult.length, responseCurrencyBody.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedCurrencyResult.length; i++){
            softAssertions.assertThat(responseCurrencyBody[i])
                            .isEqualToIgnoringGivenFields(expectedCurrencyResult[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }




}
