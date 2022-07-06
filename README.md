# HackerNewsQuiz
# Task
In this assignment we’ll be asking you to build a small app that talks to a public api of HackerNews. On the following page you’ll find the documentation:
https://github.com/HackerNews/API
Your app should have three endpoints:
1. Top 10 most occurring words in the titles of the last 25 stories
2. Top 10 most occurring words in the titles of the post of exactly the last week
3. Top 10 most occurring words in titles of the last 600 stories of users with at least 10.000 karma


### Specifications:
● Spring Boot, Maven<br/>
It's up to you what the endpoints look like and how exactly the response is returned (we do expect the latter to be JSON). Of course we expect you to make sensible choices and will ask you to explain them!

### Expected deliveries:
1. Source code
2. Instructions on how to build and run the application

# Solution
I have created a spring boot application with three endpoints, one to get the top 10 most occurring words in the titles of the last 25 stories , one to get the top 10 most occurring words in the titles of the post of exactly the last week and finally one to get the top 10 most occurring words in titles of the last 600 stories of users with at least 10.000 karma.

### Libraries used and implemetation
I decided to implement Rest Client call using  Spring Rest Client RestTempate library.<br/>
I also used used Lombok dependency to get rid of the Class boiler plate code e.g getters and setters, constructors etc<br/>
I used JUnit4 testing framework to write units tests for the application
I also used parrallem streams to make concurrent api calls to the different endpoints.This boosts performance of out api.

## How to run the application
#### Prerequisite
Java 11
Maven 3.6.3

Build the application:<br/>  
I use windows almost like everyday so this is how you going to build and run in windows;<br/>

* Open your windows terminal, change directory to where you have cloned the project and run:

* Windows: mvn clean install

Run the application:

* Windows: mvn spring-boot:run

### Testing
1. Top 10 most occurring words in the titles of the last 25 stories
   Try the curl request <br/>
   ```curl --location --request GET 'http://localhost:8080/api/v1/topwordsinlateststories'``` <br/>
   or<br/>
   simply open your postman and run a GET request to  ``` http://localhost:8080/api/v1/topwordsinlateststories ```

Expected result for the above request is <br/>
```
{
    "wordList": [
        {
            "name": "the",
            "count": 10
        },
        {
            "name": "of",
            "count": 5
        },
        {
            "name": "in",
            "count": 4
        },
        {
            "name": "are",
            "count": 4
        },
        {
            "name": "a",
            "count": 4
        },
        {
            "name": "to",
            "count": 3
        },
        {
            "name": "for",
            "count": 3
        },
        {
            "name": "with",
            "count": 3
        },
        {
            "name": "and",
            "count": 2
        },
        {
            "name": "way",
            "count": 2
        }
    ]
}
```
2. Top 10 most occurring words in the titles of the post of exactly the last week
   Try the curl request <br/>
   ```curl --location --request GET 'http://localhost:8080/api/v1/topwordslastweek'```
   or simply <br/>
   open your postman and run a GET request to  ``` http://localhost:8080/api/v1/topwordslastweek ```

Expected result for the above request is
```
{
    "wordList": [
        {
            "name": "the",
            "count": 134
        },
        {
            "name": "to",
            "count": 96
        },
        {
            "name": "a",
            "count": 85
        },
        {
            "name": "of",
            "count": 82
        },
        {
            "name": "in",
            "count": 69
        },
        {
            "name": "and",
            "count": 53
        },
        {
            "name": "hn:",
            "count": 46
        },
        {
            "name": "for",
            "count": 41
        },
        {
            "name": "is",
            "count": 36
        },
        {
            "name": "how",
            "count": 32
        }
    ]
}
```
3. Top 10 most occurring words in titles of the last 600 stories of users with at least 10.000 karma
   Try the curl request <br/>
   ```curl --location --request GET 'http://localhost:8080/api/v1/topauthorsitemswords'```
   or simply <br/>
   open your postman and run a GET request to  ``` http://localhost:8080/api/v1/topauthorsitemswords ```

Expected result for the above request is
```
{
    "wordList": [
        {
            "name": "the",
            "count": 132
        },
        {
            "name": "to",
            "count": 94
        },
        {
            "name": "a",
            "count": 82
        },
        {
            "name": "of",
            "count": 82
        },
        {
            "name": "in",
            "count": 71
        },
        {
            "name": "and",
            "count": 53
        },
        {
            "name": "hn:",
            "count": 46
        },
        {
            "name": "for",
            "count": 41
        },
        {
            "name": "is",
            "count": 37
        },
        {
            "name": "how",
            "count": 32
        }
    ]
}
```
## Appreciation
I want thank you for this opportunity, I had fun working on this. I love challenges and this one was one of them.
  