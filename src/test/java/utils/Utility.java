package utils;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;

import java.util.LinkedHashMap;
import java.util.Map;

public class Utility {

    @DisplayName("This method will create random Spartan as Map")
    public static Map<String, Object> randomSpartanInMapObject() {
        Faker faker = new Faker();
        Map<String, Object> randomSpartanInMapObject = new LinkedHashMap<>();
        randomSpartanInMapObject.put("name",faker.name().firstName());
        randomSpartanInMapObject.put("gender",faker.demographic().sex());
        randomSpartanInMapObject.put("phone",faker.number().numberBetween(5_000_000_000L, 10_000_000_000L));
        return randomSpartanInMapObject;
    }
}
