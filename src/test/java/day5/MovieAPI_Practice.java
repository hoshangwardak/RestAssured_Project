package day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieAPI_Practice {

    @DisplayName("Search movies jsonPath practice")
    @Test
    public void testSearch() {

    JsonPath jsonPath =
                    given()
                            .baseUri("http://www.omdbapi.com/")
                            .log().all()
                            .queryParam("apikey","6f1abf8")
                            .queryParam("s","Superman")
                            .queryParam("type", "series")
                    .when()
                            .get("")
                    .then()
                            .log().all()
                            .statusCode(is(200))
                            .extract()
                            .jsonPath()
                            ;
    List<String> allTitles = new LinkedList<>();
    allTitles.addAll(jsonPath.getList("Search.Title"));
    //System.out.println("allTitles = " + allTitles.size());
    int totalResults = jsonPath.getInt("totalResults");
    //System.out.println("totalResults = " + totalResults);
    int pages = (totalResults % 10 == 0)? totalResults/10 : (totalResults/10)+1;

    for (int i=2; i<=pages; i++) {

        List<String> allTitlesOnThisPage = given()
                .queryParam("apikey","6f1abf8")
                .queryParam("s","Superman")
                .queryParam("type", "series")
                .queryParam("page",i)
                .get("http://www.omdbapi.com/")
                .jsonPath()
                .getList("Search.Title")
                ;
        allTitles.addAll(allTitlesOnThisPage);
    }
        System.out.println("allTitles = " + allTitles.size());

        assertThat(allTitles, hasSize(21));
        assertThat(allTitles.get(0),containsString("Superman"));
        assertThat(allTitles, hasItem("Superman and Lois"));
        assertThat(allTitles, hasItems("Superman and Lois","The New Adventures of Superman"));
        assertThat(allTitles, everyItem(containsString("Superman")));

    }

    @DisplayName("Search movies response body validation in the chain")
    @Test
    public void testSearch2() {

                given()
                        .baseUri("http://www.omdbapi.com/")
                        .log().all()
                        .queryParam("apikey","6f1abf8")
                        .queryParam("s","Superman")
                        .queryParam("type", "series")
                        .when()
                        .get("")
                        .then()
                        .log().all()
                        .statusCode(is(200))
                        .body("Search.Title",hasSize(10))
                        .body("Search[0].Title", containsString("Superman"))
                        .body("Search.Title", hasItem("Superman and Lois"))
                        .body("Search.Title",hasItems("Superman and Lois","The New Adventures of Superman"))
                        .body("Search.Title",everyItem(containsString("Superman")))
                        ;

    }

}
