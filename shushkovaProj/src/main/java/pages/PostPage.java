package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{
    private HeaderElement headerElement=new HeaderElement(webDriver);
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check url
        Assert.assertTrue("Post Page is not loaded"
                ,isElementDisplayed(buttonEdit));
        return this;

    }
/** text це Expected result/alertSuccess.getText() це Actual result
 * assertEquals зрівнює їх
**/
    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert"
                ,text,alertSuccess.getText());
        return this;
    }

    public MyProfilePage ckickOnDeteleButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
