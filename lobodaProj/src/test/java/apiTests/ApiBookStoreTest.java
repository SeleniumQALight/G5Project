package apiTests;

import api.BookApiHelper;
import api.BookEndPoints;
import api.BookListByUserDTO;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiBookStoreTest {
    BookApiHelper bookApiHelper = new BookApiHelper();
    String userId;
    String token;

    @Before
    public void loginAndDeleteAllBooksByUserId(){
        JSONObject userIdAndToken = bookApiHelper.getUserIdAndToken();
        userId = userIdAndToken.get("userId").toString();
        token = userIdAndToken.get("token").toString();
        bookApiHelper.deleteAllBooksByUserId(token,userId);
    }

    @Test
    public void addBookToProfile(){
        String isbn = bookApiHelper.getIsbnForFirstBookInStore();

        JSONObject bodyMainParams = new JSONObject();
        bodyMainParams.put("userId", userId);

        JSONObject bodyIsbnParams = new JSONObject();
        bodyIsbnParams.put("isbn", isbn);

        List<JSONObject> listOfIsbns = new ArrayList<>();
        listOfIsbns.add(bodyIsbnParams);
        bodyMainParams.put("collectionOfIsbns", listOfIsbns);

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth()
                .oauth2(token)
                .body(bodyMainParams.toMap())
        .when()
                .post(BookEndPoints.WORK_WITH_BOOKS)
        .then()
                .statusCode(201)
                .log().all();


        BookListByUserDTO userBookList = bookApiHelper.getAllBooksByUserId(userId,token);

        Assert.assertEquals("Number of books ", 1,userBookList.getBooks().length);
        Assert.assertEquals("Isbn of book ", isbn,userBookList.getBooks()[0].getIsbn());

    }


}
