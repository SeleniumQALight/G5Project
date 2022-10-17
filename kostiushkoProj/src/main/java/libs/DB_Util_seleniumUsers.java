package libs;


import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_Util_seleniumUsers {
    private Database mySQL_DB;
    Logger logger = Logger.getLogger(getClass());

    public String getPassword(String login) throws SQLException, ClassNotFoundException {
        mySQL_DB = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB ---");

        String pass = mySQL_DB.selectValue(String.format("select passWord from seleniumUsers where login = '%s'", login));
        mySQL_DB.quit();
        return pass;
    }
}