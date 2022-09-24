package dbTest;

import libs.DB_Util;
import libs.Database;
import libs.MySQL_Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DataBaseTest {
    private Database mysqlDB;

    @Before
    public void openDBConnection() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();
    }

    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
        List<Map<String,String>> dataFromSeleniumTable =
                mysqlDB.selectTableAsMap("SELECT * FROM seleniumTable WHERE idNumber = 666");
        System.out.println(dataFromSeleniumTable);
        System.out.println(dataFromSeleniumTable.size());

        DB_Util db_util = new DB_Util();
        System.out.println(db_util.getPassForLogin("G5_Apache"));
    }

    @Test
    public void insertRowDB() throws SQLException {
        int effectedRows = mysqlDB.changeTable("INSERT INTO seleniumTable VALUES(666, 'G5_Apache', 'fox')");
        System.out.println("Number of effected rows: " + effectedRows);
    }

    @Test
    public void deleteRowDB() throws SQLException {
        int effectedRows = mysqlDB.changeTable("DELETE FROM seleniumTable WHERE idNumber = 666");
        System.out.println("Number of effected rows: " + effectedRows);
    }

    @After
    public void closeDBConnection() throws SQLException {
        mysqlDB.quit();
    }
}
