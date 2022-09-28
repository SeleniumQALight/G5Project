package libs;


import org.apache.log4j.Logger;

import java.sql.SQLException;

public class Selenium_Users {
    private Database myDataBase;
    private Logger logger = Logger.getLogger(getClass());


public String getPasswordFromSeleniumUsers(String login) throws SQLException, ClassNotFoundException {
    myDataBase=MySQL_Database.getDataBase();
    String pass = myDataBase.selectValue(String.format(
            "select passWord from seleniumUsers where login = '%s'", login)
    );
    myDataBase.quit();
    return pass;
}
}