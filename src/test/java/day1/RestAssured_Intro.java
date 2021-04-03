package day1;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RestAssured_Intro {

    @DisplayName("Testing hello endpoint")
    @Test
    public void testHelloEndPoint() {

        Response response = get("http://18.234.107.235:8000/api/hello");

        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);
        assertThat(statusCode, is(equalTo(200)));

        assertThat(response.statusCode(), is(statusCode));
        assertThat(response.getStatusCode(), is(equalTo(statusCode)));

        System.out.println("=======================================");

        String header = response.getHeader("Content-Type");
        System.out.println("header = " + header);
        assertThat(header, is(equalTo("text/plain;charset=UTF-8")));
        assertThat(response.header("Content-Type"), is("text/plain;charset=UTF-8"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));


        String contentType = response.getContentType();
        String contentType2 = response.contentType();
        assertThat(contentType2, startsWith("text/plain"));
        assertThat(contentType, endsWith("8"));

        String body = response.asString();
        System.out.println("body = " + body);
        assertThat(body, is(equalTo("Hello from Sparta")));

        // Getting the time of execution
        System.out.println("ExecutionTime = " + response.getTime());
        
        // Printing the result
        // prettyPrint() ==> Returns you String
        // prettyPeek() ==> Returns you same response object
        System.out.println("===============================");
        response.prettyPrint();
        System.out.println("============================");
        response.prettyPeek();

    }

    @DisplayName("Testing Get /api/spartans/{id} Endpiont")
    @Test
    public void testSingleSpartan() {

        Response response = get("http://18.234.107.235:8000/api/spartans/1");

        response.prettyPeek();
        System.out.println("====================================================");

        response.prettyPrint();
        System.out.println("====================================================");

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is(equalTo("application/json")));
        assertThat(response.header("Connection"), is(equalTo("keep-alive")));

        System.out.println("response.asString() = " + response.asString());

        System.out.println("=========================================================");

        System.out.println("response json id = " + response.path("id"));
        System.out.println("response json name " + response.path("name"));
        System.out.println("response json gender = " + response.path("gender"));
        System.out.println("response json phone " + response.path("phone"));

        System.out.println("=====================================================");

        int id = response.path("id");
        String name = response.path("name");
        long phoneNumber = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("phoneNumber = " + phoneNumber);


    }



}
