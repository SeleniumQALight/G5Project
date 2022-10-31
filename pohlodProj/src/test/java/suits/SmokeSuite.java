package suits;

import apiTest.ApiTest;
import categories.SmokeTestFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import loginTest.LoginTestWithPageObject;
import postsTests.CreatePostTest;
import signUpTests.SignUpValidationMessages;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        SignUpValidationMessages.class,
        ApiTest.class
})
public class SmokeSuite {
}
