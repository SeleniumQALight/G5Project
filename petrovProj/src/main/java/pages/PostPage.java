package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement AlertMessageSuccessfully;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage(){
        //TODO check url
        Assert.assertTrue("Page Post is not loaded", isElementWasDisplayed(buttonEdit));
        return  this;
    }

    public PostPage checkTestIsAlert(String text) {
        Assert.assertEquals("Text in Alert", text, AlertMessageSuccessfully.getText());
        return this;
    }
}
