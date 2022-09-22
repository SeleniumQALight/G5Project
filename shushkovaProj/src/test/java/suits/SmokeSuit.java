package suits;

import loginTest.LoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        CreatePostTest.class

})

public class SmokeSuit {
}
