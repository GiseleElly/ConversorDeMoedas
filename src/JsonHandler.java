import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHandler {

    public static JsonObject parseJson(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }
}
