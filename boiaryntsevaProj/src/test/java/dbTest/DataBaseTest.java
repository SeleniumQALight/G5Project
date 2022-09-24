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
    private Database mySqlDB;

        @Before
        public void setUp() throws SQLException, ClassNotFoundException {
            mySqlDB = MySQL_Database.getDataBase();
        }
        @Test
        public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
            List<Map<String, String>> dataFromSeleniumTable =
                    mySqlDB.selectTableAsMap("select * from seleniumTable");
            System.out.println(dataFromSeleniumTable);
            int rows = mySqlDB.changeTable("Insert Into seleniumTable VALUES(951,'G5_kate','123')");
            dataFromSeleniumTable =
                    mySqlDB.selectTableAsMap("select * from seleniumTable where login='G5_kate'");
            //System.out.println(dataFromSeleniumTable.get(1).get("login"));
            System.out.println(dataFromSeleniumTable);

            DB_Util db_util = new DB_Util();
            System.out.println(db_util.getPassForLogin("G5_kate"));
        }
        @After
        public void tearDown() throws SQLException {
            mySqlDB.quit();

        }

}
