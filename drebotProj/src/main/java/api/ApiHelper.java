package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import io.restassured.response.ResponseBody;

import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static final String USER_NAME = "katedr";
    private final String PASSWORD = "123456qwerty";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    /**
     * get token for default user
     *
     * @return
     */
    public String getToken() {
        return getToken(USER_NAME, PASSWORD);
    }

    public String getToken(String userName, String password) {

        JSONObject requestParam = new JSONObject();
        requestParam.put("username", userName);
        requestParam.put("password", password);

        ResponseBody responseBody =
                given()
//                        .contentType(ContentType.JSON)
//                        .log().all()
                        .spec(requestSpecification)
                        .body(requestParam.toMap())

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

    public PostDTO[] getAllPostsByUser(String userName) {
        return given()
                .spec(requestSpecification)

                .when()
                .get(EndPoints.POST_BY_USER,userName)

                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(PostDTO[].class);
    }
}
