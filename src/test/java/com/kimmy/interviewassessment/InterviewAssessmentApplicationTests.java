package com.kimmy.interviewassessment;

import com.kimmy.interviewassessment.service.impl.InterviewServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class InterviewAssessmentApplicationTests {

    @InjectMocks
    private InterviewServiceImpl interviewService;

    @Mock
    RestTemplate restTemplate;


    public void shouldReturnTop10WordsInTitlesOfLastest25Stories(){

    }

}
