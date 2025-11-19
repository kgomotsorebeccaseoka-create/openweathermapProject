package Tests;

import Common.BasePaths;
import PayLoadBuilder.APIPayloadBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Tests.PostStationAPITests.stationId;
import static io.restassured.RestAssured.given;

public class RegisterMeasurementsTest {
    @Test(
            dataProvider = "Measurements",
            dataProviderClass = Utils.TestDataProviders.class,
            groups = {"priority = 2"}, dependsOnMethods = {"registerStationTest"}
    )

    public void createMeasurementsTest(String scenario,
                                       String endpoint,
                                       String dt,
                                       String temperature,
                                       String wind_speed,
                                       String wind_gust,
                                       String pressure,
                                       String humidity,
                                       String rain_1h,
                                       String cloudsCondition,
                                       String expectedStatusStr) {

        System.out.println("=== Scenario: " + scenario + " ===");

        int expectedStatus = Integer.parseInt(expectedStatusStr);

        Response response =

                given().
                        baseUri(BasePaths.OpenWeatherBaseUrl).
                        basePath(endpoint).
                        queryParam("appid", Common.Secrets.openWeatherKey()).
                        contentType("application/json").
                        body(APIPayloadBuilder.postMeasurements(stationId, dt, temperature, wind_speed, wind_gust, pressure, humidity, rain_1h, cloudsCondition)).
                        log().all().
                        post().
                        then().
                        log().all().
                        extract().response();

        assert response.statusCode() == expectedStatus : "Expected status code: " + expectedStatus + ", but got: " + response.statusCode();

    }
}

