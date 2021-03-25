package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${env}.properties")
public interface WebDriverConfig extends Config {

    @Key("remote.url")
    String getRemoteUrl();

    @Key("browser")
    String getWebBrowser();

    @Key("browser.version")
    String getVersionBrowser();

    @Key("host.privat")
    String getUrlPrivat();

    @Key("host.oko")
    String getUrlOko();

    @Key("enabled")
    boolean isEnabled();

    @Key("video.storage")
    String videoStorage();


}
