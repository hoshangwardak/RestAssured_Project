package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;
@Getter @Setter
public class NewsAPI_Source {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

}
