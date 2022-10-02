package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPage extends ParentPage{
    @FindBy(xpath = ".//*[contains(@class, 'svg-inline--fa fa-edit fa-w-18')]")
    private WebElement buttonEditPost;
    @FindBy(name ="title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private  WebElement buttonSaveUpdates;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement alertSuccessEdit;

    public EditPage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/edit";
    }

    public EditPage checkToRedirectToEditPage(){
        checkUrlWithPattern();
        Assert.assertTrue("EditPage is not loaded",isElementDisplayed(buttonEditPost));
        return this;
    }

    public EditPage clickOnEditButton() {
        clickOnElement(buttonEditPost);
        return new EditPage(webDriver);

    }
        public EditPage enterTextInInputTitle(String title){
            enterTextIntoElement(inputTitle,title);
            return this;
    }


    public EditPage clickOnButtonSaveUpdates(){
        clickOnElement(buttonSaveUpdates);

        return new EditPage(webDriver);
    }

    public EditPage checkThatAlertOfSuccessEditIsDisplayed(){
        Assert.assertTrue("Post was not edit",isElementDisplayed(alertSuccessEdit));
        return this;
    }



}
