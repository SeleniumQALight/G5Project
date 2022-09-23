package dataBaseTest;

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
    public void setUo() throws SQLException, ClassNotFoundException {
        mysqlDB = MySQL_Database.getDataBase();
    }

    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
       List<Map<String, String>> dataFromSeleniumTable =
               mysqlDB.selectTableAsMap("Select* from seleniumTable where login = 'Kostiushko_G5' ");
        System.out.println(dataFromSeleniumTable);
//        System.out.println(dataFromSeleniumTable.get(1).get("login"));
        System.out.println(dataFromSeleniumTable.size());
//        int rows = mysqlDB.changeTable("Insert Into seleniumTable VALUES(795, 'Kostiushko_G5', '12345')");
//        List<Map<String, String>> dataFromSeleniumTable2 =
        //               mysqlDB.selectTableAsMap("Select* from seleniumTable where login = 'Kostiushko_G5' ");
        //       System.out.println(dataFromSeleniumTable2);
        //       System.out.println(dataFromSeleniumTable2.size());

        DB_Util db_util= new DB_Util();
        System.out.println(db_util.getPassForLogin("Kostiushko_G5"));
    }

    @After
    public void tearDown() throws SQLException {
        mysqlDB.quit();
    }
}
