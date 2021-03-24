package config;

import org.aeonbits.owner.Config;

public interface WebDriverConfig extends Config {

    @Key("remoteUrl")
    String getWebDriverUrl();

    @Key("browser")
    String getWebBrowser();

    @Key("version")
    String getVersionBrowser();

    @Key("url")
    String getUrl();

    @Key("enabled")
    boolean isEnabled();


}
