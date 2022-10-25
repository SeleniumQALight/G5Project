package apiTests;

import api.ApiHelper;
import api.Endpoints;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    /**створюємо обєкт ApiHelper**/
    ApiHelper apiHelper=new ApiHelper();
    @Test
    public void createPostByApi(){
        /**обявляємо змінну для отримання токену**/
        String token=apiHelper.getToken();
        HashMap<String,String> requestParams=new HashMap<>();
        requestParams.put("title", "New post from Api");
        requestParams.put("body","post body");
        requestParams.put("select1", "One Person");
        requestParams.put("uniquePost", "yes");
        requestParams.put("token", token);

        String response=
                given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams)
                .when()
                .post(Endpoints.CREATE_POST)
                .then()
                .statusCode(200)
                        /**
                         * отримуємо стрінгу з боді респонса*/
                .extract().response().getBody().asString();
        Assert.assertEquals("Message","\"Congrats.\"",response);
    }
}
