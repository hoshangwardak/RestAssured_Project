package day3.Assignment;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.SpartansNoAuthBaseTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class OMDBAPI_Homework {

    @DisplayName("Superman")
    @Test
    public void homework1() {

        Response response =

                given()
                        .queryParam("apikey","6f1abf8")
                        .queryParam("t", "Superman")
                        .queryParam("plot","full")
                        .queryParam("r","application/json")
                .when()
                        .get("http://www.omdbapi.com/")
                        .prettyPeek()
                ;

        JsonPath js = response.jsonPath();

        String title = js.getString("Title");
        int year = js.getInt("Year");
        Double imdbRating = js.getDouble("imdbRating");
        String secondRatingsSource = js.getString("Ratings[1].Source");
        String firstRatingsValue = js.getString("Ratings[0].Value");

        assertThat(title, is(equalTo(js.getString("Title"))));
        assertThat(year, is(equalTo(js.getInt("Year"))));
        assertThat(secondRatingsSource, is(equalTo( js.getString("Ratings[1].Source") )));
        assertThat(firstRatingsValue, is(equalTo( js.getString("Ratings[0].Value") )));

        System.out.println("title = " + title);
        System.out.println("year = " + year);
        System.out.println("imdbRating = " + imdbRating);
        System.out.println("secondRatingsSource = " + secondRatingsSource);
        System.out.println("firstRatingsValue = " + firstRatingsValue);

    }

    @DisplayName("The Flash")
    @Test
    public void homework2() {

        Response response =

                given()
                        .queryParam("apikey","6f1abf8")
                        .queryParam("s", "Flash")
                        .queryParam("type","Series")
                        .when()
                        .get("http://www.omdbapi.com/")
                        .prettyPeek()
                ;

        JsonPath js = response.jsonPath();

        String title = js.getString("Search[2].Title");
        String year = js.getString("Search[2].Year");
        String imdbID = js.getString("Search[2].imdbID");

        System.out.println("title = " + title);
        System.out.println("year = " + year);
        System.out.println("imdbID = " + imdbID);

        int totalResults = js.getInt("totalResults");
        System.out.println("totalResults = " + totalResults);

        int by10 = totalResults/10;
        List<String> allIMDBID = new LinkedList<>();
        List<String> allTitles = new LinkedList<>();

            for (int i=0; i<by10+1; i++) {

                js =
                        given()
                                .queryParam("apikey","6f1abf8")
                                .queryParam("s", "Flash")
                                .queryParam("type","Series")
                                .when()
                                .get("http://www.omdbapi.com/")
                                .jsonPath()
                ;
                allIMDBID.addAll(js.getList("Search.imdbID"));
                allTitles.addAll(js.getList("Search.Title"));
            }

        System.out.println("All IMDBID = " + allIMDBID);
        System.out.println("All IMDBID size = " + allIMDBID.size());
        System.out.println("All Titles = " + allTitles);
        System.out.println("All Titles size = " + allTitles.size());

    }


}
