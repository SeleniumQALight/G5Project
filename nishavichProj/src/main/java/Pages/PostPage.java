package Pages;

import Pages.elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy (xpath =".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
    @FindBy (xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy (xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("Post page is not loaded"
                , isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert"
                , text, alertSuccess.getText());

        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement (buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
