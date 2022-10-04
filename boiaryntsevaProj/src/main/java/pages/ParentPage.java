package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

import io.qameta.allure.Step;

abstract class ParentPage extends CommonActionsWithElements {
    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = configProperties.base_url()
                .replace("[env]", System.getProperty("env", "qa"));
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid url"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    @Step
    protected void checkUrlWithPatterns(){
        logger.debug(webDriver.getCurrentUrl());
//        Assert.assertThat("Invalid page"
//                , webDriver.getCurrentUrl()
//                , containsString(baseUrl + getRelativeUrl())  ); //asset that priymae metodi
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\n ActualURL " + actualURL +  "\n "
                        + "ExpectedURL pattern" +  baseUrl + getRelativeUrl() + " \n "
                , actualURL.matches(baseUrl + getRelativeUrl()));

    }

    protected void waitChatToBeHidden() {
        webDriverWaitLow.withMessage("Chatbis not closed")
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
}
