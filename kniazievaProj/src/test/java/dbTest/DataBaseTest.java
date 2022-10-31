package dbTest;

import libs.DB_Util;
import libs.Database;
import libs.MySQL_Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
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
        ArrayList<Map<String, String>> dataFromSeleniumTable =
                mysqlDB.selectTableAsMap("select login, password from seleniumTable where login ='G5_kniazieva'");
        System.out.println(dataFromSeleniumTable);
        //System.out.println(dataFromSeleniumTable.get(1).get("login"));
        System.out.println(dataFromSeleniumTable.size());
        //int rows = mysqlDB.changeTable("Insert Into seleniumTable VALUES(285, 'G5_kniazieva', '285')");
        dataFromSeleniumTable =
                mysqlDB.selectTableAsMap("select login, password from seleniumTable where login ='G5_kniazieva'");
        System.out.println(dataFromSeleniumTable);
        //System.out.println("size = "+ rows);

        DB_Util db_util = new DB_Util();
        System.out.println(db_util.getPassForLogin("G5_kniazieva"));

    }
    @After
    public void tesrDown(){

    }
}
