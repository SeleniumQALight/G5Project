package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement saveUpdatePostButton;
    @FindBy(xpath = ".//div[text()='Post successfully updated.']")
    private WebElement successGreenMessage;



    public EditPostPage checkIsRedirectToEditPostPage(){
        checkUrlWithPattern();
        Assert.assertTrue("Page Post is not loaded", isElementWasDisplayed(buttonEdit));
        return  this;
    }

    public EditPostPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return this;
    }

    public EditPostPage editEnterTextInInputTitle(String newTitle) {
        enterTextIntoElement(inputTitle, newTitle);
        return this;
    }

    public EditPostPage clickOnSaveUpdateButton() {
        clickOnElement(saveUpdatePostButton);
        return this;
    }

    public EditPostPage checkIsMessageSuccess() {
        isElementDisplayed(successGreenMessage);
        return this;
    }

}
