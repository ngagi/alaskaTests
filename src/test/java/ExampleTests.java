import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;
import testData.Bear;
import testData.BearType;
import helpers.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExampleTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://0.0.0.0";
        RestAssured.port = 8091;
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    @Test
    public void getInformationRequestReturnsStatusCode200() {
        int statusCode = InformationReading
            .getResponseOfInformationRequest().statusCode();
        assertThat("the status code is not 200",
            statusCode, equalTo(200));
    }

    @Test
    public void bearWithValidParametersStoresCorrectlyAfterCreation() {
        Bear expectedBear = new Bear(BearType.POLAR, "Ada", 10);
        BearCreation.createBearAndSetItsId(expectedBear);

        Bear actualBear = BearReading
            .getBear(expectedBear);

        assertThat("the bear was wrongly saved",
            actualBear, equalTo(expectedBear));
    }

    @Test
    public void listOfbearsWithValidParametersStoresCorrectlyAfterCreation() {
        BearDeletion.deleteAllBears();

        List<Bear> expectedListOfBears = new ArrayList<>();
        expectedListOfBears.add(new Bear(BearType.BLACK, "Annie", 2));
        expectedListOfBears.add(new Bear(BearType.BROWN, "Meryl", 15));

        BearCreation.createBearsFromList(expectedListOfBears);

        List<Bear> actualListOfBears = BearReading.getListOfAllBears();

        assertThat("the actual list differs from the expected list",
            actualListOfBears, equalTo(expectedListOfBears));
    }

    @Test
    public void listOfBearsAfterDeletionOfAllBearsHasSize0() {
        List<Bear> expectedListOfBears = new ArrayList<>();
        expectedListOfBears.add(new Bear(BearType.GUMMY, "Katya", 9));
        expectedListOfBears.add(new Bear(BearType.POLAR, "Lana", 20));

        BearCreation.createBearsFromList(expectedListOfBears);

        BearDeletion.deleteAllBears();

        int size = BearReading.getListOfAllBears().size();

        assertThat("the size of bear list is not 0",
            size, equalTo(0));
    }

    @Test
    public void readingSpecificBearAfterDeletionReturnsMessageEmpty() {
        Bear bear = new Bear(BearType.BROWN, "Linda", 5);
        BearCreation.createBearAndSetItsId(bear);

        BearDeletion.deleteSpecificBear(bear);

        String responseBodyOfReadingBear = BearReading
            .readSpecificBear(bear)
                .body().asString();

        assertThat("the message from response body is wrong",
            responseBodyOfReadingBear, equalTo("EMPTY"));
    }

    @Test
    public void bearCanBeUpdated() {
        Bear expectedBear = new Bear(BearType.POLAR, "Katya", 1);
        BearCreation.createBearAndSetItsId(expectedBear);

        expectedBear.setBear_type(BearType.BLACK);
        expectedBear.setBear_name("Tonya");
        expectedBear.setBear_age(9);

        BearUpdate.updateBear(expectedBear);

        Bear actualBear = BearReading
            .getBear(expectedBear);

        assertThat("the bear was wrongly updated",
            actualBear, equalTo(expectedBear));
    }
}
