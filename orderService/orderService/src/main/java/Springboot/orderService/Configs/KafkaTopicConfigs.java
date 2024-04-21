package Springboot.orderService.Configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfigs {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    // Creation of Spring Bean
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName).build();
    }

}
