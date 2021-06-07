package Entities.model;

import config.ConfigHelper;
import lombok.Data;

@Data
public class CoreUser {

    private String grant_type = "\"password\"";
    private int client_id = 3;
    private String client_secret = "\"aVmAt1RnYq8ceDnkPXFSYfxNtpNNgvFknObjdNfq\"";
    private String username = ConfigHelper.LOGIN;
    private String password = ConfigHelper.PASSWORD;

    public String urlEncoded() {
        return "{ \"grant_type\":" + grant_type + ",\"client_id\":" + client_id + ",\"client_secret\":" + client_secret + ",\"username\":" + username + ",\"password\":" + password + "}";
    }
}
