package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Elements.HeaderElement;

public class PostPage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath =".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath =".//*[@class = 'alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath =".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("Post page is not Loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTestInAlert(String text) {
         Assert.assertEquals("Text in Alert",text,alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);

        return new MyProfilePage(webDriver);
    }
}
