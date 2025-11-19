package PayLoadBuilder;

import org.json.simple.JSONArray;
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

    /**
     * Converts numbers; if invalid (e.g. “ABC”), keeps as String → good for negative tests
     */
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


    public static JSONArray postMeasurements(String station_id, String dt, String temperature, String wind_speed,
                                             String wind_gust, String pressure, String humidity, String rain_1h, String cloudsCondition) {

        JSONArray measurementsArray = new JSONArray();
        JSONArray conditionsArray = new JSONArray();


        JSONObject measurementObject = new JSONObject();
        measurementObject.put("station_id", station_id);
        measurementObject.put("dt", numberOrString(dt));
        measurementObject.put("temperature", numberOrString(temperature));
        measurementObject.put("wind_speed", numberOrString(wind_speed));
        measurementObject.put("wind_gust", numberOrString(wind_gust));
        measurementObject.put("pressure", numberOrString(pressure));
        measurementObject.put("humidity", numberOrString(humidity));
        measurementObject.put("rain_1h", numberOrString(rain_1h));

        if (cloudsCondition != null && !cloudsCondition.trim().isEmpty()) {
            String[] conditions = cloudsCondition.split(";");

            for (String condition : conditions) {
                JSONObject conditionObject = new JSONObject();
                conditionObject.put("condition", condition.trim());
                conditionsArray.add(conditionObject);
            }
            measurementObject.put("conditions", conditionsArray);
        }
        measurementsArray.add(measurementObject);
        return measurementsArray;
    }

}
