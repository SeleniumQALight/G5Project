package libs;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_UtilHW {

    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());

    public String getPasswordForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = mySQL_DataBase.selectValue(
                String.format("select passWord from seleniumUsers where login = '%s'", login)
        );
        mySQL_DataBase.quit();
        logger.info("--- Unconnected from DB ---");
        return pass;
    }
}
