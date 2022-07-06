package com.kimmy.interviewassessment.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "created",
        "id",
        "karma",
        "submitted"
})
@Data
public class Author {

    @JsonProperty("created")
    private Integer created;
    @JsonProperty("id")
    private String id;
    @JsonProperty("karma")
    private Integer karma;
    @JsonProperty("submitted")
    private List<Integer> submitted = null;


}