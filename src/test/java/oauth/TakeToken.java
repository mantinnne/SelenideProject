package oauth;

import config.ConfigHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TakeToken {


    public Map<String, String> main() {
        String jsonBody = "{\n" +
                "\t\"grant_type\": \"password\",\n" +
                "\t\"client_id\": 2,\n" +
                "\t\"client_secret\": \"0J4a1RP83KXeTTFrA96aWNYPHI6PPQOYyukzzKr6\",\n" +
                "\t\"username\": \"tester@cism-ms.ru\",\n" +
                "\t\"password\": \"id7@cS2jA\"\n" +
                "}";
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonBody)
                .when().log().body().post("http://social-api-stage.cism-ms.ru/oauth/token")
                .then().statusCode(200).extract().cookies();

    }
}



