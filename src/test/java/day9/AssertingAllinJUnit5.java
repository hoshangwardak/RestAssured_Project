package day9;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import utils.LibraryApp_BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.HR_ORDS_API_BaseTest;
import pojo.Region;
import utils.HR_ORDS_API_BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.SpartanPOJO;
import utils.DB_Utility;
import utils.LibraryApp_BaseTest;
import utils.SpartansNoAuthBaseTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static utils.DB_Utility.*;
public class AssertingAllinJUnit5 {


    @Test
    public void testAdd() {

        //assertThat(5+5, is(equalTo(10)));
        //assertThat(5+4, is(equalTo(10)));
        //assertThat(5+6, is(equalTo(10)));

        assertAll(
                ()-> assertThat(5+5, is(equalTo(10))),
                ()-> assertThat(5+4, is(equalTo(10))),
                ()-> assertThat(5+6, is(equalTo(10)))
        );
    }



}
