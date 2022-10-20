package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public  final String USER_NAME = "petrov";
    private final String PASSWORD = "qwerty123456";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * get token for default user
     * @return
     */
    public String getToken() {
        return getToken(USER_NAME, PASSWORD);
    }

    private String getToken(String user_name, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user_name);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                .when()
                        .post(EndPoints.LOGIN)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        return responseBody.asString().replace("\"", "");


    }

    /**
     * get posts for default user
     * @return
     */
    public PostDTO[] getAllPostsByUser() {
        return getAllPostsByUser(USER_NAME);
    }

    private PostDTO[] getAllPostsByUser(String user_name) {
        return given()
                .spec(requestSpecification)
              .when()
                .get(EndPoints.POST_BY_USER, user_name)
              .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(PostDTO[].class);

    }

}
