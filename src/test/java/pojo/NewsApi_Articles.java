package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApi_Articles {

   // @JsonProperty("source")
   // private Map<String,String> source;
    @JsonProperty("source")
    private NewsAPI_Source source;
    @JsonProperty("author")
    private String author;
    @JsonProperty("title")
    private String title;
}
