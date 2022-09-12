package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{

    //.//*[@name = 'title']
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(name = "select1")
    private WebElement dropDownRole;

    @FindBy (xpath = ".//*[text() = 'Приватне повідомлення']")
    private WebElement textForSelector;

    @FindBy(name = "body")
    private WebElement inputBody;

    @FindBy (xpath = ".//button[text() = 'Save New Post']")
    private WebElement saveButton;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        //TODO check url
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String title){
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);

        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return  this;

    }

    public CreatePostPage selectTextInDropDownUI(){
        selectTextInDropDownUI(dropDownRole, textForSelector);
        return this;
    }

    public  CreatePostPage enterTextInInputBodyContent(String text){
        enterTextIntoElement(inputBody, text);
        return this;
    }

    public void clickTheSaveButton(){
        clickOnElement(saveButton);
    }


}
