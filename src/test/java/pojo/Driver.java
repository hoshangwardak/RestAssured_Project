package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.junit.jupiter.params.ParameterizedTest;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver {

    private String driverId;
    private String url;
    private String givenName;
    private String familyName;
    private String dateOfBirth;
    private String nationality;
}
