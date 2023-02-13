package com.springBootwithMongo.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Slf4j

public class KafkaTopic {

    @Bean
    public NewTopic messageTopic(){
        log.debug("Topic Created");
        return TopicBuilder.name("response")
                .build();
    }
}
