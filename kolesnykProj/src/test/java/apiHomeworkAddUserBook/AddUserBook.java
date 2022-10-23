package apiHomeworkAddUserBook;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static apiHomeworkAddUserBook.ApiHelper.*;
import static io.restassured.RestAssured.given;


public class AddUserBook {

    @Before
    public void login() {
      loginAsValidUser();
      deleteUserBooks();
    }

    @Test
    public void AddBookToUser(){
        String actualResult = "";

        getFirstBookId();
        addBook();

        JsonPath response = given()
                .auth().oauth2(userToken)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.getUserBooks, userId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().jsonPath();

        List<HashMap<String, Object>> books = response.getList("books");
        for (int i = 0; i < books.size(); i++) {
            actualResult = (String) books.get(0).get("isbn");
        }

        Assert.assertEquals("Searched bookId :" + firstBookId + " not wound" , firstBookId, actualResult);

    }
}
