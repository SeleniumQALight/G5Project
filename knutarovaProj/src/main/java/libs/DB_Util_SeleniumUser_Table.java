package libs;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_Util_SeleniumUser_Table {
    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());

    public String getPasswordForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String password = mySQL_DataBase.selectValue(
                String.format("select password from seleniumUsers where login = '%s'", login)
        );
        mySQL_DataBase.quit();
        return password;
    }
}
