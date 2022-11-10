package libs;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class TestData {
    public final static String VALID_LOGIN = "boiaryntseva";
    public final static String VALID_PASSWORD = "qwerty123456";
    public static float apiCurrencySell;
    public static float apiCurrencyBuy;
    public static float uiCurrencySell;
    public static float uiCurrencyBuy;
    public static boolean noCorrespondingCurrency = false;

    Logger logger = Logger.getLogger(getClass());

    public static float getApiCurrencySell() {
        return apiCurrencySell;
    }

    public static void setApiCurrencySell(float apiCurrencySell) {
        TestData.apiCurrencySell = apiCurrencySell;
    }

    public static float getApiCurrencyBuy() {
        return apiCurrencyBuy;
    }

    public static void setApiCurrencyBuy(float apiCurrencyBuy) {
        TestData.apiCurrencyBuy = apiCurrencyBuy;
    }

    public static float getUiCurrencySell() {
        return uiCurrencySell;
    }

    public static void setUiCurrencySell(float uiCurrencySell) {
        TestData.uiCurrencySell = uiCurrencySell;
    }

    public static float getUiCurrencyBuy() {
        return uiCurrencyBuy;
    }

    public static void setUiCurrencyBuy(float uiCurrencyBuy) {
        TestData.uiCurrencyBuy = uiCurrencyBuy;
    }
}
