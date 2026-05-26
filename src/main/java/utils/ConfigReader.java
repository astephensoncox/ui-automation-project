package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        loadEnvironmentProperties();
    }

    private static void loadEnvironmentProperties() {
        String env = System.getProperty("env", "dev"); // default = dev
        String filePath = "src/main/resources/" + env + ".properties";

        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
            System.out.println("Loaded environment: " + env);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load environment file: " + filePath);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}

