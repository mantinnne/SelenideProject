package config.oauth;

import io.restassured.http.ContentType;

import java.util.Map;

import static io.restassured.RestAssured.given;

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
                .when().post("http://social-api-stage.cism-ms.ru/" + "oauth/token")
                .then().statusCode(200)
                .extract().cookies();


    }
}