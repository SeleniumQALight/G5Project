package suits;

import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import loginTest.RegistrationNewUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistrationNewUserTest.class,
        LoginTestWithPageObject.class,
        LoginTest.class
})
public class SmokeSuite {
}
