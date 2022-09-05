package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;
    @FindBy(xpath = ".//*[contains(text(),'Is this post unique?')]")
    private WebElement isUnique;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("Post page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkPostUniqueness() {
        if (isUnique.getText().contains("yes")){
            Assert.assertTrue("Post is not unique", isElementDisplayed(isUnique) & isUnique.getText().contains("yes"));
            logger.info("Post is unique");
        } else {
            Assert.assertTrue("Post is unique", isElementDisplayed(isUnique) & isUnique.getText().contains("no"));
            logger.info("Post is not unique");
        }
        return this;
    }
}
