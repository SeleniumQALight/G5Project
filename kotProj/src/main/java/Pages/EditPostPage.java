package Pages;

import Pages.Elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {

    private final HeaderElement headerElement = new HeaderElement(webDriver);
    public HeaderElement getHeaderElement() {return headerElement;}

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement editTitle;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement saveUpdatesButton;

    @FindBy(xpath = ".//*[text()='Post successfully updated.']")
    private WebElement updateAlert;

    public EditPostPage(WebDriver webDriver) {super(webDriver);}


    @Override
    String getRelatedUrl() {return ("/post/[a-zA-Z0-9]*/edit");}

    public EditPostPage checkIsRedirectedToEditPostPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Edit Post Page is not loaded", isElementDisplayed(saveUpdatesButton));
        return this;
    }

    public EditPostPage clickOnTitleInput(String new_title) {
        enterTextIntoElement(editTitle, new_title);
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(saveUpdatesButton);
        return this;
    }

    public EditPostPage updateSuccess(String text) {
        Assert.assertEquals("Post successfully updated.", text, updateAlert.getText());
        return this;
    }

}
