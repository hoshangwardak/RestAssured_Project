package day7.assignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import oracle.security.o3logon.a;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import javax.swing.*;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HackerrankChallenge {

    /*
     * This is a hacker rank challenge given in the interview
     * Given this endpoint for getting football match data
     * http://jsonmock.hackerrank.com/api/football_matches
     * below query params are available
     * year  :  year the match occurred
     * team1 :  home team name
     * team2 :  visiting team name
     * page  :  page number , max result in per response is 10
     *           any result more than 10 will be on next pages
     *           so in order to get all data you need to
     *           make separate requests until there are no more pages
     *
     * Response Format :
     *  {
     *     "page": "1",
     *     "per_page": 10,
     *     "total": 23,
     *     "total_pages": 3,
     *     "data": [
     *         {
     *             "competition": "UEFA Champions League",
     *             "year": 2012,
     *             "round": "GroupB",
     *             "team1": "Arsenal",
     *             "team2": "Olympiacos",
     *             "team1goals": "3",
     *             "team2goals": "1"
     *         },
     *         {...},
     *         {...}
     *         ]
     *  }
     *
     * Examples Requests :
     *
     * GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team1=Arsenal&team2=Chelsea&page=1
     *  return data for match between Arsenal(home team) and Chelsea(visiting team) , show page 1
     * GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team1=Arsenal&page=1
     *  return data for 2012 match between Arsenal(home team) and any visiting team and show page 2
     * GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team2=Arsenal&page=2
     *  return data for match between any home team) and Chelsea(visiting team) and show page 2
     *
     *  Write a method to return total goals of a team in a given year
     *      * Include the both result where the team is home team or visiting team
     */


    @DisplayName("Arsenal(HomeTeam) vs Chelsea(VisitingTeam)")
    @Test
    public void arsenalVsChelsea() {

     // GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team1=Arsenal&team2=Chelsea&page=1
     // return data for match between Arsenal(home team) and Chelsea(visiting team) , show page 1

       List<Map<String, Object>> arsenalVsChelsea =

        given()
                .log().all()
                .baseUri("http://jsonmock.hackerrank.com")
                .basePath("/api/")
                .queryParam("year",2012)
                .queryParam("team1","Arsenal")
                .queryParam("team2","Chelsea")
                .queryParam("page",1)
        .when()
                .get("football_matches")
                .jsonPath()
                .getList("data")
                ;

        System.out.println("arsenalVsChelsea = " + arsenalVsChelsea);

    }

    @DisplayName("Arsenal(HomeTeam) vs Any(VisitingTeam)")
    @Test
    public void arsenalVsAnyVisitingTeam() {

        //  GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team1=Arsenal&page=1
        //  return data for 2012 match between Arsenal(home team) and any visiting team and show page 2

        JsonPath js =

                given()
                        .log().uri()
                        .baseUri("http://jsonmock.hackerrank.com")
                        .basePath("/api/")
                        .queryParam("year", 2012)
                        .queryParam("team1", "Arsenal")
                        .queryParam("page", 1)
                        .when()
                        .get("football_matches")
                        .jsonPath();

        List<String> arsenalVsAnyVisitingTeam = js.getList("data");
       System.out.println(arsenalVsAnyVisitingTeam);

//    Map<String, Object> map = js.get("data[0]");
//        System.out.println(map);

    }

    @DisplayName("Any(HomeTeam) vs Chelsea(VisitingTeam)")
    @Test
    public void anyHomeTeamAndChelseaVisitingTeam() {

     // GET http://jsonmock.hackerrank.com/api/football_matches?year=2012&team2=Chelsea&page=2
     // return data for match between any home team) and Chelsea(visiting team) and show page 2

        JsonPath js =

                given()
                        .log().uri()
                        .baseUri("http://jsonmock.hackerrank.com")
                        .basePath("/api/")
                        .queryParam("year", 2012)
                        .queryParam("team2", "Chelsea")
                        .queryParam("page", 2)
                        .when()
                        .get("football_matches")
                        .jsonPath();

        List<String> arsenalVsAnyVisitingTeam = js.getList("data");
        System.out.println(arsenalVsAnyVisitingTeam);

//    Map<String, Object> map = js.get("data[0]");
//        System.out.println(map);

    }


    @DisplayName("Method to return total number of goals in given year")
    @Test
    public static void numberOfGoals(String team, int year) {

     // Write a method to return total goals of a team in a given year
     // Include the both result a the team is home team or visiting team


    }


}
