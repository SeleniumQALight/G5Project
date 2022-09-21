package Pages;

import Pages.elements.HeaderElements;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage{

    private HeaderElements headerElements = new HeaderElements(driver);
    @FindBy(xpath = "//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    WebElement buttonEdit;

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    WebElement alertSuccess;

    @FindBy(xpath = "//div[@class='body-content']/following-sibling::div[1]/p")
    private WebElement uniqueCheckboxResult;


    public PostPage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }




    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Post page is not opened",isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert",text ,alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(driver);
    }

    public CreatePostPage clickOnEditButton(){
        clickOnElement(buttonEdit);
        return new CreatePostPage(driver);
    }

    public PostPage checkIsPostMarkedUnique(){
        if (checkboxSelected){
            Assert.assertEquals("Is this post unique? : yes", uniqueCheckboxResult.getText());
        }else {
            Assert.assertEquals("Is this post unique? : no",uniqueCheckboxResult.getText());
        }
        return this;
    }
}
