package config.oauth;

import io.restassured.http.ContentType;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TakeTokenCore {


    public Map<String, String> main() {
        String jsonBody = "{\n" +
                "\t\"grant_type\": \"password\",\n" +
                "\t\"client_id\": 3,\n" +
                "\t\"client_secret\": \"aVmAt1RnYq8ceDnkPXFSYfxNtpNNgvFknObjdNfq\",\n" +
                "\t\"username\": \"tester@cism-ms.ru\",\n" +
                "\t\"password\": \"id7@cS2jA\"\n" +
                "}";
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonBody)
                .when().post("http://core.cism-ms.ru/oauth/token")
                .then().statusCode(200)
                .extract().cookies();

    }
}

