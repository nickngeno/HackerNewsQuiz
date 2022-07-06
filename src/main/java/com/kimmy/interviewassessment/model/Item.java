package com.kimmy.interviewassessment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "by",
        "descendants",
        "id",
        "kids",
        "score",
        "time",
        "title",
        "type",
        "url"
})
@Data
public class Item {

    @JsonProperty("by")
    private String by;
    @JsonProperty("descendants")
    private Integer descendants;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("kids")
    private List<Integer> kids = null;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;

}
