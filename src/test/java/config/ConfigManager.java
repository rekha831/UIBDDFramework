package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    // 1️⃣ Private static instance
    private static ConfigManager instance;
    private final Properties props = new Properties();

    // 2️⃣ Private constructor (prevents external instantiation)
    private ConfigManager() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config/config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // 3️⃣ Public accessor with lazy initialization
    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) { // thread-safe
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    // 4️⃣ Accessor methods
    public String get(String key) {
        return props.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
