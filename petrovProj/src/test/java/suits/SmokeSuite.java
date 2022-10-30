package suits;

import apiTests.ApiTests;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreatePostTest;
import registrationTest.RegistrationTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        RegistrationTest.class,
        ApiTests.class
})
public class SmokeSuite {
}
