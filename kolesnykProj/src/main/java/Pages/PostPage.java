package Pages;

import Pages.elements.HeaderElements;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage{

    private HeaderElements headerElements = new HeaderElements(driver);
    public PostPage(WebDriver driver) {
        super(driver);
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    WebElement buttonEdit;

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    WebElement alertSuccess;


    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("Post page is not opened",isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert",text ,alertSuccess.getText());
        return this;
    }
}
