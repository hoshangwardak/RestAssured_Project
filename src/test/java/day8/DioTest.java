package day8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Dog;

public class DioTest {

    //SEND GET https://e94d0713-391b-4218-906d-66e9614ea580.mock.pstmn.io/dio
    /*
    {
        "breed": "Yorkie",
        "color": "Gold",
        "age": 3,
        "owner": {
            "ownerName": "Inci",
            "address": "123 main street"
        }
    }
     */
    // represent the result json in pojo , and print them out

    @DisplayName("Practice nested json into POJO")
    @Test
    public void testDioPOJO() {
        String requestUrl = "https://e94d0713-391b-4218-906d-66e9614ea580.mock.pstmn.io/dio";

        Dog dio = RestAssured.get(requestUrl).as(Dog.class);
        //System.out.println(dio);
        System.out.println("dio.getOwner().getOwnerName() = " + dio.getOwner().getOwnerName());
    }

}
