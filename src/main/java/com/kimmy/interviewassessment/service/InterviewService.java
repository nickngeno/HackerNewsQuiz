package com.kimmy.interviewassessment.service;


import com.kimmy.interviewassessment.model.WordsResponse;

public interface InterviewService {

    WordsResponse top10WordsInlast25Stories();
    WordsResponse top10WordsInPostsOfLastWeek();
    WordsResponse getTop10WordsInTopAuthorsItems();

}
