package ru.yandex.practicum.kafka;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Config {

    @Value("${bootstrap_server}")
    String bootstrapServer;

    @Value("${key_deserializer_class}")
    String keyDeserializerClass;

    @Value("${hub events_value_deserializer_class}")
    String hubEventsValueDeserializerClass;

    @Value("${snapshots_value_deserializer_class}")
    String snapshotValueDeserialize;

    public Properties producerByHubEvent() {

        baseProperties().put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, hubEventsValueDeserializerClass);
        return baseProperties();

    }

    public Properties producerBySnapshot() {

        baseProperties().put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, snapshotValueDeserialize);
        return baseProperties();

    }

    private Properties baseProperties() {
        Properties config = new Properties();

        config.put(ConsumerConfig.GROUP_ID_CONFIG, "groupAnalyzerHubEventsConsumer");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClass);

        return config;
    }
}
