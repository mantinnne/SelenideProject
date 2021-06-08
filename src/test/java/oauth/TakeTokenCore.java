package oauth;

import Entities.model.CoreUser;
import config.ConfigHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TakeTokenCore {

    static CoreUser oauthCore = new CoreUser();


    public static String main() {
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(oauthCore.urlEncoded())
                .then().statusCode(200)
                .when().post(ConfigHelper.getBaseURLCore() + ConfigHelper.getTokenPathCore());
        String responseJson = response.jsonPath().getString("access_token");
        assertThat(responseJson, is(notNullValue()));
        return responseJson;
    }
}



