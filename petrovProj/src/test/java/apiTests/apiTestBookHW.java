package apiTests;

import api.BookHW.ApiHelperBooks;
import api.BookHW.EndPointBook;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class apiTestBookHW {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();


    @Before
    public void deleteAllUsersBooks(){
       // apiHelperBooks.deleteBooksByUserId();
    }


    @Test
    public void AddBooks(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("userId", apiHelperBooks.login().getUserId());
        String isbn_id = apiHelperBooks.getAllBooksFromSite().getBooks()[0].getIsbn();
        String putBody = "collectionOfIsbns" + ": [{" + requestParams.put("isbn", isbn_id) + "}]";

        System.out.println("user "+ apiHelperBooks.login().getUserId());
        System.out.println("isbn " + isbn_id);

        //String addBooksBody =

        given()
                .auth().oauth2(apiHelperBooks.login().getToken())
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams.toMap())
                .when().post(EndPointBook.BOOKS)
                .then()
                .statusCode(200).log().all()
                .extract().response().getBody();

    }



}
