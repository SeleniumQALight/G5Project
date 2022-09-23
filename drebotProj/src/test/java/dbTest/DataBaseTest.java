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
    public void testDataFromBase() throws SQLException, ClassNotFoundException {
        //List<Map<String,String>> dataFromSeleniumTable=mysqlDB.selectTableAsMap("Select * from seleniumTable");
        List<Map<String, String>> dataFromSeleniumTable = mysqlDB.selectTableAsMap("Select * from seleniumTable where login='G5_kate'");
        System.out.println(dataFromSeleniumTable);
        //System.out.println(dataFromSeleniumTable.get(1).get("login"));
        System.out.println(dataFromSeleniumTable.size());
        //int rows = mysqlDB.changeTable("Insert Into seleniumTable VALUES(12,'G5_kate','121212')");
        dataFromSeleniumTable = mysqlDB.selectTableAsMap("Select * from seleniumTable where login='G5_kate'");
        System.out.println(dataFromSeleniumTable);

        DB_Util db_util = new DB_Util();
        System.out.println(db_util.getPassForLogin("G5_kate"));

    }


    @After
    public void tearDown() throws SQLException {
        mysqlDB.quit();
    }
}
