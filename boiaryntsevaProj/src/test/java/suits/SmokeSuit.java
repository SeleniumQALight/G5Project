package suits;

import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTest.CreatePostTest;
import postsTest.EditPostTest;
import registrationTest.RegistrationTest;
import registrationTest.RegistrationTestWithExcel;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        EditPostTest.class,
        RegistrationTest.class,
        RegistrationTestWithExcel.class
})
public class SmokeSuit {


}
