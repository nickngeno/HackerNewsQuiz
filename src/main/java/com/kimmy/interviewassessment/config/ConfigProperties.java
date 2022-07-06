package com.kimmy.interviewassessment.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Data
public class ConfigProperties {

    @Value("${users_url}")
    private String usersUrl;

    @Value("${stories_url}")
    private String storiesUrl;

    @Value("${item_url}")
    private String itemUrl;
}
