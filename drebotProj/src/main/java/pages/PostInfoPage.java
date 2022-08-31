package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostInfoPage extends ParentPage {

    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//div[text()='New post successfully created.']")
    private WebElement messageSuccessfullyCreated;
    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']")
    private WebElement textTitle;
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    public PostInfoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostInfoPage checkIsRedirectToPostInfoPage() {
        //TODO check url
        Assert.assertTrue("PostInfoPage is not loaded", isElementDisplayed(buttonEdit));
        Assert.assertTrue("Page InfoPost was NOT loaded", isElementDisplayed(messageSuccessfullyCreated));
        return this;
    }

    public PostInfoPage checkTitleInPostInfoPage(String title) {
        Assert.assertTrue("Page InfoPost was NOT loaded", isElementContainText(textTitle, title));

        return this;
    }

    public PostInfoPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }
}
