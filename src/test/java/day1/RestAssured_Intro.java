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

        String contentType = response.getContentType();
        String contentType2 = response.contentType();
        assertThat(contentType2, startsWith("text/plain"));
        assertThat(contentType, endsWith("8"));

        String body = response.asString();
        System.out.println("body = " + body);
        assertThat(body, is(equalTo("Hello from Sparta")));


    }

}
