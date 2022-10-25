package apiHomeworkAddUserBook;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private static final String USER_NAME = "rossTest";
    private static final String PASSWORD = "12345Love!";

    public static String userId ;
    private static String userToken;

    Logger logger = Logger.getLogger(getClass());


    public static UserDTO loginAsValidUser() {
         return loginAsValidUser(USER_NAME, PASSWORD);

    }

    static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static UserDTO loginAsValidUser(String user_name, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", user_name);
        requestParams.put("password", password);

        UserDTO responseBody =
                given()
                        .spec(requestSpecification)
//                        .contentType(ContentType.JSON)
//                        .log().all()
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPoints.loginUrl)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().response().getBody().as(UserDTO.class);


        userId = responseBody.getUserId();
        userToken = responseBody.getToken();
        return responseBody;
    }

    public static void deleteUserBooks(){
        deleteUserBooks(userId, userToken );
    }

    private static void deleteUserBooks(String userId, String userToken) {

        given().auth()
                .oauth2(userToken)
                .queryParam("UserId",userId)
                .log().all()
                .when()
                .delete(EndPoints.deleteUserBook)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .log().all();

    }

    public static String getBookId(int bookIndex){
         JsonPath response = given()
                .log().all()
                .when()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().jsonPath();

    //have no idea how it works
        List<HashMap<String, Object>> books = response.getList("books");
        String firstBookId = (String) books.get(bookIndex).get("isbn");
        return firstBookId;
    }

    public static void addBook(String bookId) {
        ArrayList<Map<String, String>> collectionOfIsbns = new ArrayList<>();
        Map<String ,String > isbn = new HashMap<>();
        isbn.put("isbn", bookId);
        collectionOfIsbns.add(isbn);

        HashMap<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("userId", userId);
        bodyParams.put("collectionOfIsbns", collectionOfIsbns);
        given()
                .auth().oauth2(userToken)
                .contentType(ContentType.JSON)
                .log().all()
                .body(bodyParams)
//                .body("{\n" +
//                        "    \"userId\": \"522ec53d-cbcd-4272-b43f-3be0c54db4fc\",\n" +
//                        "    \"collectionOfIsbns\": [\n" +
//                        "        {\n" +
//                        "            \"isbn\":" + "\"" + firstBookId + "\"\n"+
//                        "        }\n" +
//                        "    ]\n" +
//                        "}")
                .when()
                .post("https://demoqa.com/BookStore/v1/Books")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                ;

    }
}
