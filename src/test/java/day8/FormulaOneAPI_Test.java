package day8;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Driver;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.reset;

public class FormulaOneAPI_Test {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://ergast.com";
        RestAssured.basePath = "/api/f1";
    }


    @DisplayName("GET /driver.json and save result to pojo")
    @Test
    public void testDrivers() {

        // Get first Driver as Driver POJO
        JsonPath jp = get("/drivers.json").jsonPath();
        Driver d1 = jp.getObject("MRData.DriverTable.Drivers[0]",Driver.class);
        //System.out.println("d1 = " + d1);
        // Get all drivers as List<Driver>
        List<Driver> drivers = jp.getList("MRData.DriverTable.Drivers",Driver.class);
        //System.out.println("drivers = " + drivers);


        for (Driver eachDriver : drivers) {
            if (eachDriver.getNationality().equalsIgnoreCase("American")) {
                System.out.println(eachDriver.getGivenName());
            }
        }



    }


    @AfterAll
    public static void cleanUp() {
        reset();
    }
}
