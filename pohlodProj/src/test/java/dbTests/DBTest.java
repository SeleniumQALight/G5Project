package dbTests;

import libs.DB_Util;
import libs.Database;
import libs.MySQL_Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBTest {

    private Database mysqlDb;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mysqlDb = MySQL_Database.getDataBase();
    }


    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
        List<Map<String, String>> dataFromSeleniumTable = mysqlDb.selectTableAsMap("select login,passWord from seleniumTable where login = 'G5_pohlod'");
        System.out.println(dataFromSeleniumTable);
     //   System.out.println(dataFromSeleniumTable.get(1).get("login"));
        System.out.println(dataFromSeleniumTable.size());
     //   int rows = mysqlDb.changeTable("Insert Into seleniumTable VALUES(777,'G5_pohlod','777')");

        dataFromSeleniumTable = mysqlDb.selectTableAsMap("select login,passWord from seleniumTable where login = 'G5_pohlod'");
        System.out.println(dataFromSeleniumTable);
      //  System.out.println("size = " + rows);

        DB_Util db_util = new DB_Util();
        System.out.println(db_util.getPassForLogin("G5_pohlod"));

    }

    @After
    public void tearDown() throws SQLException {
        mysqlDb.quit();
    }

}
