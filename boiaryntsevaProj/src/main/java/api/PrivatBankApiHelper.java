package api;

import io.restassured.http.ContentType;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;
import pages.PrivatBankLandingPage;

import static api.BankEndPoints.exchangeURL;
import static io.restassured.RestAssured.given;

public class PrivatBankApiHelper {
    Logger logger = Logger.getLogger(getClass());
    PrivatBankLandingPage privatBankLandingPage = new PrivatBankLandingPage(DriverHelper.getWebDriver());

    public CurrencyDTO[] getCurrencyExchangeList(String courseid) {
        CurrencyDTO[] response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("exchange")
                .queryParam("json")
                .queryParams("coursid", courseid)
                .when()
                .get(exchangeURL)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(CurrencyDTO[].class);
        return response;
    }

    public void getExchangeRateForCurrencyThroughApi(String courseId, String currency) {
        CurrencyDTO[] response = getCurrencyExchangeList(courseId);

        for (int i = 0; i < response.length; i++) {
            if (response[i].getCcy().equalsIgnoreCase(currency)) {
                TestData.setApiCurrencyBuy(Float.parseFloat(response[i].getBuy()));
                TestData.setApiCurrencySell(Float.parseFloat(response[i].getSale()));
                logger.info("Api exchange rate for " + currency +
                        " is Buy: " + TestData.getApiCurrencyBuy() + " and  Sell: " + TestData.getApiCurrencySell());
            }
        }
    }

}
