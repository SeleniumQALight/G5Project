package apiHomeworkAddUserBook;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static apiHomeworkAddUserBook.ApiHelper.*;
import static io.restassured.RestAssured.given;


public class AddUserBook {
    String firstBookId = "9781449325862";
    String userToken;

    @Before
    public void login() {
      loginAsValidUser();
      deleteUserBooks();

      UserDTO userDTO = ApiHelper.loginAsValidUser();
      userToken = userDTO.getToken();
    }

    @Test
    public void AddBookToUser(){
        String actualResult = "";
        addBook(getBookId(0));

        UserBooksDTO returnedUserBooks = given()
                .auth().oauth2(userToken)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.getUserBooks, userId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().as(UserBooksDTO.class);

        actualResult = returnedUserBooks.books.get(0).getIsbn();

        Assert.assertEquals("Searched bookId :" + firstBookId + " not wound" , firstBookId, actualResult);

    }
}
