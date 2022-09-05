package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostInfoPage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']")
    private WebElement textTitle;
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;
    @FindBy(xpath = ".//p[contains(text(),'Is this post unique? : ')]")
    private WebElement checkBoxText;

    public PostInfoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostInfoPage checkIsRedirectToPostInfoPage() {
        //TODO check url
        Assert.assertTrue("PostInfoPage is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostInfoPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public PostInfoPage checkTextInCheckBox(String check) {
        if (check == TestData.CHECK) {
            Assert.assertEquals("CheckBox doesn't work", "Is this post unique? : yes", checkBoxText.getText());//webDriver.findElement(By.xpath(String.format(checkBoxLocator, "yes"))).getText());
        } else if (check == TestData.UNCHECK) {
            Assert.assertEquals("CheckBox doesn't work", "Is this post unique? : no", checkBoxText.getText());//webDriver.findElement(By.xpath(String.format(checkBoxLocator, "no"))).getText());
        } else {
            Assert.fail("CheckBox doesn't work");
        }

        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
