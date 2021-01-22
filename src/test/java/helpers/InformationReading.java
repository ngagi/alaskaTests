package helpers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.when;

public class InformationReading {
    public static Response getResponseOfInformationRequest() {
        return when()
            .get("/info");
    }
}
