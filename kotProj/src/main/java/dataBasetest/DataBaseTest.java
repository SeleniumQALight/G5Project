package dataBasetest;

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
        List<Map<String, String>> dataFromseleniumTable =
                mySqlDB.selectTableAsMap("select * from seleniumTable where login='G5_Kot' ");
        System.out.println(dataFromseleniumTable);
     //   System.out.println(dataFromseleniumTable.get(1).get("login"));
        System.out.println(dataFromseleniumTable.size());

// int rows = mySqlDB.changeTable("Insert into seleniumTable VALUES(10, 'G5_Kot', '0809')");
        dataFromseleniumTable =
                mySqlDB.selectTableAsMap("select * from seleniumTable where login='G5_Kot' ");
        System.out.println(dataFromseleniumTable);
  //     System.out.println("size = "+rows);

        DB_Util db_util = new DB_Util();
        System.out.println(db_util.getPassForLogin("G5_Kot"));

    }


    @After
    public void tearDown() throws SQLException {
        mySqlDB.quit();
    }
}