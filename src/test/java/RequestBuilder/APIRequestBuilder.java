package RequestBuilder;

import Common.Secrets;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.restassured.response.Response;

import static Common.BasePaths.*;
import static PayLoadBuilder.APIPayloadBuilder.*;
import static io.restassured.RestAssured.*;

public class APIRequestBuilder {
    static String apiToken;

    //login API Request
    public static Response registerStationResponse(String external_id, String name, String latitude, String longitude, String altitude) {

        return given().
                baseUri(OpenWeatherBaseUrl).
                basePath("/stations").
                queryParam("appid", Secrets.openWeatherKey()).
                contentType("application/json").
                body(registerStationAPI(external_id, name, latitude, longitude, altitude)).
                log().all().
                post().
                then().
                log().all().
                extract().response();
    }

    public static Response postMeasurementsResponse(String station_id, String dt, String temperature, String wind_speed,
                                                    String wind_gust, String pressure, String humidity, String  rain_1h, String cloudsCondition) {
        return given().
                baseUri(OpenWeatherBaseUrl).
                basePath("/measurements").
                queryParam("appid", Secrets.openWeatherKey()).
                contentType("application/json").
                body(postMeasurements(station_id, dt, temperature, wind_speed, wind_gust, pressure, humidity, rain_1h, cloudsCondition)).
                log().all().
                post().
                then().
                log().all().
                extract().response();
    }
}