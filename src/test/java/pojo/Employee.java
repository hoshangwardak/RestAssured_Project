package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) // This is basically ignoring whatever payload that is
// not matching with my POJO class fields.
public class Employee {

    private int employee_id;
    private String first_name;
    private String last_name;
    private int salary;
    private int department_id;

}
