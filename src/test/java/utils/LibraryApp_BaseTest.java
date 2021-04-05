package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public abstract class LibraryApp_BaseTest {

    @BeforeAll
    public static void settingUpLibraryApp() {
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1/";
    }

    @AfterAll
    public static void cleanUpLibraryApp() {
        reset();
    }


}
