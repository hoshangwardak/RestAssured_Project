package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@ToString
public class Department {

    // instructing jackson to bind json field called department to below Java field
    @JsonProperty("department_id")
    private String departmentId; // json field: department_id
    @JsonProperty("department_name")
    private String name;          // json field: department_name
    private int manager_id;
    private  int location_id;
}
