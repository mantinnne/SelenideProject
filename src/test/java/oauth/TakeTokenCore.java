package oauth;

import config.ConfigHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TakeTokenCore {


    public static String main() {
        String jsonBody = "{\n" +
                "\t\"grant_type\": \"password\",\n" +
                "\t\"client_id\": 3,\n" +
                "\t\"client_secret\": \"aVmAt1RnYq8ceDnkPXFSYfxNtpNNgvFknObjdNfq\",\n" +
                "\t\"username\": \"tester@cism-ms.ru\",\n" +
                "\t\"password\": \"id7@cS2jA\"\n" +
                "}";
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonBody)
                .then().statusCode(200)
                .when().post(ConfigHelper.getBaseURLCore() + ConfigHelper.getTokenPathCore());
        String responseJson = response.jsonPath().getString("access_token");
        assertThat(responseJson, is(notNullValue()));
        return responseJson;
    }
}



