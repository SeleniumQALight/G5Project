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
    public void setUp() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();
    }

    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
        List<Map<String, String>> dataFromSeleniumTable =
                mysqlDB.selectTableAsMap("select login, passWord from seleniumTable where login = 'G5_iryna'");
        System.out.println(dataFromSeleniumTable);
//        System.out.println(dataFromSeleniumTable.get(1).get("login"));
        System.out.println(dataFromSeleniumTable.size());
//        int rows = mysqlDB.changeTable("Insert Into seleniumTable VALUES(352, 'G5_iryna', '123')");

        dataFromSeleniumTable =
                mysqlDB.selectTableAsMap("select login, passWord from seleniumTable where login = 'G5_taras'");
        System.out.println(dataFromSeleniumTable);
//        System.out.println("size = " + rows);

        DB_Util db_util = new DB_Util();
        System.out.println(db_util.getPassForLogin("G5_iryna"));
    }

    @After
    public void tearDown() throws SQLException {
        mysqlDB.quit();
    }

}
