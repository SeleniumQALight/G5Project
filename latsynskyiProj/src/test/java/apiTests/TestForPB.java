package apiTests;


import api.EndPointsPB;
import api.GetDTOPB;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestForPB {

    String ccy_EUR_RUR_USD = "3";

    @Test
    public  void getCurrencyPB (){
        Logger logger = Logger.getLogger(getClass());

        GetDTOPB[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPointsPB.GET_FOR_CURRENCY,ccy_EUR_RUR_USD)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(GetDTOPB[].class);

//        for (int i = 0; i < responseBody.length; i++) {
//            Assert.assertEquals("ccy1 incorrect  ","EUR",responseBody[0].getCcy());
//            Assert.assertEquals("base_ccy1 incorrect  ","UAH",responseBody[0].getBase_ccy());
//            Assert.assertEquals("ccy2 incorrect  ","RUR",responseBody[1].getCcy());
//            Assert.assertEquals("base_ccy2 incorrect  ","UAH",responseBody[1].getBase_ccy());
//            Assert.assertEquals("ccy3 incorrect  ","USD",responseBody[2].getCcy());
//            Assert.assertEquals("base_ccy3 incorrect  ","UAH",responseBody[2].getBase_ccy());
//            Assert.assertEquals("ccy4 incorrect  ","BTC",responseBody[3].getCcy());
//            Assert.assertEquals("base_ccy4 incorrect  ","USD",responseBody[3].getBase_ccy());
//        }
        GetDTOPB[] expectedResult = {
                new GetDTOPB("EUR","UAH"),
                new GetDTOPB("RUR","UAH"),
                new GetDTOPB("USD","UAH"),
                new GetDTOPB("BTC","USD")
        };

        Assert.assertEquals("Number of posts ",expectedResult.length,responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i],"buy","sale");

        }
        softAssertions.assertAll();

    }

}
