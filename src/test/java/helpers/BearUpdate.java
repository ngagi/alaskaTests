package helpers;

import testData.Bear;

import static io.restassured.RestAssured.given;

public class BearUpdate {
    public static void updateBear(Bear expectedBear) {
        given()
            .body(expectedBear.getRequestBodyForCreationOrUpdate())
        .when()
            .put("/bear/" + expectedBear.getBear_id());
    }
}
