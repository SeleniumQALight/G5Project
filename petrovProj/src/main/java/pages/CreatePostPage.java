package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(tagName="select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement textAreaBodyContent;

    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement createPostButton;

    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement saveUpdatePostButton;

    @FindBy(xpath = ".//option[text()='Групове повідомлення']")
    private WebElement optionGroupMessage;

    @FindBy(xpath = ".//input[@name='uniquePost']")
    private WebElement check;

    @FindBy(xpath = ".//div[text()='Post successfully updated.']")
    private WebElement successGreenMessage;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        checkUrl();
        Assert.assertTrue("Page Create post is not loaded", isElementWasDisplayed(inputTitle));
        return  this;
    }

    public CreatePostPage enterTextInInputTitle(String title){
        enterTextIntoElement(inputTitle, title);
        return this;
    }


    public CreatePostPage enterTextInInputBodyContent(String bodyText){
        enterTextIntoElement(textAreaBodyContent, bodyText);
        return this;
    }


    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectOptionInDropDownByUI(){
        selectTextInDropDownByUI();
        return this;
    }

    public PostPage clickOnCreatePostButton(){
        clickOnElement(createPostButton);
        return new PostPage(webDriver);

    }

    public CreatePostPage actionsWithCheckBox(String checkCondition){
        selectedCheckBox(check, checkCondition);
        return this;
    }

    public CreatePostPage fillInputCreatePostForm(String title, String contentText, String checkBoxAction, String role){
        enterTextInInputTitle(title)
                .enterTextInInputBodyContent(contentText)
                .actionsWithCheckBox(checkBoxAction)
                .selectValueInDropDownRole(role);
        return this;
    }


}
