package PayloadBuilder;

import org.json.simple.JSONObject;

public class APIPayloadBuilder {

    public static JSONObject registerStationAPI(String external_id, String name, Float latitude, Float longitude, Float altitude) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("external_id", external_id);
        jsonObject.put("name", name);
        jsonObject.put("latitude", latitude);
        jsonObject.put("longitude", longitude);
        jsonObject.put("altitude", altitude);

        return jsonObject;
    }

}
