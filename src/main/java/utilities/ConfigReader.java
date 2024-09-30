package utilities;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
public class ConfigReader {
    private Properties properties;

    //This is used to raed from properties files and returns properties object
    public Properties initProp() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
            properties.load(fileInputStream);
        } catch (Exception e) {
            log.error("Unable to read Properties file.");
        }
        return properties;
    }
}