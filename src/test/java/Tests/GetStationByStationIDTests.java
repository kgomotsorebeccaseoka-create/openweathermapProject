package Tests;

import RequestBuilder.APIRequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Tests.PostStationAPITests.stationId;

public class GetStationByStationIDTests {

    @Test(groups = {"priority = 3"}, dependsOnMethods = {"registerStationTest"})
    public void getStationByStationIdTest() {

        System.out.println("Getting Station with ID = " + stationId);

        Response response = APIRequestBuilder.getStationById(stationId);

        assert response.statusCode() == 200 : "Expected 200, but got: " + response.statusCode();

        // Validate some fields
        assert response.jsonPath().getString("id").equals(stationId) : "Station ID mismatch";

        System.out.println("✔ Station retrieved successfully");
    }
}
