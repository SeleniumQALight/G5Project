package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{

    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement  buttonDelete;

    @FindBy (xpath = ".//*[contains(text(),'Is this post unique?')]")
    private WebElement postUnique;



    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage (){
        checkUrlWithPattern();
        Assert.assertTrue( "Post Page is not loaded", isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage (webDriver);
    }



    public PostPage checkIsPostUnique(String state){
        String post = postUnique.getText();
        if(state.equalsIgnoreCase("check")){
            Assert.assertEquals("Post is unique","Is this post unique? : yes",post);
            logger.info("Post in unique");
        } else if (state.equalsIgnoreCase("uncheck")){
        Assert.assertEquals("Post is not unique","Is this post unique? : no",post);
            logger.info("Post in Not unique");
        } else {
            logger.info("Incorrect state of checkbox entered");
            Assert.fail("Incorrect state of checkbox entered");
        }
        return this;
    }

    public EditPostPage clickOnTheEditButton() {
        clickOnElement(editButton);
        return new EditPostPage(webDriver);
    }

}
