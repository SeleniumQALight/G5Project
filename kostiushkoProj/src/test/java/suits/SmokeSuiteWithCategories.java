package suits;

import catagories.SmokeTestFilter;
import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import loginTest.RegistrationNewUserTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        RegistrationNewUserTest.class,
        LoginTestWithPageObject.class,


})
public class SmokeSuiteWithCategories {
}
