package utils;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Utility class to read JSON test data (users, messages, etc.)
 * from src/test/resources/testdata folder.
 */
public class JsonReader {

    private static final String USER_FILE_PATH = "src/test/resources/testdata/users.json";
    private static final String MESSAGE_FILE_PATH = "src/test/resources/testdata/messages.json";

    /**
     * Get user-specific data (like email/password) from users.json
     *
     * @param userType name of the user object in users.json (e.g., "validUser")
     * @param key      key inside that object (e.g., "email" or "password")
     * @return value of that field
     */
    public static String getUserData(String userType, String key) {
        return getJsonValue(USER_FILE_PATH, userType, key);
    }

    /**
     * Get global message or text values from messages.json
     *
     * @param key top-level key in messages.json
     * @return value of the key
     */
    public static String getMessage(String key) {
        return getJsonValue(MESSAGE_FILE_PATH, key, null);
    }

    /**
     * Generic method to fetch a JSON value safely.
     */
    private static String getJsonValue(String filePath, String parentKey, String childKey) {
        try {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;

            // If we are fetching nested data like user -> password
            if (childKey != null) {
                JSONObject innerObject = (JSONObject) jsonObject.get(parentKey);
                if (innerObject == null) {
                    throw new RuntimeException("❌ No such parent key found in JSON: " + parentKey);
                }

                Object value = innerObject.get(childKey);
                if (value == null) {
                    throw new RuntimeException("❌ Key '" + childKey + "' not found under '" + parentKey + "'");
                }
                return value.toString();
            }
            // Otherwise, fetch top-level data (e.g. messages.json)
            else {
                Object value = jsonObject.get(parentKey);
                if (value == null) {
                    throw new RuntimeException("❌ Key '" + parentKey + "' not found in JSON file: " + filePath);
                }
                return value.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to read from JSON file: " + filePath + "\n" + e.getMessage());
        }
    }
}
