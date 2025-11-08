package Tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Common.commonTestData.create_success_status_code;
import static RequestBuilder.APIRequestBuilder.registerStationResponse;
import static Utils.generateTestData.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@Test
@Feature("Ndosi API")
@Story("Login")
public class PostStationAPITests {

    private static String token;

     

    public void registerStationTest() {
        Response response = registerStationResponse(externalId, name, latitude, longitude, altitude).
                then().
                log().all().
                assertThat().
                statusCode(create_success_status_code).
                body("ID", notNullValue()).
                body("external_id", equalTo(externalId)).
                body("name", equalTo(name)).
                body("latitude", equalTo(latitude)).
                body("longitude", equalTo(longitude)).
                extract().
                response();
    }
}
