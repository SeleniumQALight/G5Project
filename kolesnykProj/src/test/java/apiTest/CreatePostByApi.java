package apiTest;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class CreatePostByApi {
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deleteAllPosts(){
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByApi(){
        String token = apiHelper.getToken();
        //System.out.printf("token : "+ token);

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("title", "New post from Api");
        requestParams.put("body","post body");
        requestParams.put("select1", "One Person");
        requestParams.put("uniquePost", "yes");
        requestParams.put("token", token);

        String response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams)
        .when()
                .post(EndPoints.CREATE_POST)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().getBody().asString();

        Assert.assertEquals("Message", "\"Congrats.\"", response);

        PostDTO[] actualPostDTO = apiHelper.getAllPostsByUser();
        Assert.assertEquals("Number of posts ", 1,actualPostDTO.length);

        PostDTO[] expectedPostDTO = {
                PostDTO.builder()
                        .title(requestParams.get("title"))
                        .body(requestParams.get("body"))
                        .select1(requestParams.get("select1"))
                        .uniquePost(requestParams.get("uniquePost"))
                        .author(AuthorDTO.builder().username(ApiHelper.USER_NAME).build())
                        .isVisitorOwner(false)
                        .build()
        };

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostDTO[0]).isEqualToIgnoringGivenFields(expectedPostDTO[0], "id", "createdDate", "author");
        softAssertions.assertThat(actualPostDTO[0].getAuthor()).isEqualToIgnoringGivenFields(expectedPostDTO[0].getAuthor(), "avatar");
        softAssertions.assertAll();
    }
}
