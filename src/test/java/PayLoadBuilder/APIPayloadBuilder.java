package PayLoadBuilder;
import org.json.simple.JSONObject;


public class APIPayloadBuilder {
    public static JSONObject registerStationAPI(String external_id, String name, String latitude, String longitude, String altitude) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("external_id", external_id);
        jsonObject.put("name", name);
        jsonObject.put("latitude", numberOrString(latitude));
        jsonObject.put("longitude", numberOrString(longitude));
        jsonObject.put("altitude", numberOrString(altitude));

        return jsonObject;
    }
    /** Converts numbers; if invalid (e.g. “ABC”), keeps as String → good for negative tests */
    private static Object numberOrString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "";  // Excel blank = JSON null
        }

        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return value; // invalid number → keep as string
        }
    }



  /*  public static JSONObject postMeasurements(String station_id, int dt, float temperature, float wind_speed,
                                              float wind_gust, int pressure, int humidity, int  rain_1h)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Station_id",);
        return jsonObject;
    }*/

}
