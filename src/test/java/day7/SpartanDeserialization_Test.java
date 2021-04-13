package day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.path.json.JsonPath;
import pojo.SpartanPOJO;
import utils.SpartansNoAuthBaseTest;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanDeserialization_Test extends SpartansNoAuthBaseTest {

    @DisplayName("GET/spartans/{id}")
    @Test
    public void testGetOneData() {

        given()
                .pathParam("id",100)
                .contentType(ContentType.JSON)
        .when()
                .get("spartans/{id}")
        .then()
                .log().body()
                .statusCode(is(200))
                ;

        // Send same request, store the result into SpartanPOJO object

        Response response = given()
                .pathParam("id", 100)
                .when()
                .get("spartans/{id}") ;

        SpartanPOJO sp = response.as(SpartanPOJO.class);
        System.out.println("sp = " + sp);

        JsonPath jp = response.jsonPath();
        SpartanPOJO sp1 = jp.getObject("",SpartanPOJO.class);
        System.out.println("sp1 = " + sp1);
    }

    @DisplayName("GET/spartans/search")
    @Test
    public void testSearch() {

        Response response = given()
                .log().uri()
                .queryParam("nameContains","M")
                .queryParam("gender","Male")
        .when()
                .get("spartans/search")
                //.prettyPeek()
                ;
        JsonPath jp = response.jsonPath();
        SpartanPOJO sp = jp.getObject("content[0]",SpartanPOJO.class);
        System.out.println("sp = " + sp);

        // this is how we can do whole thing in one chain

        SpartanPOJO sp1 = given()
                .log().uri()
                .queryParam("nameContains","M")
                .queryParam("gender","Male")
                .when()
                .get("spartans/search")
                .jsonPath()
                .getObject("content[0]",SpartanPOJO.class)
                ;
        System.out.println("sp1 = " + sp1);

    }

    @DisplayName("GET/spartans/ and save as List<SpartanPOJO>")
    @Test
    public void testSearchSaveAsList() {

        List<SpartanPOJO> sp1 = given()
                .log().uri()
                .queryParam("nameContains","A")
                .queryParam("gender","Male")
                .when()
                .get("spartans/search")
                .jsonPath()
                .getList("content",SpartanPOJO.class)
                ;
        System.out.println("sp1 = " + sp1.size());
    }




}
