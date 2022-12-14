package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage{
    @FindBy (xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//body/div//button")
    private WebElement buttonSaveUpdates;

    @Override
    String getRelativeUrl() {
        //return "/post/"+"[a-zA-Z0-9]*"+"/edit";
        return "/post/"+".*"+"/edit";
    }

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public EditPostPage checkRedirectToEditPage(){
        checkUrlWithPatterns();
        return this;
    }

    public EditPostPage enterTextIntoTitleWithoutClearingInput(String title) {
        enterTextIntoPrefilledElement(inputTitle, title);
        return this;
    }

    public EditPostPage saveEditedPost(){
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in alert", text, alertSuccess.getText());
        return this;
    }
}
