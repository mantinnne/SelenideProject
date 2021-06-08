package oauth;

import Entities.model.MainUser;
import config.ConfigHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TakeToken {

    static MainUser user = new MainUser();

    public Map<String, String> main() {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user.urlEncoded())
                .when().log().body().post("http://social-api-stage.cism-ms.ru/oauth/token")
                .then().statusCode(200).extract().cookies();

    }
}



