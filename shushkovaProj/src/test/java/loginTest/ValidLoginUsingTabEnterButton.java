package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ValidLoginUsingTabEnterButton extends BaseTest {
    @Test
    public void validLoginUsingTabEnterButton(){
        /** логінимся валідними кредами**/

        loginPage.openLoginPage();
        loginPage.enterUserNameIntoLoginInput("qaauto");
        loginPage.usersPressesKeyTabTime(1);
        loginPage.enterPasswordIntoLoginInput("123456qwerty");
        loginPage.usersPressesKeyEnterTime(1);
        Assert.assertTrue("Button SinOut is not Displayed"
                , homePage.getHeaderElement().isButtonSingOutDisplayed());

        /** відкриваємо нову табу залогінені**/

        loginPage.userOpensNewTab();
        loginPage.openLoginPage();

        /** перевіряємо чи ми залогінені**/
        Assert.assertTrue("Button SinOut is not Displayed"
                , homePage.getHeaderElement().isButtonSingOutDisplayed());

        /** вилогінюємося**/

        homePage.getHeaderElement().clickOnButtonSingOut();
        /** відкриваємо нову табу вилогінені, відкриваємо нову табу вилогінені,Ф5, перевіряємо що ми вилогінені в табі**/

        loginPage.userOpensNewTab();
        loginPage.openLoginPage();
        loginPage.usersPressesKeyF5(1);
        Assert.assertTrue("Button SinIn is not Displayed"
                , loginPage.isButtonSingInDisplayed());

    }
}
