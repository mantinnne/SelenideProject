package Entities.model;

import config.ConfigHelper;
import lombok.Data;

@Data
public class MainUser {

    private String grant_type = "\"password\"";
    private int client_id = 2;
    private String client_secret = "\"0J4a1RP83KXeTTFrA96aWNYPHI6PPQOYyukzzKr6\"";
    private String username = ConfigHelper.LOGIN;
    private String password = ConfigHelper.PASSWORD;

    public String urlEncoded() {
        return "{ \"grant_type\":" + grant_type + ",\"client_id\":" + client_id + ",\"client_secret\":" + client_secret + ",\"username\":" + username + ",\"password\":" + password + "}";
    }
}
