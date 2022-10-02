package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.awt.*;


public class PostPage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy (xpath = ".//*[@class='alert alert-success text-center' and contains(text(), 'Post successfully updated.')]")
    private WebElement successfulMessage;
    @FindBy(xpath = ".//* [@class = 'btn btn-primary']")
    private WebElement buttonSaveUpdatesPost;
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;
    @FindBy(xpath = ".//* [contains(text(), 'Is this post unique?')]")
    private WebElement textCheckBox;
    @FindBy(xpath = ".//* [@class = 'svg-inline--fa fa-edit fa-w-18']")
    private WebElement editButton;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelateUrl() {
        return "/post/";
    }

    public PostPage chekIsRedirectToPostPage() {
        checkUrlwithPattern();
        Assert.assertTrue("post Page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }






    public PostPage checkTextInAllert(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public PostPage checkTextInPostUnique(String text) {
        if (text.equalsIgnoreCase("check")){
            Assert.assertEquals("Text does not match", "Is this post unique? : yes", textCheckBox.getText());
        } else if (text.equalsIgnoreCase("uncheck")) {
            Assert.assertEquals("Text does not match", "Is this post unique? : no", textCheckBox.getText());
        }

        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clicOnElement(buttonDelete);
        return new MyProfilePage(webDriver);}

    public HeaderElement getHeaderElement() {
        return headerElement;
    }


    public PostPage clickOnEditButton() {
        clicOnElement(editButton);
    return this;
    }

    public PostPage checkIsRedirectToOldPostPage() {
        checkUrlwithPattern();
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public PostPage enterNewTextInInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }
    public PostPage clikSaveUpdatesPostButton() {
        clicOnElement(buttonSaveUpdatesPost);
        return this;
    }
    public PostPage checkSuccessfulMessage() {
        Assert.assertTrue("Text not display", isElementDisplayed(successfulMessage));
        logger.info("Successful Message display");
        return this;
    }

}
