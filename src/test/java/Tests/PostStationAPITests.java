package Tests;

import Common.BasePaths;
import PayLoadBuilder.APIPayloadBuilder;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


@Feature("Weather API")
@Story("Post Station")
public class PostStationAPITests {

    private static String token;
    public static String stationId;


    @Test(
            dataProvider = "RegistrationStation",
            dataProviderClass = Utils.TestDataProviders.class,
            groups = {"priority = 1"}
    )
    public void registerStationTest(String scenario,
                                    String endpoint,
                                    String externalId,
                                    String name,
                                    String latitude,
                                    String longitude,
                                    String altitude,
                                    String expectedStatusStr) {

        System.out.println("=== Scenario: " + scenario + " ===");

        int expectedStatus = Integer.parseInt(expectedStatusStr);

        JSONObject requestBody = APIPayloadBuilder.registerStationAPI(externalId, name, latitude, longitude, altitude);
        Response response =

                given().
                        baseUri(BasePaths.OpenWeatherBaseUrl).
                        basePath(endpoint).
                        queryParam("appid", Common.Secrets.openWeatherKey()).
                        contentType("application/json").
                        body(requestBody).
                        log().all().
                        post().
                        then().
                        log().all().
                        extract().response();

        assert response.statusCode() == expectedStatus :
                "Expected status code: " + expectedStatus + ", but got: " + response.statusCode();

        stationId = response.jsonPath().getString("ID");
        System.out.println("Registered Station ID: " + stationId);

    }


}
