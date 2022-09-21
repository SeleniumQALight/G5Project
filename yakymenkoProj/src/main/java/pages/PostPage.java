package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    private final HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;
    @FindBy(xpath = ".//div[contains(@class,'content')]/h2")
    private WebElement titleContent;
    @FindBy(xpath = ".//div[@class='body-content'][2]")
    private WebElement bodyContent;
    @FindBy(xpath = ".//p[contains(text(),'Is this post unique?')]")
    private WebElement textPostIsUnique;

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

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        waitChatToBeHide();
        Assert.assertTrue("Post page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);// викликаємо
        return new MyProfilePage(webDriver);
    }

    public PostPage checkPostContentAfterCreatedNewPost(String titleText, String bodyText, String postIsUnique) {
        Assert.assertTrue("The post title is different from already created", isElementContainsText(titleContent, titleText));
        Assert.assertTrue("The post body is different from already created", isElementContainsText(bodyContent, bodyText));
        Assert.assertTrue("The post unique condition has not expected condition", isPostUniqueCondition(postIsUnique));
        return this;
    }

    private boolean isPostUniqueCondition(String postIsUnique) {
        if (postIsUnique.equalsIgnoreCase("yes") || postIsUnique.equalsIgnoreCase("no")) {
            if (postIsUnique.equalsIgnoreCase("yes") && textPostIsUnique.getText().equals("Is this post unique? : yes")) {
                return true;
            } else if (postIsUnique.equalsIgnoreCase("no") && textPostIsUnique.getText().equals("Is this post unique? : no")) {
                return true;
            } else {
                return false;
            }
        } else {
            Assert.fail("CheckBox value for unique condition is not valid");
            return false;
        }
    }
}
