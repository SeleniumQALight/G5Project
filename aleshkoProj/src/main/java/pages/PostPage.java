package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    private final HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//h2")
    private WebElement titleTextField;
    @FindBy(xpath = ".//div[@class='body-content'][2]")
    private WebElement bodyTextField;
    @FindBy(xpath = ".//h2//..//a")
    private WebElement buttonEditPost;
    @FindBy(xpath = ".//h2//..//form")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkAlertAboutNewPostCreation(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public PostPage checkTitleAndBodyTextsAfterNewPostCreation(String titleText, String bodyText) {
        Assert.assertTrue("Post's title contains another text than entered while creation", isElementContainsText(titleTextField, titleText));
        Assert.assertTrue("Post's body contains another text than entered while creation", isElementContainsText(bodyTextField, bodyText));
        return this;
    }

    public PostPage checkRedirectToPostPage() {
        //TODO check URL
        waitChatToBeHide();
        Assert.assertTrue("Post page is not loaded", isElementDisplayed(buttonEditPost));
        return this;
    }
}
