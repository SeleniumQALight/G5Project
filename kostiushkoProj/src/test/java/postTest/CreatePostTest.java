package postTest;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.CreatePostPage;

import java.util.concurrent.TimeUnit;

public class CreatePostTest extends BaseTest {


    @Test
    public void createNewPost (){

    homePage
            .openHomePage()
            .getHeaderElement().clickOnButtonCreatePost()
            .chekIsRedirectToCreatePostPage()
           .enterTextInInputTitle("Kostiushko")
//            .selecttextInDropDownRole("Приватне повідомлення")
//            .selectValueInDropDownRole("One Person")
            .selectTextInDropDownByUI("Групове повідомлення")
            .enterTextInTextAreaBodyContent("Text for body")
            .clikSaveNewPostButton()



            ;
    }


}


//    protected void selectTextInDropDownByUI (String text){
//        try {
//            webDriver.findElement(By.xpath(".//*[@value='All Users']")).click();
//            webDriver.findElement(By.xpath(".//*[contains(text(),'"+text+"')]")).click();
//            logger.info("'" + text + " ' was selected in DropDown");
//
//        }catch (Exception e){
//            prinErrorAndStopTest(e);
//        }
//    }
//
//    public CreatePostPage enterTextInTextAreaBodyContent(String text){
//        enterTextIntoElement(postBody, text);
//        return this;
//    }
//
//    public CreatePostPage clikSaveNewPostButton (String textForSelect) {
//        this.selectTextInDropDown(dropDownRole, textForSelect);
//        return this;
//    }