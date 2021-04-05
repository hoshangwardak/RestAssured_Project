package utils;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import pojo.Spartan;

import java.util.LinkedHashMap;
import java.util.Map;

public class Utility {

     private static Faker faker = new Faker();

    @DisplayName("This method will create random Spartan as Map")
    public static Map<String, Object> randomSpartanAsMapObject() {
        Map<String, Object> randomSpartanInMapObject = new LinkedHashMap<>();
        randomSpartanInMapObject.put("name",faker.name().firstName());
        randomSpartanInMapObject.put("gender",faker.demographic().sex());
        randomSpartanInMapObject.put("phone",faker.number().numberBetween(5_000_000_000L, 10_000_000_000L));
        return randomSpartanInMapObject;
    }

    @DisplayName("This method will create random Spartan as POJO")
    public static Spartan randomSpartanAsPOJOObject() {
        Spartan spartan = new Spartan();
        spartan.setName(faker.name().firstName());
        spartan.setGender(faker.demographic().sex());
        spartan.setPhone(faker.number().numberBetween(5_000_000_000L, 10_000_000_000L ));
        return spartan;
    }


}
