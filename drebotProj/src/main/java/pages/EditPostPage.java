package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    @FindBy(id = "post-title")
    private WebElement editTitle;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post" + ".+/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        //checkUrlWithPattern();
        checkUrlByRegEx();
        Assert.assertTrue("Edit post page wasn't loaded ", isElementDisplayed(editTitle));
        return this;
    }

    public EditPostPage enterNewTextEditTitle(String newTitle) {
        logger.info("OLD Title is '" + editTitle.getAttribute("value") + "'");
        enterTextIntoElement(editTitle, newTitle);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkTextInAlert(String alertText) {
        Assert.assertEquals("Text in alert ", alertText, alertSuccess.getText());
        return this;
    }
}
