package dbTest;

import libs.DB_Util_V2;
import libs.Database;
import libs.MySQL_Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DataBaseTest_table_seleniumUser {
    private Database mySglDB;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySglDB = MySQL_Database.getDataBase();
    }

    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
        List<Map<String, String>> dataFromSeleniumTable =
                mySglDB.selectTableAsMap("select login, passWord from seleniumUsers where login = 'newqaauto'");
        System.out.println(dataFromSeleniumTable);
       // System.out.println(dataFromSeleniumTable.get(1).get("login"));
        System.out.println(dataFromSeleniumTable.size());
        //int rows = mySglDB.changeTable("Insert Into seleniumTable VALUES(999777, 'G5_petrov', 'PaSSw0rd')");

        dataFromSeleniumTable = mySglDB.selectTableAsMap("select login, passWord from seleniumUsers where login = 'newqaauto'");
        System.out.println(dataFromSeleniumTable);
        System.out.println(dataFromSeleniumTable.size());


        DB_Util_V2 db_util_v2 = new DB_Util_V2();
        String Pass = db_util_v2.getPassForLogin("newqaauto");
        System.out.println(Pass);


    }

    @After
    public void tearDown() throws SQLException {
        mySglDB.quit();
    }
}
