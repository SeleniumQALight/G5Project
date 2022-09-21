package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage {
    private final HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//*[text()='Post successfully updated.']")
    private WebElement alertUpdate;
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement buttonSaveChanges;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Page does not loaded", isElementDisplayed(buttonSaveChanges));
        return this;
    }

    public EditPostPage inputNewTextIntoTitleField(String updatedTitle) {
        inputTitle.clear();
        enterTextIntoElement(inputTitle, updatedTitle);
        return this;
    }

    public EditPostPage clickOnButtonSaveChanges() {
        clickOnElement(buttonSaveChanges);
        return this;
    }

    public EditPostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert ", text, alertUpdate.getText());
        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
