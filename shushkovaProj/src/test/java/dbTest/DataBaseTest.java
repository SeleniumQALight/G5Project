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
    private Database mySqlDatabase;
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySqlDatabase= MySQL_Database.getDataBase();

    }

    @Test
    public void testDataFromDatabase() throws SQLException, ClassNotFoundException {
        List<Map<String,String>>dataFromSeleniumTable =
                mySqlDatabase.selectTableAsMap("select login,password from seleniumTable where login='Ira'");
        System.out.println(dataFromSeleniumTable);
 //       System.out.println(dataFromSeleniumTable.get(1).get("login"));
        System.out.println(dataFromSeleniumTable.size());
        int rows=mySqlDatabase.changeTable("Insert INTO seleniumTable VALUE(999,'IRA','irapass')");

        dataFromSeleniumTable=
                mySqlDatabase.selectTableAsMap("select login,password from seleniumTable where login='Ira'");
        System.out.println(dataFromSeleniumTable);

        DB_Util db_util=new DB_Util();
        System.out.println(db_util.getPassForLogin("IRA"));

    }



    @After
    public void tearDown() throws SQLException {
        mySqlDatabase.quit();

    }
}
