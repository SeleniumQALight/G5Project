package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
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

    @Step
    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    @Step
    public EditPostPage checkRedirectToEditPage(){
        checkUrlWithPatterns();
        return this;
    }

    @Step
    public EditPostPage enterTextIntoTitleWithoutClearingInput(String title) {
        enterTextIntoPrefilledElement(inputTitle, title);
        return this;
    }

    @Step
    public EditPostPage saveEditedPost(){
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    @Step
    public EditPostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in alert", text, alertSuccess.getText());
        return this;
    }
}
