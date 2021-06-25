package oauth;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TakeTokenCore {



    public static String main() {
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body("token")
                .then().statusCode(HTTP_OK)
                .when().post(/*URL*/);

        // OR USE MODEL RESPONSE!
        String responseJson = response.jsonPath().getString("access_token");
        assertThat(responseJson, is(notNullValue()));
        return responseJson;
    }
}



