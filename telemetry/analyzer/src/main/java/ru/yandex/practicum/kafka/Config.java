package ru.yandex.practicum.kafka;

import lombok.AccessLevel;
import org.springframework.beans.factory.annotation.Value;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;

import java.util.Properties;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class Config {

    @Value("${bootstrap_server}")
    static String bootstrapServer;

    @Value("${key_deserializer_class}")
    static String keyDeserializerClass;

    @Value("${hub events_value_deserializer_class}")
    static String hubEventsValueDeserializerClass;

    @Value("${snapshots_value_deserializer_class}")
    static String snapshotValueDeserialize;

    public static Properties producerByHubEvent () {

        baseProperties().put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, hubEventsValueDeserializerClass);
        return baseProperties();

    }

    public static Properties producerBySnapshot () {

        baseProperties().put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, snapshotValueDeserialize);
        return baseProperties();

    }

    private static Properties baseProperties () {
        Properties config = new Properties();

        config.put(ConsumerConfig.GROUP_ID_CONFIG, "groupAnalyzerHubEventsConsumer");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClass);

        return config;
    }
}
