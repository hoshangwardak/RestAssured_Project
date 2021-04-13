package day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.*;
import utils.HR_ORDS_API_BaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.MovieAPI_BaseTest;

public class MovieAPI_PracticeTest extends MovieAPI_BaseTest {

    // save the result of your request
    // SEND GET http://www.omdbapi.com/?t=Avenger&apikey=YOUR OWN API KEY goes here
    // save the response into Movie POJO , title Str, year int , Released str ,Language
    // ignore any unknown properties
    // match the json fields to pojo fields
    @DisplayName("GET GET http://www.omdbapi.com/?t=Avenger&apikey=YOUR OWN API KEY")
    @Test
    public void testMovieToPOJO(){

        Movie movie = given()
                .queryParam("apikey","6f1abf8")
                .queryParam("t", "Superman")
                .when()
                .get("")// This API doesn't have a basePath it just have a base URI
//                .jsonPath()
//                .getObject("",Movie.class)
                .as(Movie.class)
                ;
        System.out.println("movie = " + movie);
    }

    @DisplayName("GET Search for avenger and save Ratings field into List<Rating>")
    @Test
    public void testMovieRatingToPOJO(){

        List<Rating> allRatings = given()
                .queryParam("apikey","6f1abf8")
                .queryParam("t", "Avenger")
                .when()
                .get("")
                .jsonPath()
                .getList("Ratings",Rating.class)
                ;
        System.out.println("allRatings = " + allRatings);

    }



}
