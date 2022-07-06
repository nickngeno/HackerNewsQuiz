package com.kimmy.interviewassessment.service.impl;

import com.kimmy.interviewassessment.config.ConfigProperties;
import com.kimmy.interviewassessment.model.Author;
import com.kimmy.interviewassessment.model.Item;
import com.kimmy.interviewassessment.model.Word;
import com.kimmy.interviewassessment.model.WordsResponse;
import com.kimmy.interviewassessment.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ConfigProperties configProperties;

    @Override
    public WordsResponse top10WordsInlast25Stories() {

        WordsResponse wordsResponse = new WordsResponse();
        HttpEntity<?> entity = getHttpEntity();

        var body = extractNewStories(entity);

        if (null == body)
            return wordsResponse;

        // fetch the first 25 stories
        var last25 = body.stream().limit(25).collect(Collectors.toList());

        List<String> wordList = new ArrayList<>();

        //retrieve the titles from each story and populate the wordList array
        last25.parallelStream().forEachOrdered(value -> {
            try {
                Item item = restTemplate.exchange(configProperties.getItemUrl() + value + ".json", HttpMethod.GET, entity, Item.class).getBody();
                if (item != null)
                    wordList.addAll(Arrays.asList(item.getTitle().toLowerCase().split(" ")));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //group words
        Map<String, Long> map = wordList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Map.Entry<String, Long>> entryList = map.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10).collect(Collectors.toList());

        List<Word> topWords = new ArrayList<>();
        entryList.forEach(entry -> topWords.add(new Word(entry.getKey(), entry.getValue())));

        wordsResponse.setWordList(topWords);
        return wordsResponse;
    }

    private HttpEntity<?> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        headers.add("Accept-Encoding", "application/json");
        return new HttpEntity<>(headers);
    }

    @Override
    public WordsResponse top10WordsInPostsOfLastWeek() {
        // get the range of time for last week
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startDayLastWeek = today.minusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDateTime lastDayLastWeek = startDayLastWeek.plusDays(6);

        ArrayList<Object> newStories = this.extractNewStories(getHttpEntity());

        // get items by top authors
        ArrayList<Item> myList = new ArrayList<>();

        newStories.parallelStream().forEach(value -> {
            try {
                Item item = restTemplate.exchange(configProperties.getItemUrl() + value + ".json", HttpMethod.GET, this.getHttpEntity(), Item.class).getBody();

                if (null != item) {
                    long date = item.getTime();
                    System.out.println("item epochSeconds : " + date);

                    if ((date > startDayLastWeek.toEpochSecond(ZoneOffset.UTC)) && (date < (lastDayLastWeek.toEpochSecond(ZoneOffset.UTC)))) {
                        System.out.println(" Thread : " + Thread.currentThread().getId() + " " + "adding item : " + item.getId());
                        myList.add(item);
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        System.out.println(myList.size());

        // compose response message
        return getWordsResponse(myList);

    }

    private WordsResponse getWordsResponse(ArrayList<Item> myList) {
        List<String> wordList = new ArrayList<>();
        myList.forEach(item -> wordList.addAll(List.of(item.getTitle().toLowerCase().split(" "))));

        // group wordlist and pic the to 10 words
        List<Map.Entry<String, Long>> entryList = wordList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10).collect(Collectors.toList());

        List<Word> words = new ArrayList<>();
        entryList.forEach(entry -> {
            words.add(new Word(entry.getKey(), entry.getValue()));
        });

        // map to default response object
        WordsResponse wordsResponse = new WordsResponse();
        wordsResponse.setWordList(words);
        return wordsResponse;
    }

    @Override
    public WordsResponse getTop10WordsInTopAuthorsItems() {
        //find the top 600 stories
        ArrayList<Object> newStories = this.extractNewStories(getHttpEntity());

        // get items by top authors
        ArrayList<Item> myList = new ArrayList<>();

        newStories.parallelStream().forEach(value -> {
            try {
                System.out.println(Thread.currentThread().getId());
                Item item = restTemplate.exchange(configProperties.getItemUrl() + value + ".json", HttpMethod.GET, this.getHttpEntity(), Item.class).getBody();
                if (item != null) {
                    try {
                        Author author1 = restTemplate.exchange(configProperties.getUsersUrl() + item.getBy() + ".json", HttpMethod.GET, this.getHttpEntity(), Author.class).getBody();
                        if (author1 != null && author1.getKarma() > 10000)
                            System.out.println(" Thread : " + Thread.currentThread().getId() + " " + "adding item : " + item.getId());
                        myList.add(item);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // compose response message
        return getWordsResponse(myList);

    }

    private ArrayList<Object> extractNewStories(HttpEntity<?> entity) {

        var body = new ArrayList<>();
        try {
            body = restTemplate.getForObject(configProperties.getStoriesUrl(), ArrayList.class, entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return body;
    }
}
