package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class QAToolsApiHelper {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public void deleteAllBooksInUserList(String uuid, String token) {
        given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .param("UserId", uuid)
        .when()
                .delete(BooksEndPoints.booksUrl)
        .then()
                .statusCode(204)
                .log().all();

        QAToolsUsersBooksDTO responseBody = getUsersListOfBooks(uuid, token);

        Assert.assertTrue("Number of books in list is not 0", (responseBody.books.length == 0));
        logger.info("List of books was cleared");
    }

    public void deleteBookInUserListByIsbn(String uuid, String token, String isbn) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("isbn", isbn);
        requestBody.put("userId", uuid);

        given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .body(requestBody.toMap())
        .when()
                .delete(BooksEndPoints.deleteBookFromUserList)
        .then()
                .statusCode(204)
                .log().all();
    }

    public QAToolsUsersBooksDTO getUsersListOfBooks(String uuid, String token) {
        return given()
                    .spec(requestSpecification)
                    .auth().oauth2(token)
               .when()
                    .get(BooksEndPoints.getAllBooksByUser, uuid)
               .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response().getBody().as(QAToolsUsersBooksDTO.class);
    }

    public QAToolsBooksListDTO getAllBooksInStore() {
      return given()
                    .spec(requestSpecification)
             .when()
                    .get(BooksEndPoints.booksUrl)
             .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response().getBody().as(QAToolsBooksListDTO.class);
    }

    public void addBooksToUserList(String uuid, String token, String isbn) {
        HashMap<String,String> listOfIsbns = new HashMap<>();
        listOfIsbns.put("isbn", isbn);

        HashMap[] collectionOfIsbns = {listOfIsbns};

        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", uuid);
        requestBody.put("collectionOfIsbns", collectionOfIsbns);

        String responseBody =
                given()
                        .spec(requestSpecification)
                        .auth().oauth2(token)
                        .body(requestBody.toMap())
                .when()
                        .post(BooksEndPoints.booksUrl)
                .then()
                        .log().all()
                        .statusCode(201)
                        .extract().response().getBody().asString();

        logger.info(responseBody);

        Assert.assertTrue("Book was not added to list", responseBody.contains(isbn));
        logger.info(String.format("Book with isbn [%s] was added to user's list", isbn));
    }

    public ArrayList<String> isbnParser(QAToolsBooksDTO[] bookList) {
        ArrayList<String> listOfIsbn = new ArrayList<>();

        for (int i = 0; i < bookList.length; i++) {
            listOfIsbn.add(bookList[i].getIsbn());
        }
        return listOfIsbn;
    }
}
