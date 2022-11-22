package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElement;


abstract class ParentPage extends CommonActionsWithElements{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    public HeaderElement getHeaderElement() {
        return headerElement;
    }

        protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = configProperties.base_url_complex_app()
                .replace("[env]", System.getProperty("env", "qa"));

    }


    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("InValid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern(){
       // Assert.assertThat("Invalid Page"
        //        ,webDriver.getCurrentUrl()
        //        , containsString(baseUrl + getRelativeUrl())   );

        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\n ActualURL " + actualURL +  "\n"
                        + "ExpectedURL " +  baseUrl + getRelativeUrl() + "\n"
                , actualURL.matches(baseUrl + getRelativeUrl()));

    }



    protected void waitChatToBeHide(){
        webDriverWaitLow.withMessage("chat is not closed")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
}