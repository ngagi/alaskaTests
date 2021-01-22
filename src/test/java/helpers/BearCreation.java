package helpers;

import testData.Bear;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BearCreation {
    public static void createBearAndSetItsId(Bear bear) {
        String response = given()
            .body(bear.getRequestBodyForCreationOrUpdate())
        .when()
            .post("/bear")
        .then()
            .extract().body().asString();

        bear.setBear_id(Integer.parseInt(response));
    }

    public static void createBearsFromList(List<Bear> listOfBears) {
        listOfBears.forEach(bear -> {
            createBearAndSetItsId(bear);
        });
    }
}
