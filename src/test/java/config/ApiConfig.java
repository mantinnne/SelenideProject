package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${env}.properties")
public interface ApiConfig extends Config {

    @Key("host_cism")
    String  getUrlCism();

    @Key("host_core")
    String getUrlCore();


}

