package helpers;

import io.restassured.response.Response;
import testData.Bear;

import java.util.List;

import static io.restassured.RestAssured.when;

public class BearReading {
    public static List<Bear> getListOfAllBears() {
        Response response = when()
            .get("/bear");
        return deserializeListOfBearsFromJson(response);
    }

    public static Bear getBear(Bear bear) {
        Response response = readSpecificBear(bear);
        return deserializeBearFromJson(response);
    }

    public static Response readSpecificBear(Bear bear) {
        return when()
            .get("/bear/" + bear.getBear_id());
    }

    public static Bear deserializeBearFromJson(Response response) {
        return response.getBody().as(Bear.class);
    }

    public static List<Bear> deserializeListOfBearsFromJson(Response response) {
        return response.body().jsonPath().getList(".", Bear.class);
    }
}
