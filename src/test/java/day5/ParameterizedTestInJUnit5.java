package day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.Spartan;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParameterizedTestInJUnit5 {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
    public void testPrintMultipleValue( int num) {
        //int num = 10;
        System.out.println("num = " + num);
        assertTrue(num > 5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ahmad","Mahmood", "Iqbal", "Bojan", "Daniel"})
    public void testPrintMultipleNames( String names ) {
        System.out.println("names = " + names);
        assertTrue(names.length() >= 5);
    }

    // SEND GET REQUEST TO http://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200
    @ParameterizedTest
    @ValueSource(ints = {22309,22310, 22032, 22193 , 22192, 22304, 22305})
    public void testZipCodes(int zipCode) {
        //System.out.println("zipCode = " + zipCode);
        given()
                .log().all()
                .baseUri("http://api.zippopotam.us")
                .basePath("/us/")
                .pathParam("zipCode",zipCode)
        .when()
                .get("{zipCode}")
        .then()
                .log().body()
                .statusCode(is(200))

                ;
    }



}
