package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement AlertMessageSuccessfully;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;






    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage(){
        //TODO check url
        Assert.assertTrue("Page Post is not loaded", isElementWasDisplayed(buttonEdit));
        return  this;
    }

    public PostPage checkTestIsAlert(String text) {
        Assert.assertEquals("Text in Alert", text, AlertMessageSuccessfully.getText());
        return this;
    }

    protected MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkIsAppliedCheckBox(String checkMessage) {
        Assert.assertTrue("Check value is not applied", isElementDisplayed(webDriver.findElement(By.xpath(checkMessage))));
        return this;
    }
}
