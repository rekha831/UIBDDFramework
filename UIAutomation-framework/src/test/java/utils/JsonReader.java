package utils;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {

    public static String getUserData(String userType, String key) {
        return getJsonValue("src/test/resources/testdata/users.json", userType, key);
    }

    public static String getMessage(String key) {
        return getJsonValue("src/test/resources/testdata/messages.json", key, null);
    }

    private static String getJsonValue(String filePath, String parentKey, String childKey) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;

            if (childKey != null) {
                JSONObject innerObject = (JSONObject) jsonObject.get(parentKey);
                return (String) innerObject.get(childKey);
            } else {
                return (String) jsonObject.get(parentKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
