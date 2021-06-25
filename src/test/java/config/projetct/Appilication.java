package config.projetct;

import org.aeonbits.owner.ConfigFactory;

public class Appilication {
    public static ApplicationConfig config = ConfigFactory.create(ApplicationConfig.class, System.getProperties());
}
