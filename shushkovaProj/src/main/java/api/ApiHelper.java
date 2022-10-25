package api;

import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public final String USER_NAME="shushkovaira13";
    public final String PASSWORD="shushkovaira13";

    public String getToken() {
        return getToken(USER_NAME,PASSWORD);
    }

    private String getToken(String user_name, String password) {
        /**створюємо JSON для реквеста**/
        JSONObject requestParams=new JSONObject();
        requestParams.put("username",user_name);
        requestParams.put("password",password);

        /**створюємо змінну для респонса**/
        ResponseBody responseBody=
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(requestParams.toMap())
                             .when()
                        .post(Endpoints.LOGIN)
                                   .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        return responseBody.asString().replace("\"","");
    }
}
