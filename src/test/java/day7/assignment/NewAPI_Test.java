package day7.assignment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.NewsApi_Articles;
import utils.ConfigurationReader;
import utils.NewsApi_BaseTest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class NewAPI_Test extends NewsApi_BaseTest {

    // https://newsapi.org/v2/top-headlines?country=us&category=business

    @DisplayName("News API /Top Headlines printing author name")
    @Test
    public void newsApiTest() {

        /*
                    Homework :
            Read the doc of News API Org
            Send Authorized request to
            https://newsapi.org/v2/top-headlines?country=us&category=business
            Print author name and title if source id is not null
            HINT : Use your POJO Knowledge you learned Today
         */

        String apiKey = ConfigurationReader.getProperty("newsApiKey");

        List<NewsApi_Articles> allArticles =

                given()
                        .log().uri()
                        .queryParam("apiKey", apiKey)
                        .queryParam("country", "us")
                        .queryParam("category", "business")
                .when()
                        .get("/top-headlines")
                        .jsonPath()
                        .getList("articles", NewsApi_Articles.class);
        //allArticles.forEach(System.out::println);

        int counter = 0;

        for (NewsApi_Articles each : allArticles) {

            if (each.getSource().getId() != null) {
                counter++;
                  System.out.println("---------------------------------");
                  System.out.println("Author's Name = " + each.getAuthor());
                  System.out.println("--------------------------------");
                System.out.println("Title = " + each.getTitle());
            }

        }
        System.out.println("Size of allAuthors and Titles = " + counter);

        //System.out.println(allArticles.get(0).getSource().get("id"));
//        for (NewsApi_Articles each: allArticles){
//            if ( !(each.getSource().get("id") == null) ){
//                System.out.println("---------------------------------");
//                System.out.println(each);
//                System.out.println("--------------------------------");
//            }
//        }
    }


}
