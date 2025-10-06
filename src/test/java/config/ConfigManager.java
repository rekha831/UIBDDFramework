package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static Properties props = new Properties();

	static {
		try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties")) {
			props.load(in);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

	public static int getInt(String key) {
		return Integer.parseInt(get(key));
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}
}