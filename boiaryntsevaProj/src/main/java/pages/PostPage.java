package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {


    private HeaderElement headerElement = new HeaderElement(webDriver);

    public PostPage(WebDriver webDriver) {

        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/.*";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;


    @FindBy(xpath = ".//div//div[4]//p")
    private WebElement textWeatherPostIsUnique;

    @Step
    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPatterns();
        Assert.assertTrue("Post page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    @Step
    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in alert", text, alertSuccess.getText());
        return this;
    }

    @Step
    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    @Step
    public EditPostPage clickOnEditButton(){
        clickOnElement(buttonEdit);
        return new EditPostPage(webDriver);
    }

    @Step
    public PostPage validateCheckBoxStateOnPost(String checkBoxValue){
        if ((!checkBoxValue.equalsIgnoreCase("check")) && (!checkBoxValue.equalsIgnoreCase("uncheck"))) {
            logger.info("Invalid value for checkbox was entered - Validation");
            Assert.fail("Invalid value for checkbox was entered - Validation");
        }
        else if (checkBoxValue.equalsIgnoreCase("check")){
            Assert.assertTrue(textWeatherPostIsUnique.getText().contains("yes"));
            logger.info("Checkbox is displayed as 'checked' on Post Page");
        } else if (checkBoxValue.equalsIgnoreCase("uncheck")) {
            Assert.assertTrue(textWeatherPostIsUnique.getText().contains("no"));
            logger.info("Checkbox is displayed as 'not checked' on Post Page");
        }

        return this;
    }
}
