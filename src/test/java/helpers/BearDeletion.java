package helpers;

import testData.Bear;

import static io.restassured.RestAssured.when;

public class BearDeletion {
    public static void deleteAllBears() {
        when()
            .delete("/bear");
    }

    public static void deleteSpecificBear(Bear bear) {
        when()
            .delete("/bear/" + bear.getBear_id());
    }
}
