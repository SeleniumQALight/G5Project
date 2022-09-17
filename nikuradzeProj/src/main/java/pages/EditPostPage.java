package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement saveUpdatesButton;
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(xpath = ".//*[text()='Post successfully updated.']")
    private WebElement alertUpdated;
    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Page is not loaded", isElementDisplayed(saveUpdatesButton));
        return this;
    }

    public EditPostPage enterUpdatedTextInInputTitle(String updatedTitle) {
        enterTextIntoElement(inputTitle, updatedTitle);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdates() {
        clickOnElement(saveUpdatesButton);
        return this;
    }

    public EditPostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertUpdated.getText());
        return this;
    }
}
