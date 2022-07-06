package com.kimmy.interviewassessment.controller;

import com.kimmy.interviewassessment.model.WordsResponse;
import com.kimmy.interviewassessment.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AssessmentController {

    private final InterviewService interviewService;

    @GetMapping(value = "/topwordsinlateststories")
    public WordsResponse getTop10WordsInlast25Stories() {
        return interviewService.top10WordsInlast25Stories();
    }

    @GetMapping(value = "/topauthorsitemswords")
    public WordsResponse getTop10WordsInTopAuthorsItems() {
        return interviewService.getTop10WordsInTopAuthorsItems();
    }

     @GetMapping(value = "/topwordslastweek")
    public WordsResponse getTop10WordsInPostsOfLastWeek() {
        return interviewService.top10WordsInPostsOfLastWeek();
    }

}
