package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTestWithInValidParams;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreatePostTest;
import registrationTest.RegistrationTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        RegistrationTest.class
})
public class SmokeSuite {
}
